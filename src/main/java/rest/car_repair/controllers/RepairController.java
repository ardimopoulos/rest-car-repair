package rest.car_repair.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.car_repair.domain.Repair;
import rest.car_repair.dto.RepairDTO;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.repair.RepairExistException;
import rest.car_repair.exceptions.repair.RepairNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.services.RepairService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
public class RepairController {

    private RepairService repairService;
    private ModelMapper modelMapper;

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity<List<RepairDTO>> getRepairs(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId)
            throws RepairNotFoundException, VehicleNotReferredToUserException, VehicleNotFoundException, MemberNotFoundException {

        List<Repair> repairs = repairService.getRepairsByVehicle(memberId, vehicleId);

        Type listType = new TypeToken<List<RepairDTO>>() {}.getType();
        List<RepairDTO> repairsDTO = modelMapper.map(repairs, listType);

        return new ResponseEntity<>(repairsDTO, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity<RepairDTO> getRepair(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId, @PathVariable @Min(1) Long repairId)
            throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {

        Repair repair = repairService.getRepairByVehicle(memberId, vehicleId, repairId);
        RepairDTO repairDTO = modelMapper.map(repair, RepairDTO.class);

        return new ResponseEntity<>(repairDTO,HttpStatus.OK);
    }

    @PostMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity createRepair(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId, @Valid @RequestBody RepairDTO repairDTO)
            throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {

        Repair repair = modelMapper.map(repairDTO, Repair.class);
        Repair newRepair = repairService.saveRepair(memberId, vehicleId, repair);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{repairId}")
                .buildAndExpand(newRepair.getRepairId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity updateRepair(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId, @PathVariable @Min(1) Long repairId, @Valid @RequestBody RepairDTO repairDTO)
            throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException, RepairExistException {

        Repair repair = modelMapper.map(repairDTO, Repair.class);
        repairService.updateRepair(memberId, vehicleId, repairId, repair);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity deleteRepair(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId, @PathVariable @Min(1) Long repairId)
            throws RepairNotReferredToVehicleException, VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException, RepairNotFoundException {

        repairService.deleteRepair(memberId, vehicleId, repairId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
