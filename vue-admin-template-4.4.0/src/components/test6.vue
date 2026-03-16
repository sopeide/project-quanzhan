<template>
  <div class="card">
    <div class="axis-item x-axis">
      <div class="color-indicator"></div>
      <span class="axis-label">X轴</span>
    </div>
    <div class="axis-item y-axis">
      <div class="color-indicator"></div>
      <span class="axis-label">Y轴</span>
    </div>
    <div class="axis-item z-axis">
      <div class="color-indicator"></div>
      <span class="axis-label">Z轴</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AxisCard'
}
</script>

<style scoped>
.card {
  width: 220px;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  font-family: 'Arial', sans-serif;
}

.axis-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  padding: 10px 15px;
  border-radius: 8px;
  transition: transform 0.3s ease;
}

.axis-item:last-child {
  margin-bottom: 0;
}

.axis-item:hover {
  transform: translateX(5px);
}

.color-indicator {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  margin-right: 12px;
  flex-shrink: 0;
}

.axis-label {
  font-size: 18px;
  font-weight: 500;
}

.x-axis {
  background-color: rgba(255, 193, 7, 0.15);
}
.x-axis .color-indicator {
  background-color: #fffc36; /* 黄色 */
}

.y-axis {
  background-color: rgba(76, 175, 80, 0.15);
}
.y-axis .color-indicator {
  background-color: #07ff0f; /* 绿色 */
}

.z-axis {
  background-color: rgba(33, 150, 243, 0.15);
}
.z-axis .color-indicator {
  background-color: #2196F3; /* 蓝色 */
}
</style>