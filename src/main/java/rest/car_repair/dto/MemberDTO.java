package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import rest.car_repair.domain.User;
import rest.car_repair.domain.Vehicle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class MemberDTO extends User{

    @NotNull
    @Size(min = 9, max = 9)
    private String vat;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String address;

    private User user;

    private List<Vehicle> vehicles;
}
