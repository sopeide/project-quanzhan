<template>
  <div class="screen-container">
    <header class="screen-header">
      <div>
        <!-- <img src="/static/img/header_border_light.png" alt="" /> -->
      </div>
      <!-- <span class="logo">
        <img src="/static/img/logo_dark.png" alt="" />
      </span> -->
      <span class="title">无人机平台实时监控系统</span>
      <div class="title-right">
        <!-- <img src="/static/img/qiehuan_dark.png" class="qiehuan" /> -->
        <span class="datetime">2049-01-01 00:00:00</span>
      </div>
    </header>
    <div class="screen-body">
      <section class="screen-left">
        <div id="left-top">
          <!-- 销量趋势图表 -->
          <Test1></Test1>
        </div>
        <div id="left-bottom">
          <!-- 商家销售金额图表 -->
          <Test4></Test4>
        </div>
      </section>
      <section class="screen-middle">
        <div id="middle-top">
          <!-- 商家分布图表 -->
          <Test5></Test5>
        </div>
        <div id="middle-bottom">
          <!-- 地区销量排行图表 -->
          <Test6></Test6>
        </div>
      </section>
      <section class="screen-right">
        <div id="right-top">
          <!-- 热销商品占比图表 -->
          <Test2></Test2>
        </div>
        <div id="right-bottom">
          <!-- 库存销量分析图表 -->
          <Test3></Test3>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
import Test1 from '@/components/test1.vue'
import Test2 from '@/components/test2.vue'
import Test3 from '@/components/test3.vue'
import Test4 from '@/components/test4.vue'
import Test5 from '@/components/test5.vue'
import Test6 from '@/components/test6.vue'

export default {
  components: {
    Test1,
    Test2,
    Test3,
    Test4,
    Test5,
    Test6
  }
};
</script>
  <style lang="scss" scoped>
.screen-container {
  width: 100%;
  height: 100%;
  padding: 0 20px;
  position: absolute;
  background-color: #ffffff;
  color: #000000;
  box-sizing: border-box;
}
.screen-header {
  width: 100%;
  height: 64px;
  font-size: 20px;
  position: relative;
  > div {
    img {
      width: 100%;
    }
  }
  .title {
    position: absolute;
    left: 50%;
    top: 50%;
    font-size: 20px;
    transform: translate(-50%, -50%);
  }
  .title-right {
    display: flex;
    align-items: center;
    position: absolute;
    right: 0px;
    top: 50%;
    transform: translateY(-80%);
  }
  .qiehuan {
    width: 28px;
    height: 21px;
    cursor: pointer;
  }
  .datetime {
    font-size: 15px;
    margin-left: 10px;
  }
  .logo {
    position: absolute;
    left: 0px;
    top: 50%;
    transform: translateY(-80%);
    img {
      height: 35px;
      width: 128px;
    }
  }
}
.screen-body {
  width: 100%;
  height: 100%;
  display: flex;
  margin-top: 10px;
  .screen-left {
    height: 100%;
    width: 27.6%;
    #left-top {
      height: 53%;
      position: relative;
    }
    #left-bottom {
      height: 31%;
      margin-top: 25px;
      position: relative;
    }
  }
  .screen-middle {
    height: 100%;
    width: 41.5%;
    margin-left: 1.6%;
    margin-right: 1.6%;
    #middle-top {
      width: 100%;
      height: 56%;
      position: relative;
    }
    #middle-bottom {
      margin-top: 25px;
      width: 100%;
      height: 28%;
      position: relative;
    }
  }
  .screen-right {
    height: 100%;
    width: 27.6%;
    #right-top {
      height: 46%;
      position: relative;
    }
    #right-bottom {
      height: 38%;
      margin-top: 25px;
      position: relative;
    }
  }
}
// section > div {
//   border: 1px solid red;
// }
</style>
