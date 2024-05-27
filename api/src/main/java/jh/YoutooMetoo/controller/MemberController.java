package jh.YoutooMetoo.controller;


import jh.YoutooMetoo.domain.Member;
import jh.YoutooMetoo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<?> getMemberList(){
        return new ResponseEntity<>(memberService.findAllMember(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveMember(@RequestBody String memberName) {
        Member newMember = new Member();
        newMember.setMemberName(memberName);
        return new ResponseEntity<>(memberService.registerMember(newMember), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteMember(@RequestBody long memberId){
        memberService.deleteMemberById(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
