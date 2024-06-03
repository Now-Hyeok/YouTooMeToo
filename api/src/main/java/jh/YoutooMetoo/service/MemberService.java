package jh.YoutooMetoo.service;

import jakarta.transaction.Transactional;
import jh.YoutooMetoo.domain.Attendance;
import jh.YoutooMetoo.domain.Member;
import jh.YoutooMetoo.repository.AttendanceRepository;
import jh.YoutooMetoo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private final AttendanceRepository attendanceRepository;
    public Member registerMember(Member member){
        return memberRepository.save(member);
    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    public void updateMemberName(Long memberId, String name) {
        Member findMember = memberRepository.findById(memberId).orElseThrow();
        findMember.setMemberName(name);
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
        attendanceRepository.deleteAllByMemberId(memberId);
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

}
