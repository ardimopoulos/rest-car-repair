package rest.car_repair.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Repair;
import rest.car_repair.domain.Vehicle;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.repositories.RepairRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepairServiceImp implements RepairService {

    private RepairRepository repairRepository;
    private VehicleService vehicleService;

    @Override
    public List<Repair> getRepairsByVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, RepairNotFoundException, MemberNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId, vehicleId);
        List<Repair> repairs = vehicle.getRepairs();

        if(repairs.isEmpty()){
            throw new RepairNotFoundException("Repairs not found for the vehicle with id " + vehicleId);
        }

        return repairs;
    }

    @Override
    public Repair getRepairByVehicle(Long memberId, Long vehicleId, Long repairId) throws MemberNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, RepairNotReferredToVehicleException, RepairNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId,vehicleId);
        Optional<Repair> persistentRepair = repairRepository.findById(repairId);

        if(!persistentRepair.isPresent()){
            throw new RepairNotFoundException("Repair with id " +repairId+ " not foound");
        }

        Repair repair = persistentRepair.get();

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
        repairRepository.deleteById(repairId);
    }
}