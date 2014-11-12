package yaskoam.oit.lab2.web;

import org.apache.wicket.markup.html.WebPage;

import yaskoam.oit.lab2.service.TransportService;

public class BasePage extends WebPage {

    public BasePage() {
        add(new Header("header"));
    }

    public TransportService getTransportService() {
        return TransportationsApplication.get().getTransportService();
    }
}