package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import rest.car_repair.domain.Member;
import rest.car_repair.domain.Repair;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class VehicleDTO {

    private long vehicleId;
    private String plate;
    private String brand;
    private String model;
    private Date year;
    private String color;
    private Member member;
    private List<Repair> repairs;
}
