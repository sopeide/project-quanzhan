import request from '@/utils/request'

// 通过新建一个文件来统一存放前后端接口

export default {
  getUserList4(searchModel) { // 前端定义的名字
    return request({
      url: '/user/list',
      method: 'get',
      params: { // 因为是get请求所以用params，前后端名字得一致
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        username: searchModel.username,
        phone: searchModel.phone
      }
    })
  },

  addUser(user) { // 前端定义的名字
    return request({
      url: '/user',
      method: 'post',
      data: user
    })
  },
  // 修改用户的接口
  getUserById(id) { 
    return request({
      url: `/user/${id}`,
      method: 'get'
    })
  },
  // 修改用户的接口
  updateUser(user) { 
    return request({
      url: '/user/',
      method: 'put',
      data: user
    })
  },
  // 判断是修改还是保存
  saveUser(user) {
    if (user.id == null && user.id == undefined) {
      return this.addUser(user)
    }
    return this.updateUser(user)
  },
  deleteUserById(id) {
    return request ({
      url: `/user/${id}`,
      method: 'delete'
    })
  }
}
