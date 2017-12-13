package rest.carRepair.services;

import rest.carRepair.domain.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    Member getMemberById(long id);

    Member saveMember(Member member);
}
