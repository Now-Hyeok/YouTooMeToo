package jh.YoutooMetoo.service;

import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendanceRepository attendanceRepository;


    public void attendance(Long memberId){
        Attendance attendance = new Attendance();
        attendance.setMemberId(memberId);
        attendance.setAttendanceDate(LocalDate.now());

        attendanceRepository.save(attendance);
    }

    public List<Attendance> findAll(){
        return attendanceRepository.findAll();
    }

    public List<Attendance> findAllByMemberId(Long memberId){
        return attendanceRepository.findAllByMemberId(memberId);
    }

    public void deleteAttendance(Long attendanceId){
        attendanceRepository.deleteById(attendanceId);
    }



}
