package jh.YoutooMetoo.service;

import jh.YoutooMetoo.domain.Member;
import jh.YoutooMetoo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository repository;
    public Member registerMember(Member member){
        return repository.save(member);
    }

    public List<Member> findAllMember(){
        return repository.findAll();
    }

    public void updateMemberName(Long memberId, String name) {
        Member findMember = repository.findById(memberId).orElseThrow();
        findMember.setMemberName(name);
    }

    public void deleteMemberById(Long memberId) {
        repository.deleteById(memberId);

    }


}
