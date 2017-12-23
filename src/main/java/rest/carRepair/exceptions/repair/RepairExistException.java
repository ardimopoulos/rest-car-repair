package rest.carRepair.exceptions.repair;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RepairExistException extends Exception {
    public RepairExistException(String message) {
        super(message);
    }
}
