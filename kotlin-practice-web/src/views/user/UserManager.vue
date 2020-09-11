<template>
  <el-card shadow="always" class="box-card">
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="account"
        label="账号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="id"
        label="id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="nickname"
        label="昵称"
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
  </el-card>
</template>

<script>
import pageMixin from '@/mixin/PageMixin'
import {page} from "@/api/user"

export default {
  name: "UserManager",
  mixins: [pageMixin],
  data() {
    return {
      tableData: []
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
        size: this.size
      })).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
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
