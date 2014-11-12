package yaskoam.oit.lab2.web;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;

import yaskoam.oit.lab2.service.TestDataGenerator;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Transportation;
import yaskoam.oit.lab2.web.transportation.TransportationsPage;

public class TransportationsApplication extends WebApplication {

    @Autowired
    private TransportService transportService;

    @Override
    public Class<? extends Page> getHomePage() {
        return TransportationsPage.class;
    }

    @Override
    protected void init() {
        super.init();
        generateTestDataIfNeeded();
    }

    public static TransportationsApplication get() {
        return (TransportationsApplication) WebApplication.get();
    }

    public TransportService getTransportService() {
        return transportService;
    }

    private void generateTestDataIfNeeded() {
        if (transportService.getTransportations().isEmpty()) {
            List<Transportation> transportations = TestDataGenerator.generateTestData();
            transportService.saveTransportations(transportations);
        }
    }
}