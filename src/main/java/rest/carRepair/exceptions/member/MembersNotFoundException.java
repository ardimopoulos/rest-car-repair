package rest.carRepair.exceptions.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MembersNotFoundException extends Exception {
    public MembersNotFoundException(String message) {
        super(message);
    }
}
