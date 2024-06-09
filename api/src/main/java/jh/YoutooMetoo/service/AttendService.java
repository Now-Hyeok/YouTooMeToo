package jh.YoutooMetoo.service;

import jakarta.transaction.Transactional;
import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.domain.AttendanceRequestDto;
import jh.YoutooMetoo.domain.AttendanceResponseDto;
import jh.YoutooMetoo.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendanceRepository attendanceRepository;


    public void attendance(Long memberId, String date){
        Attendance attendance = new Attendance();
        attendance.setMemberId(memberId);
        attendance.setAttendanceDate(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
        attendanceRepository.save(attendance);
    }


    public void deleteAttendance(Long memberId, String date){
//        attendanceRepository.deleteAttendance(memberId,LocalDate.parse(date,DateTimeFormatter.ISO_DATE));
    }

    public List<AttendanceResponseDto> findAllAttendanceList(){
        return attendanceRepository.findAllAttendanceList();
    }

    public List<Attendance> findAllByMemberId(Long memberId){
        return attendanceRepository.findAllByMemberId(memberId);
    }

    public void deleteAttendance(Long attendanceId){
        attendanceRepository.deleteById(attendanceId);
    }



    @Transactional
    public void updateAttendance(AttendanceRequestDto dto) {
        if (dto.getAttendance()) {
            Attendance attendance = new Attendance();
            attendance.setMemberId(dto.getMemberId());
            attendance.setAttendanceDate(LocalDate.parse(dto.getAttendanceDate(), DateTimeFormatter.ISO_DATE));
            attendanceRepository.save(attendance);
        } else {
            attendanceRepository.deleteByMemberIdAndAttendanceDate(dto.getMemberId(),LocalDate.parse(dto.getAttendanceDate(),DateTimeFormatter.ISO_DATE));
        }

    }
}
