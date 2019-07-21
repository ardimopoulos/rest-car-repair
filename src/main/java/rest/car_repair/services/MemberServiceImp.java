package rest.car_repair.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Member;
import rest.car_repair.exceptions.member.MemberExistException;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.repositories.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService{

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Member> getMembers() throws MemberNotFoundException{
        List<Member> allMembers = memberRepository.findAll();

        if (allMembers.isEmpty()) {
            throw new MemberNotFoundException("Members not found");
        }

        return allMembers;
    }

    @Override
    public Member getMemberById(long id) throws MemberNotFoundException {
        Optional<Member> persistentMember = memberRepository.findById(id);

        if (!persistentMember.isPresent()) {
            throw new MemberNotFoundException("Member not found");
        }

        return persistentMember.get();
    }

    @Override
    public Member saveMember(Member member) throws MemberExistException{
        try {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            return memberRepository.save(member);
        }catch (Exception e){
            throw new MemberExistException("Member already exist with same VAT and/or email");
        }
    }

    @Override
    public Member updateMember(Long memberId, Member member)throws MemberNotFoundException {
        getMemberById(memberId);
        member.setUserId(memberId);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void deleteMemberById(long id) throws MemberNotFoundException{
        getMemberById(id);
        memberRepository.deleteById(id);
    }
}
