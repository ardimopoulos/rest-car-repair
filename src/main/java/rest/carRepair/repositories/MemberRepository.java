package rest.carRepair.repositories;

import org.springframework.data.repository.CrudRepository;
import rest.carRepair.domain.Member;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findAll();
    Member findMemberById(long id) /*throws MemberNotFoundExceptpion*/;

}
