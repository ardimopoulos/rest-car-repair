package rest.carRepair.services;

import org.springframework.stereotype.Service;
import rest.carRepair.domain.Repair;

import java.util.List;

@Service
public interface RepairService {

    List<Repair> getRepairsByVehicle(Long memberId, Long vehicleId);

    Repair getRepairByVehicle(Long memberId, Long vehicleId, Long repairId);

    Repair saveRepair(Long memberId, Long vehicleId, Repair repair);

    Repair updateRepair(Long memberId, Long vehicleId, Long repairId, Repair repair);

    void deleteRepair(Long memberId, Long vehicleId, Long repairId);
}
