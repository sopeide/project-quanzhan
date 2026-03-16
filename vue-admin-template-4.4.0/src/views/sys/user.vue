<template>
  <div>
    <!-- 搜索框 -->
    <el-card id="search">
     <el-input v-model="searchModel.username" placeholder="用户名" clearable></el-input>
     <el-input v-model="searchModel.phone" placeholder="电话" clearable></el-input>
     <el-button @click="getUserlist" type="primary" round icon="el-icon-search">查询</el-button>
     <el-button @click="openEditUi(null)" type="primary" icon="el-icon-plus" id="chazao"></el-button>
    </el-card>

    <!-- 表格内容 -->
    <el-card>
      <el-table :data="userlist" stripe style="width: 100%">
        <el-table-column type="index" label="#" width="80">
          <template slot-scope="scope">
            <!-- 调整排列数，(pageNo-1) * pageSize + index +1 ;index其实为0  -->
            {{ (searchModel.pageNo-1) * searchModel.pageSize + scope.$index +1 }}
          </template>
        </el-table-column>
        <el-table-column prop="id" label="用户id" width="150"></el-table-column>
        <el-table-column prop="username" label="用户名" width="180"></el-table-column>
        <el-table-column prop="phone" label="电话" width="150"></el-table-column>
        <el-table-column prop="status" label="用户状态" width="150">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == 1">正常</el-tag>
            <el-tag v-else type="danger">禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180"></el-table-column>
        <el-table-column label="操作" id="caozuo">
          <template slot-scope="scope">
            <el-button @click="openEditUi(scope.row.id)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="deleteUser(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
  </el-card>
  <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 30, 40]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <!-- 添加用户框 -->
    <el-dialog @close="clearFrom" :title="title" :visible.sync="dialogFormVisible"> 
      <el-form :model="userForm" ref="userFormRef" :rules="rules">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-model="userForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="userForm.id == null || userForm.id == undefined" label="密码" prop="password" :label-width="formLabelWidth">
          <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="userForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户状态" :label-width="formLabelWidth">
          <el-switch v-model="userForm.status" :active-value="1" :inactive-value="0"></el-switch>
        </el-form-item>
        <el-form-item label="电子邮件" prop="email" :label-width="formLabelWidth">
          <el-input v-model="userForm.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import userApi from '@/api/userManage' // 调入刚刚设置的接口文件
import { Message } from 'element-ui'
export default {
  data() {
    // 验证邮箱程序
    var checkEmail = (rule, value, callback) => {
      var reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/
      if (!reg.test(value)) {
        return callback(new Error('邮箱格式错误'))
      } else {
        callback()
      }
    }
    //
    return {
      formLabelWidth: '150px',
      userForm: {},
      dialogFormVisible: false, // 是否可见
      title: '',
      total: 0,
      searchModel: {
        pageNo: 1, // 当前是第几页
        pageSize: 10 // 当前页有多少个
      },
      userlist: [],
      rules: {
        username: [
          { require: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 50, message: '长度在3到50个字符', trigger: 'blur' }
        ],
        password: [
          { require: true, message: '请输入初始密码', trigger: 'blur' },
          { min: 6, max: 16, message: '长度在6到16个字符', trigger: 'blur' }
        ],
        email: [
          { require: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    deleteUser(user) {
      this.$confirm(`您确认删除用户 ${user.username}`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userApi.deleteUserById(user.id).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getUserlist()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveUser() {
      // 触发表单验证
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          // 传输数据给后台
          userApi.saveUser(this.userForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            });
            // 关闭对话框
            this.dialogFormVisible = false;
            // 刷新表格
            this.getUserlist();
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    clearFrom() {
      this.userForm = {}
      this.$refs.userFormRef.clearValidate()
    },
    openEditUi(id) {
      if (id == null) {
        this.title = '新增用户'
      } else {
        this.title = '修改用户'
        // 根据id查询用户数据
        userApi.getUserById(id).then(response => {
          this.userForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getUserlist()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getUserlist()
    },
    getUserlist() {
      userApi.getUserList4(this.searchModel).then(response => {
        this.userlist = response.data.rows
        this.total = response.data.total
      })
    }
  },
  created() { // 创建时调用接口调用
    this.getUserlist();
  }
}
</script>

<style>
#search .el-input{
  width: 200px;
  margin: 5px;
}
#search .el-button{
  margin: 5px;
}
#chazao{
  float : right;
}
#search{
  margin-bottom: 10px;
}

</style>
