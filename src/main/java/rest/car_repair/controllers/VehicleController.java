package rest.car_repair.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.car_repair.domain.Vehicle;
import rest.car_repair.dto.VehicleDTO;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleExistException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.services.VehicleService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@Validated
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/members/{memberId}/vehicles")
    public ResponseEntity<List<VehicleDTO>> getVehicles(@PathVariable @Min(1) Long memberId) throws VehicleNotFoundException, MemberNotFoundException {
        List<Vehicle> allVehicles = vehicleService.getAllVehiclesByMember(memberId);

        Type listType = new TypeToken<List<VehicleDTO>>() {}.getType();
        List<VehicleDTO> allVehiclesDTO = modelMapper.map(allVehicles, listType);

        return new ResponseEntity<>(allVehiclesDTO, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId, vehicleId);
        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);

        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @PostMapping("members/{memberId}/vehicles")
    public ResponseEntity createVehicle(@PathVariable @Min(1) Long memberId, @Valid @RequestBody VehicleDTO vehicleDTO) throws MemberNotFoundException, VehicleExistException {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle newVehicle = vehicleService.saveVehicle(memberId, vehicle);

        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{vehicleId}")
                            .buildAndExpand(newVehicle.getVehicleId())
                            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity updateVehicle(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId, @Valid @RequestBody VehicleDTO vehicleDTO) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleService.updateVehicle(memberId, vehicleId, vehicle);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable @Min(1) Long memberId, @PathVariable @Min(1) Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        vehicleService.deleteVehicle(memberId, vehicleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
