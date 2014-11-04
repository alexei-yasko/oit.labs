package yaskoam.oit.lab2.service;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;

public interface TransportService {

    void saveCar(Car car);

    Car getCar(String code);

    List<Car> getCars();
}