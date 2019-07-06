package rest.car_repair.exceptions.vehicle;

import lombok.Getter;

public class VehicleNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public VehicleNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
