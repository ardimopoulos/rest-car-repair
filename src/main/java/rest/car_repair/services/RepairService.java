package rest.car_repair.services;

import rest.car_repair.domain.Repair;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.repair.RepairExistException;
import rest.car_repair.exceptions.repair.RepairNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;

import java.util.List;

public interface RepairService {

    List<Repair> getRepairsByVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, RepairNotFoundException, MemberNotFoundException;

    Repair getRepairByVehicle(Long memberId, Long vehicleId, Long repairId) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, RepairNotReferredToVehicleException, RepairNotFoundException;

    Repair saveRepair(Long memberId, Long vehicleId, Repair repair) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException;

    Repair updateRepair(Long memberId, Long vehicleId, Long repairId, Repair repair) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException, RepairExistException;

    void deleteRepair(Long memberId, Long vehicleId, Long repairId) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException;
}
