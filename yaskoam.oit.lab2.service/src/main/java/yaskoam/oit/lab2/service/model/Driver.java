package yaskoam.oit.lab2.service.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Driver")
public class Driver {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    public Driver() {
    }

    public Driver(String name) {
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
        return "Driver{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}