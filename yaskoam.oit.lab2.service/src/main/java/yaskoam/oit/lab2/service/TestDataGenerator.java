package yaskoam.oit.lab2.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public class TestDataGenerator {

    public static List<Transportation> generateTestData() {
        Car car1 = new Car("audi", loadTestImage("audi.png"));
        Car car2 = new Car("bmw", loadTestImage("bmw.png"));
        Car car3 = new Car("ford", loadTestImage("ford.png"));
        Car car4 = new Car("honda", loadTestImage("honda.png"));
        Car car5 = new Car("volkswagen", loadTestImage("volkswagen.png"));
        Car car6 = new Car("opel", loadTestImage("opel.png"));

        Driver driver1 = new Driver("Иванов");
        Driver driver2 = new Driver("Петров");
        Driver driver3 = new Driver("Семёнов");
        Driver driver4 = new Driver("Васечкин");

        Transportation transportation1 = new Transportation(newDate(2014, 5, 24), driver1, car1, 2, 35);
        Transportation transportation2 = new Transportation(newDate(2014, 9, 11), driver3, car2, 4, 10);
        Transportation transportation3 = new Transportation(newDate(2014, 3, 3), driver2, car1, 1, 29);
        Transportation transportation4 = new Transportation(newDate(2014, 1, 8), driver4, car3, 7.5, 59);
        Transportation transportation5 = new Transportation(newDate(2014, 11, 4), driver1, car6, 3, 40.5);
        Transportation transportation6 = new Transportation(newDate(2014, 10, 17), driver2, car5, 5.5, 10.5);
        Transportation transportation7 = new Transportation(newDate(2014, 2, 26), driver1, car6, 6, 90);
        Transportation transportation8 = new Transportation(newDate(2014, 4, 9), driver1, car4, 4, 84.5);
        Transportation transportation9 = new Transportation(newDate(2014, 6, 13), driver3, car1, 2, 78);
        Transportation transportation10 = new Transportation(newDate(2014, 8, 14), driver2, car4, 3, 14);
        Transportation transportation11 = new Transportation(newDate(2014, 7, 10), driver4, car6, 2.5, 27);
        Transportation transportation12 = new Transportation(newDate(2014, 8, 19), driver2, car5, 3.5, 39);
        Transportation transportation13 = new Transportation(newDate(2014, 10, 15), driver1, car3, 7, 47);
        Transportation transportation14 = new Transportation(newDate(2014, 9, 28), driver4, car3, 8, 59);
        Transportation transportation15 = new Transportation(newDate(2014, 10, 30), driver1, car2, 4.5, 67);
        Transportation transportation16 = new Transportation(newDate(2014, 1, 29), driver3, car4, 7, 81);

        return Arrays.asList(
            transportation1, transportation2, transportation3, transportation4, transportation5, transportation6,
            transportation7, transportation8, transportation9, transportation10, transportation11, transportation12,
            transportation13, transportation14, transportation15, transportation16
        );
    }

    private static LocalDate newDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    private static byte[] loadTestImage(String imageName) {
        InputStream imageInputStream = null;
        try {
            imageInputStream = TestDataGenerator.class.getClassLoader().getResourceAsStream("images/" + imageName);
            return IOUtils.toByteArray(imageInputStream);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
        finally {
            IOUtils.closeQuietly(imageInputStream);
        }
    }
}