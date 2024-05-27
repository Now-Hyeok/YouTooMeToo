
<template>

  <h1> 너도나도 소모임 멤버 출석 관리</h1>

  <div>
      <el-table
      ref="multipleTable"
      :data="memberList"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        property="name"
        label="Name"
        width="120">
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button @click="toggleSelection()">Clear selection</el-button>
    </div>
  </div>


  <el-button type="primary">회원 추가</el-button>
</template>

<script>
import {onMounted, ref} from 'vue'

export default {
  setup(){

    const memberList = ref([{"name":"김경호"},{"name":"윤남현"},{"name":"이찬희"}]);

    const toggleSelection = (rows)=> {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      }

    const handleSelectionChange  = (val)=> {
        this.multipleSelection = val;
      }
    return{
      memberList,
      handleSelectionChange,
      toggleSelection
    }
  }
}
</script>

<style>

</style>
