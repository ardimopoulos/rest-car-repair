package rest.carRepair.repositories;

import org.springframework.data.repository.CrudRepository;
import rest.carRepair.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Vehicle save(Vehicle vehicle);

    void deleteByVehicleId(Long id);
}
