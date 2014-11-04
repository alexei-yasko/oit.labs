package yaskoam.oit.lab2.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Car")
public class Car {

    @Id
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    public Car() {
    }

    public Car(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}