package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import rest.car_repair.domain.User;
import rest.car_repair.domain.Vehicle;

import java.util.List;

@Getter
@Setter
public class MemberDTO extends User{

    private String vat;
    private String firstname;
    private String lastname;
    private String address;
    private User user;
    private List<Vehicle> vehicles;
}
