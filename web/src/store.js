import * as memberApiService from './service/memberApiService';
import * as attendanceApiService from './service/attendanceApiService'

import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const memberStore = defineStore("memberStore", () => {
  const memberList = ref([]);
  const memberListWithAttendance = ref([]);
  const week = ref();
  
  const updateMemberList = async() =>{
    let response = await memberApiService.getMemberList();
    if (response.status === 200) {
      memberList.value = response.data;
  }
  week.value = getCurrentWeek();
  updateMemberListWithAttendance();
}

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

const updateMemberListWithAttendance = async () => {
    // 멤버 데이터를 가져온 후 출석 상태 초기화
    memberListWithAttendance.value = memberList.value.map(member => ({
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
        let member = memberListWithAttendance.value.find(m => m.memberId === attendance.memberId);
        if (member) {
          member.attendance[attendance.attendanceDate] = true;
        }
      });
    }
};


  const getMemberList = computed(() => memberList.value);
  const getMemberListWithAttendance = computed(() => memberListWithAttendance.value);

  return { memberList, updateMemberList,getMemberList, getMemberListWithAttendance,updateMemberListWithAttendance};
});