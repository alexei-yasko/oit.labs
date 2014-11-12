package yaskoam.oit.lab2.web.driver;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.web.BasePage;

public class DriversPage extends BasePage {

    public DriversPage() {
        DataView<Driver> dataView = new DataView<Driver>("drivers", new DriversDataProvider(new DriversModel())) {
            @Override
            protected void populateItem(Item<Driver> item) {
                item.add(new Label("code", PropertyModel.of(item.getModel(), "code")));
                item.add(new Label("name", PropertyModel.of(item.getModel(), "name")));
            }
        };

        add(dataView);
    }

    private class DriversDataProvider extends ListDataProvider<Driver> {

        private IModel<List<Driver>> driversModel;

        public DriversDataProvider(IModel<List<Driver>> driversModel) {
            this.driversModel = driversModel;
        }

        @Override
        protected List<Driver> getData() {
            return driversModel.getObject();
        }
    }

    private class DriversModel extends LoadableDetachableModel<List<Driver>> {

        @Override
        protected List<Driver> load() {
            return getTransportService().getDrivers();
        }
    }
}