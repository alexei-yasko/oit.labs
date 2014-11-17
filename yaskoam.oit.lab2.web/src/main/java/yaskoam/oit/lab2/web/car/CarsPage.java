package yaskoam.oit.lab2.web.car;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.TransportationsApplication;
import yaskoam.oit.lab2.web.support.Utils;

public class CarsPage extends BasePage<List<Car>> {

    public CarsPage() {
        setModel(new CarsModel());

        add(new EditCarLink("addLink", Model.of(new Car())));

        ListView<Car> listView = new ListView<Car>("cars", getModel()) {
            @Override
            protected void populateItem(ListItem<Car> item) {
                item.add(new Label("code", PropertyModel.of(item.getModel(), "code")));
                item.add(new Label("model", PropertyModel.of(item.getModel(), "model")));
                item.add(Utils.createImage("photo", item.getModelObject().getPhoto()));
                item.add(new EditCarLink("editLink", item.getModel()));
                item.add(new RemoveCarLink<>("removeLink", item.getModel()));
            }
        };
        add(listView);
    }

    private class EditCarLink extends Link<Car> {

        public EditCarLink(String id, IModel<Car> model) {
            super(id, model);
        }

        @Override
        public void onClick() {
            setResponsePage(new EditCarPage(getModel()));
        }
    }

    public class RemoveCarLink<T> extends Link<T> {

        public RemoveCarLink(String id, IModel<T> model) {
            super(id, model);
        }

        @Override
        public void onClick() {
            try {
                TransportationsApplication.get().getTransportService().remove(getModelObject());
                CarsPage.this.detachModels();
            }
            catch (Exception e) {
                getPage().error(e.getMessage());
            }
        }
    }
}