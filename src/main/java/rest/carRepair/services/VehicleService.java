package rest.carRepair.services;

import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleExistException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehiclesByMember(Long userId) throws VehicleNotFoundException, MemberNotFoundException;

    Vehicle getVehicleByMember(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException;

    Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws MemberNotFoundException, VehicleExistException;

    Vehicle updateVehicle(Long id, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException;

    void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException;
}
