package jh.YoutooMetoo.controller;


import jh.YoutooMetoo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberRepository memberRepository;

    @GetMapping
    public ResponseEntity<?> getMemberList(){


        return new ResponseEntity<>(HttpStatus.OK);

    }
}
