package rest.carRepair.services;

import rest.carRepair.domain.Repair;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.repair.RepairExistException;
import rest.carRepair.exceptions.repair.RepairNotFoundException;
import rest.carRepair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;

import java.util.List;

public interface RepairService {

    List<Repair> getRepairsByVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, RepairNotFoundException, MemberNotFoundException;

    Repair getRepairByVehicle(Long memberId, Long vehicleId, Long repairId) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, RepairNotReferredToVehicleException, RepairNotFoundException;

    Repair saveRepair(Long memberId, Long vehicleId, Repair repair) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException;

    Repair updateRepair(Long memberId, Long vehicleId, Long repairId, Repair repair) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException, RepairExistException;

    void deleteRepair(Long memberId, Long vehicleId, Long repairId) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException;
}
