package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import rest.car_repair.domain.Member;
import rest.car_repair.domain.Repair;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VehicleDTO {

    @NotNull
    private long vehicleId;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}-[0-9]{3}")
    private String plate;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate year;

    @NotNull
    private String color;

    private Member member;

    private List<Repair> repairs;
}
