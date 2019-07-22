package rest.car_repair.responses;

import lombok.Getter;

import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;

@Getter
public class ErrorResponse extends Response{

    private String error;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, String status, String error, String message) {
        super(timestamp, FALSE, status);
        this.error = error;
        this.message = message;
    }
}
