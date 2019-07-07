package rest.car_repair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.car_repair.domain.Repair;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {}
