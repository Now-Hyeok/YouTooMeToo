<template>
  <el-table :data="memberList">
    <el-table-column prop="memberName" label="회원명"/>

    <el-table-column label="주간출결여부">
      <el-table-column
        v-for="(date, index) in week" 
        :key="index" 
        :label="date"
        >
        <template #default="{ row }">
          <el-button 
            :type="row.attendance[date] ? 'success' : 'danger'"
            @click="toggleAttendance(row, date)"
          >
            {{ row.attendance[date] ? '출석' : '불출석' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table-column>
  </el-table>
</template>

<script setup>
import * as memberApiService from '../service/memberApiService';
import * as attendanceApiService from '../service/attendanceApiService';
import {ref, onMounted} from 'vue';

const today = ref(null);
const week = ref();

const getCurrentWeek = () => {
  const day = new Date();
  const sunday = day.getTime() - 86400000 * day.getDay();
  day.setTime(sunday);

  const result = [day.toISOString().slice(0,10)];

  for(let i=1; i<7; i++){
    day.setTime(day.getTime() + 86400000);
    result.push(day.toISOString().slice(0,10));
  }

  return result;
}

const memberList = ref([]);

const getMemberList = async () => {
  let response = await memberApiService.getMemberList();
  if (response.status === 200) {
    // 멤버 데이터를 가져온 후 출석 상태 초기화
    memberList.value = response.data.map(member => ({
      ...member,
      attendance: week.value.reduce((acc, date) => {
        acc[date] = false;
        return acc;
      }, {})
    }));

    // 출석 데이터를 가져와서 각 멤버의 출석 상태를 업데이트
    let attendanceResponse = await attendanceApiService.getAttendanceList();
    if (attendanceResponse.status === 200) {
      attendanceResponse.data.forEach(attendance => {
        let member = memberList.value.find(m => m.memberId === attendance.memberId);
        if (member) {
          member.attendance[attendance.attendanceDate] = true;
        }
      });
    }
  }
};

const toggleAttendance = async (member, date) => {
  member.attendance[date] = !member.attendance[date];
  let data = {
    memberId : member.memberId,
    attendanceDate : date,
    attendance : member.attendance[date]
  }
  let response = await attendanceApiService.updateAttendance(data);
  if (response.status !== 200) {
    member.attendance[date] = !member.attendance[date];  // 오류가 발생한 경우 상태를 되돌림
    alert('출석 오류 발생');
  }
};

onMounted(()=>{
  getMemberList();
  today.value = new Date();
  week.value = getCurrentWeek();
});

</script>

<style>

</style>
