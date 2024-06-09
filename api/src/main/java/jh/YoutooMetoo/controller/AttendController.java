package jh.YoutooMetoo.controller;

import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.domain.AttendanceRequestDto;
import jh.YoutooMetoo.domain.AttendanceResponseDto;
import jh.YoutooMetoo.service.AttendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@Slf4j
public class AttendController {

    private final AttendService attendService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AttendanceResponseDto> getAllAttendanceList(){
        return attendService.findAllAttendanceList();

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void attendance(@RequestBody Long memberId) {

//        attendService.attendance(memberId);

    }

    @GetMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Attendance> getAttendanceByMemberId(@PathVariable String memberId){
        return attendService.findAllByMemberId(Long.getLong(memberId));

    }

    @PostMapping("updateAttendance")
    @ResponseStatus(HttpStatus.OK)
    public void updateAttendance(@RequestBody AttendanceRequestDto dto) {
        log.info("update Attendance request = {}", dto.toString());
        attendService.updateAttendance(dto);
    }
}
