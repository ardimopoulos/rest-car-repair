package rest.car_repair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.car_repair.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    List<Member> findAll();

    Optional<Member> findByEmail(String email);
}
