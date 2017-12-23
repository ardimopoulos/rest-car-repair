package rest.carRepair.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Repair{

    @Id
    @Column(name = "repair_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long repairId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime repairDate;

    @Column(nullable = false, length = 1)
    private int status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean type;

    @Column(nullable = false)
    private double cost;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    public Repair(){}

    public Repair(LocalDateTime repairDate, int status, String description, boolean type, double cost) {
        this.repairDate = repairDate;
        this.status = status;
        this.description = description;
        this.type = type;
        this.cost = cost;
    }


    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public LocalDateTime getrepairDate() {
        return repairDate;
    }

    public void setDate(LocalDateTime date) {
        this.repairDate = repairDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}