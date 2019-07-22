package rest.car_repair.responses;

import lombok.Getter;

import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;

@Getter
public class AuthenticationResponse extends Response {

    private String token;

    public AuthenticationResponse(LocalDateTime timestamp, String status, String token) {
        super(timestamp, TRUE, status);
        this.token = token;
    }
}
