package rest.carRepair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.carRepair.domain.Repair;
import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.repair.RepairNotFoundException;
import rest.carRepair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.carRepair.repositories.RepairRepository;

import java.util.List;

@Service
public class RepairServiceImp implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public List<Repair> getRepairsByVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, RepairNotFoundException, MemberNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId, vehicleId);
        List<Repair> repairs = vehicle.getRepairs();
        if(repairs.size() == 0){
            throw new RepairNotFoundException("Repairs not found for the vehicle with id " + vehicleId);
        }
        return repairs;
    }

    @Override
    public Repair getRepairByVehicle(Long memberId, Long vehicleId, Long repairId) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, RepairNotReferredToVehicleException, RepairNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId,vehicleId);
        Repair repair = repairRepository.findOne(repairId);
        if(repair == null){
            throw new RepairNotFoundException("Repair with id " +repairId+ " not foound");
        }
        if(repair.getVehicle().getVehicleId() != vehicle.getVehicleId()){
            throw new RepairNotReferredToVehicleException("Repair with id " + repairId + " is not referred to vehicle " + vehicleId);
        }
        return repair;
    }

    @Override
    public Repair saveRepair(Long memberId, Long vehicleId, Repair repair) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId, vehicleId);
        repair.setVehicle(vehicle);
        return repairRepository.save(repair);
    }

    @Override
    public Repair updateRepair(Long memberId, Long vehicleId, Long repairId, Repair repair) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {
        repair.setRepairId(repairId);
        return saveRepair(memberId, vehicleId, repair);
    }

    @Override
    public void deleteRepair(Long memberId, Long vehicleId, Long repairId) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {
        getRepairByVehicle(memberId, vehicleId, repairId);
        repairRepository.delete(repairId);
    }
}