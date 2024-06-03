<template>
  <el-container>
    <el-header>
      <h1>너도 나도 회원 출석 관리</h1>
    </el-header>
    <el-tabs type="card" class="main-tab">
      <el-tab-pane label="회원관리">
        <Member />
      </el-tab-pane>
      <el-tab-pane label="주간">
        <Weekly />
      </el-tab-pane>
      <el-tab-pane label="월간">
        <Monthly />
      </el-tab-pane>
      <el-tab-pane label="total">
        <Total />
      </el-tab-pane>
    </el-tabs>
  </el-container>
</template>

<script setup>
import Member from './Member.vue';
import Weekly from './Weekly.vue';
import Monthly from './Monthly.vue';
import Total from './Total.vue';
</script>

<style>
.main-tab {
  height: 100%;
}
.main-tab .el-tabs__item:hover {
  color: #000000;
  cursor: pointer;
}
.main-tab .el-tabs__header .el-tabs__item {
  font-family: 'NanumSquare', sans-serif;
  font-size: 15px;
}
.main-tab .el-tabs__header .el-tabs__item.is-active {
  background-color: #1a8084;
  color: white;
}
.main-tab .el-tabs__header {
  margin: 0;
  padding: 0;
  position: relative;
}
</style>
