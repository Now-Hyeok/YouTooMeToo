<template>
  <el-table :data="store.getMemberListWithAttendance">
    <el-table-column prop="memberName" label="회원명"/>

    <el-table-column label="주간출결여부">
      <el-table-column
        v-for="(dateInfo, index) in week" 
        :key="index" 
        :label="`${dateInfo.dayOfWeek} (${dateInfo.date})`"
      >
        <template #default="{ row }">
          <el-button 
            :type="row.attendance[dateInfo.date] ? 'success' : 'danger'"
            @click="toggleAttendance(row, dateInfo.date)"
          >
            {{ row.attendance[dateInfo.date] ? '앙 출석 띠' : '힝 불참 띠 ㅜㅜ ' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table-column>

    <el-table-column
      fixed="right"
      label="출석횟수"
      width="120">
      <template #default="{ row }">
        {{ calculateAttendanceCount(row.attendance) }}
      </template>
    </el-table-column>

  </el-table>
</template>
<script setup>
import * as memberApiService from '../service/memberApiService';
import * as attendanceApiService from '../service/attendanceApiService';
import {ref, onMounted} from 'vue';
import { memberStore } from '../store';

const calculateAttendanceCount = (attendance) => {
  return Object.values(attendance).filter(Boolean).length;
};


const today = ref(null);
const week = ref();
const store = memberStore();
const WEEKDAY = ['일', '월', '화', '수', '목', '금', '토'];
const getCurrentWeek = () => {
  const day = new Date();
  const sunday = day.getTime() - 86400000 * day.getDay();
  day.setTime(sunday);

  const result = [{ date: day.toISOString().slice(0, 10), dayOfWeek: WEEKDAY[day.getDay()] }];

  for(let i = 1; i < 7; i++){
    day.setTime(day.getTime() + 86400000);
    result.push({ date: day.toISOString().slice(0, 10), dayOfWeek: WEEKDAY[day.getDay()] });
  }

  return result;
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
store.updateMemberListWithAttendance();
  today.value = new Date();
  week.value = getCurrentWeek();
});

</script>

<style>

</style>
