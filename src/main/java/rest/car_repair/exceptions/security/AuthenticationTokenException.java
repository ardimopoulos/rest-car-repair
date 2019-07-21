package rest.car_repair.exceptions.security;

import lombok.Getter;

public class AuthenticationTokenException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public AuthenticationTokenException(String message) {
        super(message);
        this.message = message;
    }
}
