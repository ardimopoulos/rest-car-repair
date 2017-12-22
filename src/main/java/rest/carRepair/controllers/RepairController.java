package rest.carRepair.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.carRepair.domain.Repair;

import java.util.List;

@RestController
public class RepairController {

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity<List<Repair>> getRepairs(@PathVariable Long memberId, @PathVariable Long vehicleId) {
        return null;
    }

    @GetMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity<Repair> getRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId){
        return null;
    }

    @PostMapping("/members/{memberId}/vehicles/{vehicleId}/repairs")
    public ResponseEntity createRepair(@PathVariable Long memberId, @PathVariable Long vehicleId) {
        return null;
    }

    @PutMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public ResponseEntity updateRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId){
        return null;
    }

    @DeleteMapping("/members/{memberId}/vehicles/{vehicleId}/repairs/{repairId}")
    public void deleteRepair(@PathVariable Long memberId,@PathVariable Long vehicleId,@PathVariable Long repairId){

    }


}
