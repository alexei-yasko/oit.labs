package yaskoam.oit.lab2.service.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity(name = "Car")
public class Car implements Serializable {

    private static final int serialVersionUID = -1;

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "code")
    private int code;

    @Column(name = "model")
    private String model;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    public Car() {
    }

    public Car(String model, byte[] photo) {
        this.model = model;
        this.photo = photo;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Car{" +
            "code='" + code + '\'' +
            ", model='" + model + '\'' +
            '}';
    }
}