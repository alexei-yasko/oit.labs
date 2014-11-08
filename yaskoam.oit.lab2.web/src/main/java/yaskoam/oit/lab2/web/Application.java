package yaskoam.oit.lab2.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class Application extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }
}