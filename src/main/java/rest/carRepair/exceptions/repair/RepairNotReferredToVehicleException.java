package rest.carRepair.exceptions.repair;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepairNotReferredToVehicleException extends Exception {
    public RepairNotReferredToVehicleException(String message) {
        super(message);
    }
}
