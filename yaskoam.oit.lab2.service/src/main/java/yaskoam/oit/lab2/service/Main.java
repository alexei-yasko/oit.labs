package yaskoam.oit.lab2.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        TransportService service = context.getBean(TransportService.class);

        service.saveTransportations(TestDataGenerator.generateTestData());

        List<Car> cars = service.getCars();
        System.out.println(cars);

        List<Driver> drivers = service.getDrivers();
        System.out.println(drivers);

        List<Transportation> transportations = service.getTransportations();
        System.out.println(transportations);
    }
}