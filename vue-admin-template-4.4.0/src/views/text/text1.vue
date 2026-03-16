<template>
  <div class="com-page">
    <test1></test1>
  </div>
</template>

<script>
import Test1 from '@/components/test1.vue'
// import roleApi from '@/api/roleManage'
export default {
  data() {
    return {
    }
  },
  methods: {
  },
  components: {
    test1: Test1
  }
}
</script>

<style>
.com-page {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: absolute;
}
</style>
