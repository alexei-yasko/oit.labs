package yaskoam.oit.lab2.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public class TestDataGenerator {

    public static List<Transportation> generateTestData() {
        Car car1 = new Car("1", "audi");
        Car car2 = new Car("2", "bmw");
        Car car3 = new Car("3", "ford");
        Car car4 = new Car("4", "honda");
        Car car5 = new Car("5", "volkswagen");
        Car car6 = new Car("6", "opel");

        Driver driver1 = new Driver("1", "Иванов");
        Driver driver2 = new Driver("2", "Петров");
        Driver driver3 = new Driver("3", "Семёнов");
        Driver driver4 = new Driver("4", "Васечкин");

        Transportation transportation1 = new Transportation("1", newDate(2014, 5, 24), driver1, car1, 2, 35);
        Transportation transportation2 = new Transportation("2", newDate(2014, 9, 11), driver3, car2, 4, 10);
        Transportation transportation3 = new Transportation("3", newDate(2014, 3, 3), driver2, car1, 1, 29);
        Transportation transportation4 = new Transportation("4", newDate(2014, 1, 8), driver4, car3, 7.5, 59);
        Transportation transportation5 = new Transportation("5", newDate(2014, 11, 4), driver1, car6, 3, 40.5);
        Transportation transportation6 = new Transportation("6", newDate(2014, 10, 17), driver2, car5, 5.5, 10.5);
        Transportation transportation7 = new Transportation("7", newDate(2014, 2, 26), driver1, car6, 6, 90);
        Transportation transportation8 = new Transportation("8", newDate(2014, 4, 9), driver1, car4, 4, 84.5);
        Transportation transportation9 = new Transportation("9", newDate(2014, 6, 13), driver3, car1, 2, 78);
        Transportation transportation10 = new Transportation("10", newDate(2014, 8, 14), driver2, car4, 3, 14);
        Transportation transportation11 = new Transportation("11", newDate(2014, 7, 10), driver4, car6, 2.5, 27);
        Transportation transportation12 = new Transportation("12", newDate(2014, 8, 19), driver2, car5, 3.5, 39);
        Transportation transportation13 = new Transportation("13", newDate(2014, 10, 15), driver1, car3, 7, 47);
        Transportation transportation14 = new Transportation("14", newDate(2014, 9, 28), driver4, car3, 8, 59);
        Transportation transportation15 = new Transportation("15", newDate(2014, 10, 30), driver1, car2, 4.5, 67);
        Transportation transportation16 = new Transportation("16", newDate(2014, 1, 29), driver3, car4, 7, 81);

        return Arrays.asList(
            transportation1, transportation2, transportation3, transportation4, transportation5, transportation6,
            transportation7, transportation8, transportation9, transportation10, transportation11, transportation12,
            transportation13, transportation14, transportation15, transportation16
        );
    }

    private static Date newDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
}