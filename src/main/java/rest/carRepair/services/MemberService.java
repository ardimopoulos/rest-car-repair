package rest.carRepair.services;

import rest.carRepair.domain.Member;
import rest.carRepair.exceptions.member.MemberExistException;
import rest.carRepair.exceptions.member.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    List<Member> getMembers() throws MemberNotFoundException;

    Member getMemberById(long id) throws MemberNotFoundException;

    Member saveMember(Member member) throws MemberExistException;

    Member updateMember(Long Id, Member member) throws MemberNotFoundException;

    void deleteMemberById(long id) throws MemberNotFoundException;
}
