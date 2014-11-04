package yaskoam.oit.lab2.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import yaskoam.oit.lab2.service.model.Car;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        TransportService service = context.getBean(TransportService.class);

        Car car = new Car("1", "vw");
        service.saveCar(car);

        List<Car> cars = service.getCars();
        System.out.println(cars);

        Car car1 = service.getCar(car.getCode());
        System.out.println(car1);
    }
}