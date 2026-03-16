<template>
  <div class="geo-card">
    <h3 class="card-title">地理位置信息</h3>
    
    <div class="geo-content">
      <!-- 第一行：经纬度 -->
      <div class="row-container">
        <div class="geo-item latitude">
          <div class="geo-label">
            <span class="label-zh">纬度</span>
            <span class="label-en">LATITUDE</span>
          </div>
          <div class="geo-value">
            {{ formattedLatitude }}
          </div>
        </div>

        <div class="geo-item longitude">
          <div class="geo-label">
            <span class="label-zh">经度</span>
            <span class="label-en">LONGITUDE</span>
          </div>
          <div class="geo-value">
            {{ formattedLongitude }}
          </div>
        </div>
      </div>

      <!-- 第二行：海拔 -->
      <div class="geo-item altitude">
        <div class="geo-label">
          <span class="label-zh">海拔高度</span>
          <span class="label-en">ALTITUDE</span>
        </div>
        <div class="geo-value">
          {{ formattedAltitude }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState('drone', ['latitude', 'longitude', 'altitude']),

    formattedLatitude() {
      const absVal = Math.abs(this.latitude).toFixed(4);
      return `${absVal}° ${this.latitude >= 0 ? 'N' : 'S'}`;
    },
    formattedLongitude() {
      const absVal = Math.abs(this.longitude).toFixed(4);
      return `${absVal}° ${this.longitude >= 0 ? 'E' : 'W'}`;
    },
    formattedAltitude() {
      return `${this.altitude.toFixed(1)} 米`;
    }
  }
}
</script>
<style scoped>
.geo-card {
  background: #ffffff;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  margin: 1rem auto;
  transition: transform 0.2s;
}

.geo-card:hover {
  transform: translateY(-2px);
}

.card-title {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 1.5rem;
  font-size: 1.4rem;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #eee;
}

.geo-content {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

/* 经纬度行容器 */
.row-container {
  display: flex;
  gap: 1rem;
  justify-content: space-between;
}

.geo-item {
  flex: 1;
  padding: 0.8rem;
  border-radius: 8px;
  background: #f8f9fa;
  min-width: 180px;
}

.geo-label {
  display: flex;
  flex-direction: column;
  margin-bottom: 0.5rem;
}

.label-zh {
  font-size: 1rem;
  color: #666;
  margin-bottom: 0.2rem;
}

.label-en {
  font-size: 0.8rem;
  color: #999;
  letter-spacing: 0.5px;
}

.geo-value {
  font-size: 1.3rem;
  font-weight: 600;
  font-family: 'Courier New', monospace;
  text-align: center;
}

/* 颜色区分 */
.latitude .geo-value { color: #27ae60; }  /* 绿色 */
.longitude .geo-value { color: #2980b9; } /* 蓝色 */
.altitude .geo-value { color: #e67e22; }  /* 橙色 */

@media (max-width: 480px) {
  .geo-card {
    margin: 0.5rem;
    padding: 1rem;
  }
  
  .row-container {
    flex-direction: column;
    gap: 0.8rem;
  }

  .geo-item {
    min-width: auto;
  }

  .geo-value {
    font-size: 1.1rem;
  }
}
</style>