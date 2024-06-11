<template>
    <div class="member-container">
      <el-row>
        <el-table :data="store.getMemberList" @selection-change="handleSelectionChange" ref="multipleTable">
          <el-table-column type="selection"></el-table-column>
          <el-table-column prop="memberName" label="이름"></el-table-column>
        </el-table>
      </el-row>

      <el-row :gutter="20" class="button-row">
        <el-col :span="8">
          <el-button @click="deleteSelectedMember">선택 회원 삭제</el-button>
        </el-col>
        <el-col :span="8">
          <el-button @click="clearSelection">선택 취소</el-button>
        </el-col>

      </el-row>
      <el-row :gutter="20" class="button-row">
        <el-col :span="8">
            <el-input v-model="newMemberName"></el-input>
          <el-button @click="registerMember">회원 등록</el-button>
        </el-col>
      </el-row>
    </div>
  </template>
  
  <script setup>
  import { onMounted, ref } from 'vue';
  import * as memberApiService from '../service/memberApiService';
  import { memberStore } from '../store';

  const multipleSelection = ref([]);
  const multipleTable = ref(null);
  const newMemberName = ref();
  const store = memberStore();

  const deleteSelectedMember = async () => {
    let data = multipleSelection.value;
    let response = await memberApiService.deleteMember(data);
    if (response.status===200){
        store.updateMemberList();
        clearSelection();
    }
    // Implement the deletion logic here
  };

  const registerMember = async () =>{
    if(newMemberName.value === ''){
        return;
    }
    let data = {
        memberName : newMemberName.value
    }
    let response = await memberApiService.registerMember(data);
    if(response.status===200){
      store.updateMemberList();


    }else{
        alert("회원 등록 실패");
    }

    newMemberName.value = '';
  }
  
  const clearSelection = () => {
    if (multipleTable.value) {
      multipleTable.value.clearSelection();
    }
  };
  
  const handleSelectionChange = (val) => {
    multipleSelection.value = val;
  };
  
  onMounted(() => {
    store.updateMemberList();

  });
  </script>
  
  <style>
  .member-container {
    padding: 20px;
  }
  
  .el-button {
    width: 100%; /* Ensure buttons take the full width of their column */
  }
  
  .button-row {
    margin-top: 10px;
  }
  </style>
  