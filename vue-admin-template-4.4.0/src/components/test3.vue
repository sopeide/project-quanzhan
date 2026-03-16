<template>
  <div class="map-card">
    <div ref="mapContainer" class="map-container"></div>
  </div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
import { mapState } from 'vuex'

export default {
  name: 'DroneMapCard',
  data() {
    return {
      map: null,
      marker: null,
      initialized: false
    }
  },
  computed: {
    ...mapState('drone', ['latitude', 'longitude'])
  },
  watch: {
    latitude(newLat) {
      this.updatePosition([this.longitude, newLat])
    },
    longitude(newLng) {
      this.updatePosition([newLng, this.latitude])
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initMap()
    })
  },
  methods: {
    initMap() {
      AMapLoader.load({
        key: '8e7caa4ade172ea014c538afebcc2488',
        version: '2.0',
        plugins: [],
      })
        .then((AMap) => {
          const initialPos = (this.longitude && this.latitude)
            ? [this.longitude, this.latitude]
            : [116.397428, 39.90923] // 默认北京天安门

          this.map = new AMap.Map(this.$refs.mapContainer, {
            viewMode: '3D',
            zoom: 18,
            center: initialPos,
            layers: [
              new AMap.TileLayer.Satellite(),
              new AMap.TileLayer.RoadNet(),
            ],
          })

          this.marker = new AMap.Marker({
            position: initialPos,
            icon: 'https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png',
            offset: new AMap.Pixel(-13, -30),
          })

          this.map.add(this.marker)
          this.initialized = true
        })
        .catch((e) => {
          console.error('高德地图加载失败:', e)
        })
    },

    updatePosition(pos) {
      if (!this.map || !this.marker || !this.initialized) return
      this.marker.setPosition(pos)
      this.map.setCenter(pos)
    }
  }
}
</script>

<style scoped>
.map-card {
  width: 100%;
  max-width: 1000px; /* 最大宽度控制卡片扩展范围 */
  height: 360px;
  margin: 0 auto;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.12);
  background: #fff;
}

.map-container {
  width: 100%;
  height: 100%;
}
</style>
