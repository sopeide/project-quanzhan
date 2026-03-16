<!-- <template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script> -->

<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  mounted() {
    const socket = new WebSocket('ws://10.139.130.20:1111/ws/drone')

    socket.onopen = () => {
      console.log('✅ WebSocket 已连接')
    }

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data)
      console.log('📡 接收到数据:', data)
      this.$store.dispatch('drone/setDroneData', data)
    }

    socket.onerror = (err) => {
      console.error('❌ WebSocket 错误:', err)
    }

    socket.onclose = () => {
      console.warn('⚠️ WebSocket 连接关闭')
    }
  }
}
</script>

<style>
  .app-main{
    padding: 10px;
  }
</style>
