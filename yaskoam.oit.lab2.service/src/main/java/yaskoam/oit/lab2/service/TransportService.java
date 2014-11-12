package yaskoam.oit.lab2.service;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public interface TransportService {

    List<Car> getCars();

    Car getCar(int code);

    void saveOrUpdateCar(Car car);

    void removeCars(List<Car> cars);

    List<Driver> getDrivers();

    Driver getDriver(int code);

    void saveOrUpdateDriver(Driver driver);

    void removeDrivers(List<Driver> drivers);

    List<Transportation> getTransportations();

    public void saveOrUpdateTransportation(Transportation transportation);

    public void saveTransportations(List<Transportation> transportations);

    public double calculateCost(List<Transportation> transportations, double rate);

    void removeTransportations(List<Transportation> transportations);
}