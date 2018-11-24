package pl.bgolc.tachograph.driver;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    @Column(name="user_id")
    private Integer userId;

    @NotBlank(message = "Pole imie nie może byc puste")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "Pole nazwisko nie może byc puste")
    @Column(name="surname")
    private String surname;

    /*
    * Getters
    * */
    public Integer getDriverId() {
        return driverId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    /*
    * Setters
    * */
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
