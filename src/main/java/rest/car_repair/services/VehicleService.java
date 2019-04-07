package rest.car_repair.services;

import rest.car_repair.domain.Vehicle;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleExistException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehiclesByMember(Long userId) throws VehicleNotFoundException, MemberNotFoundException;

    Vehicle getVehicleByMember(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException;

    Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws MemberNotFoundException, VehicleExistException;

    Vehicle updateVehicle(Long id, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException;

    void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException;
}
