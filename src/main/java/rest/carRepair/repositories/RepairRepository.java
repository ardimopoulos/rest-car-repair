package rest.carRepair.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.carRepair.domain.Repair;

import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findAll();

    Repair findOne(Long repairId);

    Repair save(Repair repair);

    void delete(Long repairId);

}
