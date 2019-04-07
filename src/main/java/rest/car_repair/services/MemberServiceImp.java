package rest.car_repair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Member;
import rest.car_repair.exceptions.member.MemberExistException;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.repositories.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getMembers() throws MemberNotFoundException{
        List<Member> allMembers = memberRepository.findAll();

        if(allMembers.isEmpty()){
            throw new MemberNotFoundException("Members not found");
        }

        return allMembers;
    }

    @Override
    public Member getMemberById(long id) throws MemberNotFoundException {
        Member member = memberRepository.findOne(id);

        if(isNull(member)){
            throw new MemberNotFoundException("Member not found");
        }
        return member;
    }

    @Override
    public Member saveMember(Member member) throws MemberExistException{
        try {
            return memberRepository.save(member);
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
