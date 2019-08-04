package rest.car_repair.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "vehicle_id", nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long vehicleId;

    @Column(nullable = false, unique = true, length = 8)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}")
    private String plate;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private LocalDate year;

    @Column(nullable = false)
    private String color;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "vehicle", targetEntity = Repair.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Repair> repairs;

    public Vehicle(String plate, String brand, String model, LocalDate year, String color) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }
}
