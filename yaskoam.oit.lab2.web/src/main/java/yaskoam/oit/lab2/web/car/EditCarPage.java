package yaskoam.oit.lab2.web.car;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.web.BasePage;
import yaskoam.oit.lab2.web.support.CancelButton;
import yaskoam.oit.lab2.web.support.EditEntityForm;
import yaskoam.oit.lab2.web.support.SaveButton;
import yaskoam.oit.lab2.web.support.Utils;

public class EditCarPage extends BasePage<Car> {

    private FileUploadField fileUploadField;

    public EditCarPage(IModel<Car> model) {
        setModel(model);

        EditCarForm editForm = new EditCarForm("editForm", model);

        editForm.add(new TextField<>("model", PropertyModel.of(getModel(), "model"), String.class));
        editForm.add(Utils.createImage("photo", getModelObject().getPhoto()));
        editForm.add(fileUploadField = new FileUploadField("photoFileInput"));
        editForm.add(new SaveButton("saveButton", CarsPage.class));
        editForm.add(new CancelButton("cancelButton", CarsPage.class));

        add(editForm);
    }

    private class EditCarForm extends EditEntityForm<Car> {

        public EditCarForm(String id, IModel<Car> model) {
            super(id, model);
        }

        @Override
        protected void onSubmit() {
            FileUpload upload = fileUploadField.getFileUpload();
            if (upload != null) {
                getModelObject().setPhoto(upload.getBytes());
            }

            super.onSubmit();
        }
    }
}