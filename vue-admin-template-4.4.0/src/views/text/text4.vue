<template>
    <div class="com-page">
      <test4></test4>
    </div>
  </template>
  
  <script>
  import Test4 from '@/components/test4.vue'
  // import roleApi from '@/api/roleManage'
  export default {
    data() {
      return {
      }
    },
    methods: {
    },
    components: {
      test4: Test4
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
  