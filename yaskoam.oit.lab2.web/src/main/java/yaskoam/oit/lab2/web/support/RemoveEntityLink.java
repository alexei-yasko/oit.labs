package yaskoam.oit.lab2.web.support;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import yaskoam.oit.lab2.web.TransportationsApplication;

public class RemoveEntityLink<T> extends Link<T> {

    public RemoveEntityLink(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    public void onClick() {
        try {
            TransportationsApplication.get().getTransportService().remove(getModelObject());
        }
        catch (Exception e) {
            getPage().error(e.getMessage());
        }
    }
}