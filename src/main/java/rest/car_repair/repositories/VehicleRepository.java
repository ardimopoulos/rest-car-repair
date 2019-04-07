package rest.car_repair.repositories;

import org.springframework.data.repository.CrudRepository;
import rest.car_repair.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Vehicle save(Vehicle vehicle);

    void deleteByVehicleId(Long id);
}
