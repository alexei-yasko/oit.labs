package yaskoam.oit.lab2.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Driver")
public class Driver {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public Driver() {
    }

    public Driver(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Driver{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}