package yaskoam.oit.lab2.web.car;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.web.TransportationsApplication;

public class CarsModel extends LoadableDetachableModel<List<Car>> {

    @Override
    protected List<Car> load() {
        return TransportationsApplication.get().getTransportService().getAll(Car.class);
    }
}