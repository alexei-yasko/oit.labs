package yaskoam.oit.lab2.web.transportation;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.support.RedirectLink;
import yaskoam.oit.lab2.web.support.RemoveEntityLink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportationsPage extends BasePage<List<Transportation>> {

    private static final PatternDateConverter DATE_CONVERTER = new PatternDateConverter("dd.MM.yyyy", true);

    private double rate;

    private double cost;

    public TransportationsPage() {
        setModel(new TransportationsModel());

        CheckGroup<Transportation> group = new CheckGroup<>("group", Collections.emptyList());

        Form calculateCostForm = new Form("calculateCostForm") {
            @Override
            protected void onSubmit() {
                cost = getTransportService().calculateCost(new ArrayList<>(group.getModelObject()), rate);
            }
        };

        calculateCostForm.add(group);
        add(calculateCostForm);

        group.add(new RedirectLink("addTransportationLink", new EditTransportationPage(TransportationsPage.this)));
        group.add(new CheckGroupSelector("selectAll"));

        group.add(new TextField<>("rate", PropertyModel.of(this, "rate"), Double.class));
        group.add(new TextField<>("cost", PropertyModel.of(this, "cost"), Double.class));

        ListView<Transportation> listView = new ListView<Transportation>("transportations", getModel()) {
            @Override
            protected void populateItem(ListItem<Transportation> item) {
                item.add(new Check<>("check", item.getModel()));

                item.add(new Label("number", PropertyModel.of(item.getModel(), "number")));
                item.add(new DateLabel("date", PropertyModel.of(item.getModel(), "date"), DATE_CONVERTER));
                item.add(new Label("driver", PropertyModel.of(item.getModel(), "driver.code")));
                item.add(new Label("car", PropertyModel.of(item.getModel(), "car.code")));
                item.add(new Label("weight", PropertyModel.of(item.getModel(), "weight")));
                item.add(new Label("length", PropertyModel.of(item.getModel(), "length")));

                item.add(new RedirectLink("editLink", new EditTransportationPage(item.getModel(), TransportationsPage.this)));
                item.add(new RemoveEntityLink<>("removeLink", item.getModel()));
            }
        };

        listView.setReuseItems(true);
        group.add(listView);
    }

    private class TransportationsModel extends LoadableDetachableModel<List<Transportation>> {

        @Override
        protected List<Transportation> load() {
            return getTransportService().getAll(Transportation.class);
        }
    }
}