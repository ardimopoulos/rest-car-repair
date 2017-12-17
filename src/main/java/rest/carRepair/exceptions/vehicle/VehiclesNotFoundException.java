package rest.carRepair.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehiclesNotFoundException extends Exception {
    public VehiclesNotFoundException(String message) {
        super(message);
    }
}
