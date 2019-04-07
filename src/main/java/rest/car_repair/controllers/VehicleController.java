package rest.car_repair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.car_repair.domain.Vehicle;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleExistException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.services.VehicleService;

import java.net.URI;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/members/{memberId}/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable Long memberId) throws VehicleNotFoundException, MemberNotFoundException {
        List<Vehicle> allVehicles = vehicleService.getAllVehiclesByMember(memberId);
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        Vehicle vehicle = vehicleService.getVehicleByMember(memberId, vehicleId);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("members/{memberId}/vehicles")
    public ResponseEntity createVehicle(@PathVariable Long memberId, @RequestBody Vehicle vehicle) throws MemberNotFoundException, VehicleExistException {
        Vehicle newVehicle = vehicleService.saveVehicle(memberId, vehicle);

        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{vehicleId}")
                            .buildAndExpand(newVehicle.getVehicleId())
                            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity updateVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId, @RequestBody Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException {
        vehicleService.updateVehicle(memberId, vehicleId, vehicle);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        vehicleService.deleteVehicle(memberId, vehicleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}