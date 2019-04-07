package rest.car_repair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.car_repair.domain.Repair;

import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findAll();

    Repair findOne(Long repairId);

    Repair save(Repair repair);

    void delete(Long repairId);

}
