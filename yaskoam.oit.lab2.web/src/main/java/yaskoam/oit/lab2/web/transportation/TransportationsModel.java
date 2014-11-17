package yaskoam.oit.lab2.web.transportation;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.TransportationsApplication;

public class TransportationsModel extends LoadableDetachableModel<List<Transportation>> {

    @Override
    protected List<Transportation> load() {
        return TransportationsApplication.get().getTransportService().getAll(Transportation.class);
    }
}