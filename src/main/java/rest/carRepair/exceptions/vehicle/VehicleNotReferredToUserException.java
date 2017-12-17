package rest.carRepair.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleNotReferredToUserException extends Exception {
    public VehicleNotReferredToUserException(String message) {
        super(message);
    }
}
