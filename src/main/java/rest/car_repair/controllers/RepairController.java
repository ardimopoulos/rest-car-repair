package rest.car_repair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.car_repair.domain.Repair;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.repair.RepairExistException;
import rest.car_repair.exceptions.repair.RepairNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.services.RepairService;

import java.net.URI;
import java.util.List;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity<List<Repair>> getRepairs(@PathVariable Long memberId, @PathVariable Long vehicleId) throws RepairNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, MemberNotFoundException {
        List<Repair> repairs = repairService.getRepairsByVehicle(memberId, vehicleId);
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity<Repair> getRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {
        Repair repair = repairService.getRepairByVehicle(memberId,vehicleId,repairId);
        return new ResponseEntity<>(repair,HttpStatus.OK);
    }

    @PostMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity createRepair(@PathVariable Long memberId, @PathVariable Long vehicleId, @RequestBody Repair repair) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        Repair newRepair = repairService.saveRepair(memberId, vehicleId, repair);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{repairId}")
                .buildAndExpand(newRepair.getRepairId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity updateRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId, @RequestBody Repair repair) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException, RepairExistException {
        repairService.updateRepair(memberId, vehicleId, repairId, repair);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity deleteRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId) throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {
        repairService.deleteRepair(memberId, vehicleId, repairId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
