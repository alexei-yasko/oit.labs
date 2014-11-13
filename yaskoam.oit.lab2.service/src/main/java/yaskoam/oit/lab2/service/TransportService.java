package yaskoam.oit.lab2.service;

import java.util.List;

import yaskoam.oit.lab2.service.model.Transportation;

public interface TransportService {

    <T> List<T> getAll(Class<T> clazz);

    <T> T get(Class<T> clazz, int id);

    <T> void saveOrUpdate(T entity);

    <T> void saveOrUpdate(List<T> entities);

    <T> void remove(T entity);

    <T> void removeAll(List<T> entities);

    public double calculateCost(List<Transportation> transportations, double rate);
}