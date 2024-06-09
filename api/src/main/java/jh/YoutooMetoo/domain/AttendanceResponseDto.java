package jh.YoutooMetoo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AttendanceResponseDto {

    private Long attendanceId;
    private LocalDate attendanceDate;
    private Long memberId;
    private String memberName;

}
