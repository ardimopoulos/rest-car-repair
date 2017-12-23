package rest.carRepair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.carRepair.domain.Member;
import rest.carRepair.exceptions.member.MemberExistException;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.repositories.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getMembers() throws MemberNotFoundException{
        List<Member> allMembers = memberRepository.findAll();
        if(allMembers.size() == 0){
            throw new MemberNotFoundException("Members not found");
        }
        return allMembers;
    }

    @Override
    public Member getMemberById(long id) throws MemberNotFoundException {
        Member member = memberRepository.findOne(id);
        if(member == null){
            throw new MemberNotFoundException("Member not found");
        }
        return member;
    }

    @Override
    public Member saveMember(Member member) throws MemberExistException{
        try {
            Member newMember = memberRepository.save(member);
            return newMember;
        }catch (Exception e){
            throw new MemberExistException("Member already exist with same VAT and/or email");
        }
    }

    @Override
    public Member updateMember(Long memberId, Member member)throws MemberNotFoundException {
        getMemberById(memberId);
        member.setUserId(memberId);
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void deleteMemberById(long id) throws MemberNotFoundException{
        getMemberById(id);
        memberRepository.deleteByUserId(id);
    }
}
