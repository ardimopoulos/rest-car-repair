package rest.car_repair.exceptions.repair;

import lombok.Getter;

public class RepairNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public RepairNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
