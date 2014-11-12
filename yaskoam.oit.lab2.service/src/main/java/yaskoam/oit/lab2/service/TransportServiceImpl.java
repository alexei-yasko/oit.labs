package yaskoam.oit.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(readOnly = true)
    @Override
    public List<Car> getCars() {
        return hibernateTemplate.loadAll(Car.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Car getCar(int code) {
        return hibernateTemplate.get(Car.class, code);
    }

    @Transactional
    @Override
    public void saveOrUpdateCar(Car car) {
        hibernateTemplate.saveOrUpdate(car);
    }

    @Transactional
    @Override
    public void removeCars(List<Car> cars) {
        hibernateTemplate.deleteAll(cars);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Driver> getDrivers() {
        return hibernateTemplate.loadAll(Driver.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Driver getDriver(int code) {
        return hibernateTemplate.get(Driver.class, code);
    }

    @Transactional
    @Override
    public void saveOrUpdateDriver(Driver driver) {
        hibernateTemplate.saveOrUpdate(driver);
    }

    @Transactional
    @Override
    public void removeDrivers(List<Driver> drivers) {
        hibernateTemplate.deleteAll(drivers);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transportation> getTransportations() {
        return hibernateTemplate.loadAll(Transportation.class);
    }

    @Transactional
    @Override
    public void saveOrUpdateTransportation(Transportation transportation) {
        hibernateTemplate.saveOrUpdate(transportation);
    }

    @Transactional
    @Override
    public void saveTransportations(List<Transportation> transportations) {
        transportations.stream().forEach(this::saveOrUpdateTransportation);
    }

    @Transactional(readOnly = true)
    @Override
    public double calculateCost(List<Transportation> transportations, double rate) {
        List<Integer> numbers =
            transportations.stream().map(Transportation::getNumber).collect(Collectors.toCollection(ArrayList::new));

        List result = hibernateTemplate.findByNamedParam(
            "SELECT SUM(t.weight * t.length * :rate) FROM Transportation t WHERE t.number IN (:numbers)",
            new String[]{"rate", "numbers"}, new Object[]{rate, numbers});

        return result.get(0) != null ? (double) result.get(0) : 0;
    }

    @Transactional
    @Override
    public void removeTransportations(List<Transportation> transportations) {
        hibernateTemplate.deleteAll(transportations);
    }
}