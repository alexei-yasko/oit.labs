package yaskoam.oit.lab2.service.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity(name = "Transportation")
public class Transportation {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "number")
    private int number;

    @Column(name = "date")
    private LocalDate date;

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

    public Transportation(LocalDate date, Driver driver, Car car, double weight, double length) {
        this.date = date;
        this.driver = driver;
        this.car = car;
        this.weight = weight;
        this.length = length;
    }

    public int getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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