<template>
  <div class="com-page">
    <test2></test2>
  </div>
</template>

<script>
import Test2 from '@/components/test2.vue'
// import roleApi from '@/api/roleManage'
export default {
  data() {
    return {
    }
  },
  methods: {
  },
  components: {
    test2: Test2
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
