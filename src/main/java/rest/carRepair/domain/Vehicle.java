package rest.carRepair.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vehicle {

    @Id
    @Column(name = "vehicle_id", nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long vehicleId;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date year;

    @Column(nullable = false)
    private String color;

    /*@JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_Id", referencedColumnName = "user_id")
    private Member member;*/

    //@JsonManagedReference
   /* @OneToMany(mappedBy = "vehicle", targetEntity = Repair.class, cascade = CascadeType.ALL)
    protected List<Repair> repairs;*/

    public Vehicle(){}

    public Vehicle(String plate, String brand, String model, Date year, String color) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Date getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    /*public Member getMember() {
        return member;
    }*/

   /*public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }*/
}
