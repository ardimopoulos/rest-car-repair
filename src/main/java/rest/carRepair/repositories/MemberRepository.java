package rest.carRepair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.carRepair.domain.Member;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findAll();

    Member findOne(long id);

    Member save(Member member);

    void deleteByUserId(long id);


}
