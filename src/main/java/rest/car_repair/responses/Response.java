package rest.car_repair.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public abstract class Response {

    protected LocalDateTime timestamp;
    protected boolean success;
    protected String status;
}