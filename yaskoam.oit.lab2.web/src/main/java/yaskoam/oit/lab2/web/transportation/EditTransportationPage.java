package yaskoam.oit.lab2.web.transportation;

import java.util.List;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.BasePage;

public class EditTransportationPage extends BasePage<Transportation> {

    public EditTransportationPage(IModel<Transportation> model) {
        setModel(model);

        Form<Transportation> editForm = new Form<>("editForm", new CompoundPropertyModel<>(model));
        editForm.add(new TextField<String>("number"));
        editForm.add(new DateTextField("date", new PatternDateConverter("dd.MM.yyyy", false)));
        editForm.add(new DropDownChoice<>("driver", new DriversModel()));
        editForm.add(new DropDownChoice<>("car", new CarsModel()));
        editForm.add(new NumberTextField<Double>("weight"));
        editForm.add(new NumberTextField<Double>("length"));
        editForm.add(new Button("saveButton"));

        add(editForm);
    }

    private class DriversModel extends LoadableDetachableModel<List<Driver>> {

        @Override
        protected List<Driver> load() {
            return getTransportService().getAll(Driver.class);
        }
    }

    private class CarsModel extends LoadableDetachableModel<List<Car>> {

        @Override
        protected List<Car> load() {
            return getTransportService().getAll(Car.class);
        }
    }
}