<template>
  <el-card shadow="always" class="box-card">
    <el-row>
      <el-col :span="2" style='float: right'>
        <el-button :disabled="btDisabled" type="primary" @click="search">搜索</el-button>
      </el-col>
      <el-col :span="2" style='float: right; margin-right: 30px'>
        <el-input v-model="userId" clearable placeholder="请输入会员id"/>
      </el-col>
    </el-row>
    <el-row>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          prop="name"
          label="商品名称"
          width="180">
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="num"
        :page-sizes="pageSizeList"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-row>
  </el-card>
</template>

<script>
import {page} from '@/api/goods'
import pageMixin from '@/mixin/PageMixin'

export default {
  name: "GoodsManager",
  mixins: [pageMixin],
  data() {
    return {
      tableData: [],
      btDisabled: false,
      userId: null
    }
  },
  mounted() {
    this.requestPage()
  },
  methods: {
    requestPage() {
      this.btDisabled = true
      page(Object.assign({
        num: this.num,
        size: this.size,
        userId: Number.parseInt(this.userId)
      })).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
        this.btDisabled = false
      }).catch(_ => {
        this.btDisabled = false
      })
    },
    handleSizeChange(size) {
      this.size = size
      this.requestPage()
    },
    handleCurrentChange(num) {
      this.num = num
      this.requestPage()
    },
    search() {
      this.requestPage()
    }
  }
}
</script>

<style scoped>
.box-card {
  margin: 20px;
}
</style>
