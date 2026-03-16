<template>
  <div class="dashboard-container">
    <!-- 主视觉区域 -->
    <div class="drone-visualization">
      <!-- 背景图容器 -->
      <div class="drone-image-wrapper">
        <img src="/public/static/img/tupian1.jpeg" 
             class="drone-main-image"
             alt="无人机主体图像">
      </div>

      <!-- 动态数据卡片 -->
      <div v-for="(item, index) in droneMetrics"
           :key="item.title"
           :class="['data-card', `card-pos-${index}`]">
        <div class="card-content">
          <h3 class="metric-title">{{ item.title }}</h3>
          <p class="metric-value">{{ item.value }}</p>
        </div>
        <div :class="['connection-line', `line-${index}`]"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      droneMetrics: [
        { title: '相机模块', value: '48MP 4K/60fps' },
        { title: '图传系统', value: '1080p@3.2km' },
        { title: '避障能力', value: '六向双目避障' },
        { title: '续航时间', value: '32分钟' }
      ]
    }
  },
  methods: {
    // 预留API接入方法
    async fetchDroneData() {
      // 后续可替换为实际API调用
      // const response = await axios.get('/api/drone-metrics')
      // this.droneMetrics = response.data
    }
  },
  created() {
    this.fetchDroneData()
  }
}
</script>

<style scoped>
.dashboard-container {
  position: relative;
  max-width: 1200px;
  margin: 2rem auto;
  padding: 20px;
}

.drone-visualization {
  position: relative;
  width: 80%;
  margin: 0 auto;
  min-height: 600px;
}

.drone-image-wrapper {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 320px;
  z-index: 2;
}

.drone-main-image {
  width: 100%;
  filter: drop-shadow(0 0 12px rgba(0,0,0,0.1));
}

.data-card {
  position: absolute;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 10px;
  padding: 1.5rem;
  width: 160px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  border: 1px solid rgba(0,0,0,0.05);
  z-index: 3;
}

.metric-title {
  color: #2c3e50;
  font-size: 1.1rem;
  margin-bottom: 0.5rem;
}

.metric-value {
  color: #34495e;
  font-size: 1.4rem;
  font-weight: 600;
  margin: 0;
}

/* 卡片定位 */
.card-pos-0 { /* 右侧 */
  top: 30%;
  right: 5%;
  transform: translateY(-50%);
}
.card-pos-1 { /* 底部 */
  bottom: 10%;
  right: 20%;
}
.card-pos-2 { /* 左侧 */
  top: 30%;
  left: 5%;
  transform: translateY(-50%);
}
.card-pos-3 { /* 顶部 */
  top: 10%;
  left: 20%;
}

/* 连接线样式 */
.connection-line {
  position: absolute;
  background: #3498db;
  height: 2px;
  transform-origin: left center;
  z-index: 1;
}

.line-0 { /* 右侧连线 */
  width: 120px;
  transform: rotate(-45deg);
  right: -120px;
  top: 50%;
}

.line-1 { /* 底部连线 */
  width: 140px;
  transform: rotate(45deg);
  bottom: -40px;
  right: -100px;
}

.line-2 { /* 左侧连线 */
  width: 120px;
  transform: rotate(45deg);
  left: -120px;
  top: 50%;
}

.line-3 { /* 顶部连线 */
  width: 140px;
  transform: rotate(-45deg);
  top: -40px;
  left: -100px;
}

@media (max-width: 768px) {
  .drone-visualization {
    width: 95%;
    min-height: 500px;
  }
  
  .data-card {
    width: 140px;
    padding: 1rem;
  }

  .connection-line {
    display: none; /* 小屏幕隐藏连接线 */
  }
}
</style>