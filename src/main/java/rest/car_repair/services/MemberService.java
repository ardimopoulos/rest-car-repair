package rest.car_repair.services;

import rest.car_repair.domain.Member;
import rest.car_repair.exceptions.member.MemberExistException;
import rest.car_repair.exceptions.member.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    List<Member> getMembers() throws MemberNotFoundException;

    Member getMemberById(long id) throws MemberNotFoundException;

    Member saveMember(Member member) throws MemberExistException;

    Member updateMember(Long id, Member member) throws MemberNotFoundException;

    void deleteMemberById(long id) throws MemberNotFoundException;
}
