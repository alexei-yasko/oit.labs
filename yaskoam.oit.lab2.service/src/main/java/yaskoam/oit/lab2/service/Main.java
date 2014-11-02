package yaskoam.oit.lab2.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by alex on 11/2/14.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        TransportService service = context.getBean("transportService", TransportService.class);

        Car car = new Car("12312");
        service.saveCar(car);

        List<Car> cars = service.getCars();
        System.out.println(cars);

        Car car1 = service.getCar(car.getId());
        System.out.println(car1);
    }
}