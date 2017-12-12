package rest.carRepair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.carRepair.domain.Member;
import rest.carRepair.repositories.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getMembers() {
        List<Member> allMembers = memberRepository.findAll();
        if(allMembers == null){
            return null;
        }
        return allMembers;
    }

    @Override
    public Member getMemberById(long id) {
        Member member = memberRepository.findOne(id);
        if(member == null){
            return null;
        }
        return member;
    }
}
