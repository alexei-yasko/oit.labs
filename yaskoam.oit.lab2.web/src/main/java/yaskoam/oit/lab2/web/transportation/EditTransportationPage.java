package yaskoam.oit.lab2.web.transportation;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.car.CarsModel;
import yaskoam.oit.lab2.web.driver.DriversModel;
import yaskoam.oit.lab2.web.support.CancelButton;
import yaskoam.oit.lab2.web.support.EditEntityForm;
import yaskoam.oit.lab2.web.support.SaveButton;

public class EditTransportationPage extends BasePage<Transportation> {

    public EditTransportationPage(IModel<Transportation> model) {
        setModel(model);

        EditEntityForm<Transportation> editForm = new EditEntityForm<>("editForm", new CompoundPropertyModel<>(model));

        editForm.add(new DateTextField("date", new PatternDateConverter("dd.MM.yyyy", true)));
        editForm.add(new DropDownChoice<>("driver", new DriversModel(), new DriverRenderer()));
        editForm.add(new DropDownChoice<>("car", new CarsModel(), new CarRenderer()));
        editForm.add(new TextField<>("weight", Double.class));
        editForm.add(new TextField<>("length", Double.class));
        editForm.add(new SaveButton("saveButton", TransportationsPage.class));
        editForm.add(new CancelButton("cancelButton", TransportationsPage.class));

        add(editForm);
    }

    private class DriverRenderer implements IChoiceRenderer<Driver> {

        @Override
        public String getDisplayValue(Driver driver) {
            return Integer.toString(driver.getCode());
        }

        @Override
        public String getIdValue(Driver driver, int index) {
            return Integer.toString(driver.getCode());
        }
    }

    private class CarRenderer implements IChoiceRenderer<Car> {

        @Override
        public String getDisplayValue(Car car) {
            return Integer.toString(car.getCode());
        }

        @Override
        public String getIdValue(Car car, int index) {
            return Integer.toString(car.getCode());
        }
    }
}