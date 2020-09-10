<template>
  <el-card shadow="always" class="box-card">
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="商品名称"
        width="180">
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
import { page } from '@/api/goods'
export default {
  name: "GoodsManager",
  data() {
    return {
      tableData: []
    }
  },
  mounted() {
    page({}).then(res => {
       this.tableData = res.data
    })
  }
}
</script>

<style scoped>
.box-card {
  margin: 20px;
}
</style>
