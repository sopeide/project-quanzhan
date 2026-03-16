<template>
  <div class="com-page">
    <test3></test3>
  </div>
</template>

<script>
import Test3 from '@/components/test3.vue'
// import roleApi from '@/api/roleManage'
export default {
  data() {
    return {
    }
  },
  methods: {
  },
  components: {
    test3: Test3
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
