package yaskoam.oit.lab2.service;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public interface TransportService {

    List<Car> getCars();

    Car getCar(String code);

    void saveCar(Car car);

    List<Driver> getDrivers();

    Driver getDriver(String code);

    List<Transportation> getTransportations();

    public void saveTransportation(Transportation transportation);

    public void saveTransportations(List<Transportation> transportations);

    public void updateTransportation(Transportation transportations);
}