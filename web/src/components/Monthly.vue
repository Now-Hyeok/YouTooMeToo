<template>
    <div class="member-container">
      <el-row>
        <el-table :data="store.getMemberList">
          <el-table-column prop="memberName" label="이름"></el-table-column>
        </el-table>
      </el-row>


    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import { memberStore } from '../store';

const store = memberStore();
onMounted(()=>{
})


</script>

<style>

</style>