package yaskoam.oit.lab2.web.driver;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.TransportationsApplication;

public class DriversPage extends BasePage<List<Driver>> {

    public DriversPage() {
        setModel(new DriversModel());

        add(new EditDriverLink("addLink", Model.of(new Driver())));

        ListView<Driver> listView = new ListView<Driver>("drivers", getModel()) {
            @Override
            protected void populateItem(ListItem<Driver> item) {
                item.add(new Label("code", PropertyModel.of(item.getModel(), "code")));
                item.add(new Label("name", PropertyModel.of(item.getModel(), "name")));
                item.add(new EditDriverLink("editLink", item.getModel()));
                item.add(new RemoveDriverLink<>("removeLink", item.getModel()));
            }
        };
        add(listView);
    }

    private class EditDriverLink extends Link<Driver> {

        public EditDriverLink(String id, IModel<Driver> model) {
            super(id, model);
        }

        @Override
        public void onClick() {
            setResponsePage(new EditDriverPage(getModel()));
        }
    }

    public class RemoveDriverLink<T> extends Link<T> {

        public RemoveDriverLink(String id, IModel<T> model) {
            super(id, model);
        }

        @Override
        public void onClick() {
            try {
                TransportationsApplication.get().getTransportService().remove(getModelObject());
                DriversPage.this.detachModels();
            }
            catch (Exception e) {
                getPage().error(e.getMessage());
            }
        }
    }
}