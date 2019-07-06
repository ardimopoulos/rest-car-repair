package rest.car_repair.exceptions.vehicle;

import lombok.Getter;

public class VehicleExistException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public VehicleExistException(String message) {
        super(message);
        this.message = message;
    }
}
