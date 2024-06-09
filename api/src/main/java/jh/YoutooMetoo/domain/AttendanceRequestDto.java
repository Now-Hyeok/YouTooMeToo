package jh.YoutooMetoo.domain;

import lombok.Data;

@Data
public class AttendanceRequestDto {

    private Long memberId;
    private String attendanceDate;
    private Boolean attendance;
}
