package jh.YoutooMetoo.controller;


import jh.YoutooMetoo.domain.Member;
import jh.YoutooMetoo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getMemberList(){
        return memberService.findAllMember();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Member saveMember(@RequestBody Member newMember) {
        log.info("save member = {}", newMember.toString());
        return memberService.registerMember(newMember);
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteMembers(@RequestBody List<Member> members){
        members.forEach(member -> memberService.deleteMemberById(member.getMemberId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
