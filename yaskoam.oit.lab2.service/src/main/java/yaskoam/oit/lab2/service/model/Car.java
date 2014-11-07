package yaskoam.oit.lab2.service.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Car")
public class Car {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}