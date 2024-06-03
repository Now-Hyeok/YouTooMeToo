package jh.YoutooMetoo.controller;

import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.service.AttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Attendance> getAllAttendance(){
        return attendService.findAll();

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void attendance(@RequestBody Long memberId) {

        attendService.attendance(memberId);

    }

    @GetMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Attendance> getAttendanceByMemberId(@PathVariable String memberId){
        return attendService.findAllByMemberId(Long.getLong(memberId));

    }

}
