package yaskoam.oit.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Car getCar(String code) {
        return hibernateTemplate.get(Car.class, code);
    }

    @Transactional
    @Override
    public void saveCar(Car car) {
        hibernateTemplate.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> getDrivers() {
        return hibernateTemplate.loadAll(Driver.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transportation> getTransportations() {
        return hibernateTemplate.loadAll(Transportation.class);
    }

    @Transactional
    @Override
    public void saveTransportation(Transportation transportation) {
        hibernateTemplate.save(transportation);
    }

    @Transactional
    @Override
    public void saveTransportations(List<Transportation> transportations) {
        transportations.stream().forEach(this::saveTransportation);
    }
}