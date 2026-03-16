import request from '@/utils/request'

export default {
  getBiaodan1List(searchModel) { // 前端定义的名字
    return request({
      url: '/biaodan1/pist',
      method: 'get',
      params: { // 因为是get请求所以用params，前后端名字得一致
        pageNo: 1,
        pageSize: 10,
        biaodan1Name: searchModel.biaodan1Name,
        biaodan1Value: searchModel.biaodan1Value
      }
    })
  }
}
