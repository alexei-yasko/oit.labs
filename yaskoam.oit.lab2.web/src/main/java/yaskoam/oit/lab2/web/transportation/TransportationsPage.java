package yaskoam.oit.lab2.web.transportation;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.BasePage;

import java.util.List;

public class TransportationsPage extends BasePage {

    @SpringBean
    private TransportService service;

    public TransportationsPage() {
        TransportationsDataProvider dataProvider = new TransportationsDataProvider(new TransportationsModel());

        DataView<Transportation> dataView = new DataView<Transportation>("transportations", dataProvider) {
            @Override
            protected void populateItem(Item<Transportation> item) {
                item.add(new Label("number", PropertyModel.of(item.getModel(), "number")));

                item.add(new DateLabel(
                    "date", PropertyModel.of(item.getModel(), "date"), new PatternDateConverter("dd.MM.yyyy", true)));

                item.add(new Label("driver", PropertyModel.of(item.getModel(), "driver.code")));
                item.add(new Label("car", PropertyModel.of(item.getModel(), "car.code")));
                item.add(new Label("weight", PropertyModel.of(item.getModel(), "weight")));
                item.add(new Label("length", PropertyModel.of(item.getModel(), "length")));
            }
        };

        add(dataView);
    }

    private class TransportationsDataProvider extends ListDataProvider<Transportation> {

        private IModel<List<Transportation>> transportationsModel;

        public TransportationsDataProvider(IModel<List<Transportation>> transportationsModel) {
            this.transportationsModel = transportationsModel;
        }

        @Override
        protected List<Transportation> getData() {
            return transportationsModel.getObject();
        }
    }

    private class TransportationsModel extends LoadableDetachableModel<List<Transportation>> {

        @Override
        protected List<Transportation> load() {
            return service.getTransportations();
        }
    }
}