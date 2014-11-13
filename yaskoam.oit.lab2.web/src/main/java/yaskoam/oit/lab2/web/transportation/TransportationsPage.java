package yaskoam.oit.lab2.web.transportation;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.support.RemoveEntityLink;

import java.util.List;

public class TransportationsPage extends BasePage<List<Transportation>> {

    public TransportationsPage() {
        setModel(new TransportationsModel());

        TransportationsDataProvider dataProvider = new TransportationsDataProvider(getModel());

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

                item.add(new Link<Transportation>("editLink", item.getModel()) {
                    @Override
                    public void onClick() {

                    }
                });

                item.add(new RemoveEntityLink<>("removeLink", item.getModel()));
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
            return getTransportService().getAll(Transportation.class);
        }
    }
}