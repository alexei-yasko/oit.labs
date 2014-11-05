package yaskoam.oit.lab2.service.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Transportation")
public class Transportation {

    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_code")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_code")
    private Car car;

    @Column(name = "weight")
    private double weight;

    @Column(name = "length")
    private double length;

    public Transportation() {
    }

    public Transportation(String number, Date date, Driver driver, Car car, double weight, double length) {
        this.number = number;
        this.date = date;
        this.driver = driver;
        this.car = car;
        this.weight = weight;
        this.length = length;
    }

    public String getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Transportation{" +
            "number='" + number + '\'' +
            ", date=" + date +
            ", driver=" + driver +
            ", car=" + car +
            ", weight=" + weight +
            ", length=" + length +
            '}';
    }
}