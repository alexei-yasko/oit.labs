package yaskoam.oit.lab2.web.support;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import yaskoam.oit.lab2.web.TransportationsApplication;

public class EditEntityForm<T> extends Form<T> {

    public EditEntityForm(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    protected void onSubmit() {
        TransportationsApplication.get().getTransportService().saveOrUpdate(getModelObject());
    }
}