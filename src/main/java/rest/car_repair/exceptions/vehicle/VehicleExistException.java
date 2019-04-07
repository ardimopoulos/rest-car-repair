package rest.car_repair.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VehicleExistException extends Exception {
    public VehicleExistException(String message) {
        super(message);
    }
}
