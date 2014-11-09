package yaskoam.oit.lab2.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import yaskoam.oit.lab2.web.transportation.TransportationsPage;

public class Application extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return TransportationsPage.class;
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}