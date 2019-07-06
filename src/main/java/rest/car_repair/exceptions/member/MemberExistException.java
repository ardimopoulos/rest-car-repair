package rest.car_repair.exceptions.member;


import lombok.Getter;

public class MemberExistException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public MemberExistException(String message){
        super(message);
        this.message = message;
    }
}
