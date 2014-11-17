package yaskoam.oit.lab2.web.driver;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.web.TransportationsApplication;

public class DriversModel extends LoadableDetachableModel<List<Driver>> {

    @Override
    protected List<Driver> load() {
        return TransportationsApplication.get().getTransportService().getAll(Driver.class);
    }
}