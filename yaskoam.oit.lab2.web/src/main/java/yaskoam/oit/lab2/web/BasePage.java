package yaskoam.oit.lab2.web;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.resource.PackageResourceReference;

import yaskoam.oit.lab2.service.TransportService;

public class BasePage<T> extends GenericWebPage<T> {

    public BasePage() {
        add(new Header("header"));
        add(new FeedbackPanel("feedbackPanel"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(
            CssReferenceHeaderItem.forReference(new PackageResourceReference(TransportationsApplication.class, "style.css")));
    }

    public TransportService getTransportService() {
        return TransportationsApplication.get().getTransportService();
    }
}