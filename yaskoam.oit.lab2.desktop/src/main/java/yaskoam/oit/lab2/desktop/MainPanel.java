package yaskoam.oit.lab2.desktop;

import yaskoam.oit.lab2.desktop.panels.DriversAndCarsPanel;
import yaskoam.oit.lab2.desktop.panels.TransportationsPanel;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPanel extends BaseComponent {

    public TransportationsPanel transportationsPanel;

    public DriversAndCarsPanel driversAndCarsPanel;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        driversAndCarsPanel.setTransportationsPanel(transportationsPanel);
    }
}