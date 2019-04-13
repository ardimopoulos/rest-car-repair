package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import rest.car_repair.domain.Vehicle;

import java.time.LocalDateTime;

@Getter
@Setter
public class RepairDTO {

    private long repairId;
    private LocalDateTime repairDate;
    private int status;
    private String description;
    private boolean type;
    private double cost;
    private Vehicle vehicle;
}
