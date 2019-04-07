package rest.car_repair.exceptions.member;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MemberExistException extends Exception {
    public MemberExistException(String message){
        super(message);
    }
}
