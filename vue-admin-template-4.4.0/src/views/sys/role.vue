<template>
  <div class="com-page">
    <biaodan1></biaodan1>
  </div>
</template>

<script>
import Biaodan1 from '@/components/Biaodan1.vue'
// import roleApi from '@/api/roleManage'
export default {
  data() {
    return {
    //   total: 0,
    //   searchModel: {
    //     pageNo: 1,
    //     pageSize: 10
    //   },
    //   biaodan1List: []
    }
  },
  methods: {
    // handleSizeChange() {

    // },
    // handleCurrentChange() {

    // },
    // getBiaodan1List() {
    //   roleApi.getBiaodan1List(this.searchModel).then(response => {
    //     this.biaodan1List = response.data.rows
    //     this.total = response.data.total
    //   })
    // }
  },
  // created() {
  //   this.getBiaodan1List()
  // },
  components: {
    biaodan1: Biaodan1
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
