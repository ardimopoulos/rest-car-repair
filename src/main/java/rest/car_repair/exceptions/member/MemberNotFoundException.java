package rest.car_repair.exceptions.member;

import lombok.Getter;

public class MemberNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public MemberNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
