<template>
    <div class="com-page">
      <test5></test5>
    </div>
  </template>
  
  <script>
  import Test5 from '@/components/test5.vue'
  // import roleApi from '@/api/roleManage'
  export default {
    data() {
      return {
      }
    },
    methods: {
    },
    components: {
      test5: Test5
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
  