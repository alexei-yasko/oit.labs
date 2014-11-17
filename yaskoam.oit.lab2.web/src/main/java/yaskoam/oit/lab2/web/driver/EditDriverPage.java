package yaskoam.oit.lab2.web.driver;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.support.CancelButton;
import yaskoam.oit.lab2.web.support.EditEntityForm;
import yaskoam.oit.lab2.web.support.SaveButton;

public class EditDriverPage extends BasePage<Driver> {

    public EditDriverPage(IModel<Driver> model) {
        setModel(model);

        EditEntityForm<Driver> editForm = new EditEntityForm<>("editForm", new CompoundPropertyModel<>(model));

        editForm.add(new TextField<>("name", String.class));
        editForm.add(new SaveButton("saveButton", DriversPage.class));
        editForm.add(new CancelButton("cancelButton", DriversPage.class));

        add(editForm);
    }
}