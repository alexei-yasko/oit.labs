package yaskoam.oit.lab2.desktop;

import yaskoam.oit.lab2.service.TransportService;

public class AppSettings {

    private static final AppSettings appSettings = new AppSettings();

    private TransportService transportService;

    private AppSettings() {
    }

    public static AppSettings get() {
        return appSettings;
    }

    public TransportService getTransportService() {
        return transportService;
    }

    public void setTransportService(TransportService transportService) {
        this.transportService = transportService;
    }
}