package yaskoam.oit.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import yaskoam.oit.lab2.service.model.Transportation;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(readOnly = true)
    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return hibernateTemplate.loadAll(clazz);
    }

    @Transactional(readOnly = true)
    @Override
    public <T> T get(Class<T> clazz, int id) {
        return hibernateTemplate.get(clazz, id);
    }

    @Transactional
    @Override
    public <T> void saveOrUpdate(T entity) {
        hibernateTemplate.save(entity);
    }

    @Transactional
    @Override
    public <T> void saveOrUpdate(List<T> entities) {
        entities.stream().forEach(this::saveOrUpdate);
    }

    @Transactional
    @Override
    public <T> void remove(T entity) {
        hibernateTemplate.delete(entity);
    }

    @Transactional
    @Override
    public <T> void removeAll(List<T> entities) {
        hibernateTemplate.deleteAll(entities);
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
}