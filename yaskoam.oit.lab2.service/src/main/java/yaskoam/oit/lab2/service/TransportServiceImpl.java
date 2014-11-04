package yaskoam.oit.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveCar(Car car) {
        hibernateTemplate.save(car);
    }

    @Transactional(readOnly = true)
    @Override
    public Car getCar(String code) {
        return hibernateTemplate.get(Car.class, code);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> getCars() {
        return hibernateTemplate.loadAll(Car.class);
    }
}