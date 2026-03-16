<template>
  <div class="attitude-display">
    <h1 class="title">飞行姿态角</h1>
    <div class="vertical-container">
      <!-- Pitch -->
      <div class="angle-card pitch">
        <div class="angle-header">
          <span class="angle-label">俯仰角</span>
          <span class="angle-en">PITCH</span>
        </div>
        <div class="angle-value">
          {{ pitch.toFixed(4) }}°
        </div>
      </div>

      <!-- Roll -->
      <div class="angle-card roll">
        <div class="angle-header">
          <span class="angle-label">横滚角</span>
          <span class="angle-en">ROLL</span>
        </div>
        <div class="angle-value">
          {{ roll.toFixed(4) }}°
        </div>
      </div>

      <!-- Yaw -->
      <div class="angle-card yaw">
        <div class="angle-header">
          <span class="angle-label">偏航角</span>
          <span class="angle-en">YAW</span>
        </div>
        <div class="angle-value">
          {{ yaw.toFixed(4) }}°
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState('drone', ['pitch', 'roll', 'yaw']) // 从 Vuex 中读取姿态角
  }
}
</script>

<style scoped>
.attitude-display {
  padding: 2rem;
  max-width: 400px;
  margin: 0 auto;
  font-family: 'Segoe UI', sans-serif;
}

.title {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 2rem;
  font-size: 1.8rem;
}

.vertical-container {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.angle-card {
  background: #ffffff;
  border-radius: 10px;
  padding: 1.2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.angle-card:hover {
  transform: translateY(-2px);
}

.angle-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.angle-label {
  font-size: 1.1rem;
  color: #666;
}

.angle-en {
  font-size: 0.9rem;
  color: #999;
  letter-spacing: 1px;
}

.angle-value {
  font-size: 2.2rem;
  font-weight: 600;
  text-align: center;
  padding: 0.5rem 0;
}

/* 颜色区分 */
.pitch .angle-value { color: #e74c3c; }
.roll .angle-value { color: #27ae60; }
.yaw .angle-value { color: #2980b9; }
</style>
