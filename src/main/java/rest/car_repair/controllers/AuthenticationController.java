package rest.car_repair.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.responses.AuthenticationResponse;
import rest.car_repair.services.AuthenticationService;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@Validated
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> getToken(@RequestParam @Email String username, @RequestParam @NotNull String password) throws MemberNotFoundException {
        String token = authenticationService.getToken(username, password);
        AuthenticationResponse response = new AuthenticationResponse(
                LocalDateTime.now(),
                "success",
                token
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
