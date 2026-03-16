<template>
  <div class="camera-card">
    <div class="card-header">
      <span>无人机摄像头视图</span>
      <button @click="goFullscreen">全屏</button>
    </div>
    <video ref="video" autoplay muted playsinline class="camera-video"></video>
  </div>
</template>

<script>
export default {
  name: 'DroneCameraCard',
  mounted() {
    this.initCamera()
  },
  methods: {
    initCamera() {
      navigator.mediaDevices.getUserMedia({ video: true, audio: false })
        .then((stream) => {
          this.$refs.video.srcObject = stream
        })
        .catch((err) => {
          console.error('无法访问摄像头：', err)
        })
    },
    goFullscreen() {
      const video = this.$refs.video
      if (video.requestFullscreen) {
        video.requestFullscreen()
      } else if (video.webkitRequestFullscreen) {
        video.webkitRequestFullscreen()
      } else if (video.mozRequestFullScreen) {
        video.mozRequestFullScreen()
      } else if (video.msRequestFullscreen) {
        video.msRequestFullscreen()
      }
    },
  }
}
</script>

<style scoped>
.camera-card {
  width: 100%;
  max-width: 1000px;
  height: 360px;
  margin: 0 auto;
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.12);
  display: flex;
  flex-direction: column;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 12px;
  background: #f0f0f0;
  font-weight: bold;
}

.camera-video {
  flex: 1;
  width: 100%;
  object-fit: cover;
  background: #000;
}
</style>
