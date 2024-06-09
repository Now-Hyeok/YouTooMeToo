package jh.YoutooMetoo.repository;

import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.domain.AttendanceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    List<Attendance> findAllByMemberId(Long memberId);
    void deleteAllByMemberId(Long memberId);

    void deleteByMemberIdAndAttendanceDate(Long memberId, LocalDate date);

    @Query("SELECT new jh.YoutooMetoo.domain.AttendanceResponseDto(a.attendanceId, a.attendanceDate, a.memberId, m.memberName) " +
            "FROM tb_attendance a JOIN tb_member m ON a.memberId = m.memberId")
    List<AttendanceResponseDto> findAllAttendanceList();

}
