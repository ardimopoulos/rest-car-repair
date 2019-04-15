package rest.car_repair.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import rest.car_repair.domain.Vehicle;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class RepairDTO {

    @NotNull
    private long repairId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime repairDate;

    @NotNull
    @Max(1)
    private int status;

    @NotNull
    private String description;

    @NotNull
    private boolean type;

    @NotNull
    private double cost;

    private Vehicle vehicle;
}
