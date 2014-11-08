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

    @Column(name = "model")
    private String model;

    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    public int getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String name) {
        this.model = name;
    }

    @Override
    public String toString() {
        return "Car{" +
            "code='" + code + '\'' +
            ", model='" + model + '\'' +
            '}';
    }
}