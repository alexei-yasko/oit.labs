package yaskoam.oit.lab2.service;

import java.util.List;

/**
 * Created by alex on 11/2/14.
 */
public interface TransportService {

    void saveCar(Car car);

    Car getCar(String id);

    List<Car> getCars();
}