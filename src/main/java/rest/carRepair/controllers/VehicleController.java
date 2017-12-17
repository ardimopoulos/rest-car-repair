package rest.carRepair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleExistException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.carRepair.exceptions.vehicle.VehiclesNotFoundException;
import rest.carRepair.services.VehicleService;

import java.net.URI;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/members/{memberId}/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable Long memberId) throws VehiclesNotFoundException, MemberNotFoundException {
        List<Vehicle> allUserVehicles = vehicleService.getAllMemberVehicles(memberId);
        return new ResponseEntity(allUserVehicles, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException {
        Vehicle vehicle = vehicleService.getMemberVehicle(memberId, vehicleId);
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }

    @PostMapping("members/{memberId}/vehicles")
    public ResponseEntity createVehicle(@PathVariable Long memberId, @RequestBody Vehicle vehicle) throws VehicleExistException, MemberNotFoundException {
        Vehicle newVehicle = vehicleService.saveVehicle(memberId, vehicle);

        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{vehicleId}")
                            .buildAndExpand(newVehicle.getVehicleId())
                            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity updateVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId, @RequestBody Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException{
        vehicleService.updateVehicle(memberId, vehicleId, vehicle);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable Long memberId, @PathVariable Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException {
        vehicleService.deleteVehicle(memberId, vehicleId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
