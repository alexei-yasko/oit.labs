package yaskoam.oit.lab2.service;

import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 11/2/14.
 */
public class TransportServiceImpl implements TransportService {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        hibernateTemplate.save(car);
    }

    @Override
    @Transactional
    public Car getCar(String id) {
        return hibernateTemplate.get(Car.class, id);
    }

    @Override
    @Transactional
    public List<Car> getCars() {
        return hibernateTemplate.loadAll(Car.class);
    }
}