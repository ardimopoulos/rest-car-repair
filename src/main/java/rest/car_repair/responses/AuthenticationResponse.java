package rest.car_repair.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private LocalDateTime timestamp;
    private String status;
    private String token;
}
