package rest.car_repair.exceptions.repair;

import lombok.Getter;

public class RepairNotReferredToVehicleException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public RepairNotReferredToVehicleException(String message) {
        super(message);
        this.message = message;
    }
}
