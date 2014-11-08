package yaskoam.oit.lab2.desktop.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.converter.DefaultStringConverter;
import yaskoam.oit.lab2.desktop.AppSettings;
import yaskoam.oit.lab2.desktop.BaseComponent;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DriversAndCarsPanel extends BaseComponent {

    public TableView<Driver> driversTableView;
    public TableColumn<Driver, Integer> driverCodeColumn;
    public TableColumn<Driver, String> driverNameColumn;

    public TableView<Car> carsTableView;
    public TableColumn<Car, Integer> carCodeColumn;
    public TableColumn<Car, String> carModelColumn;

    public TextField newDriverNameTextField;

    public TextField newCarModelTextField;

    private TransportationsPanel transportationsPanel;

    private TransportService transportService;

    private ObservableList<Driver> drivers;

    private ObservableList<Car> cars;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        drivers = FXCollections.observableArrayList();
        cars = FXCollections.observableArrayList();

        transportService = AppSettings.get().getTransportService();

        driversTableView.setItems(drivers);
        driversTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        driversTableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removeDrivers(driversTableView.getSelectionModel().getSelectedItems());
            }
        });

        configureDriverCodeColumn(driverCodeColumn);
        configureDriverNameColumn(driverNameColumn);

        carsTableView.setItems(cars);
        carsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        carsTableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removeCars(carsTableView.getSelectionModel().getSelectedItems());
            }
        });

        configureCarCodeColumn(carCodeColumn);
        configureCarModelColumn(carModelColumn);

        updateData();
    }

    public void setTransportationsPanel(TransportationsPanel transportationsPanel) {
        this.transportationsPanel = transportationsPanel;
    }

    public void updateData() {
        drivers.setAll(transportService.getDrivers());
        cars.setAll(transportService.getCars());
    }

    public void saveNewDriver() {
        Driver driver = new Driver(newDriverNameTextField.getText());
        transportService.saveDriver(driver);
        updateData();
        transportationsPanel.updateData();
    }

    public void saveNewCar() {
        Car car = new Car(newCarModelTextField.getText());
        transportService.saveCar(car);
        updateData();
        transportationsPanel.updateData();
    }

    private void removeDrivers(List<Driver> drivers) {
        transportService.removeDrivers(drivers);
        updateData();
        transportationsPanel.updateData();
    }

    private void removeCars(List<Car> cars) {
        transportService.removeCars(cars);
        updateData();
        transportationsPanel.updateData();
    }

    private void configureDriverCodeColumn(TableColumn<Driver, Integer> column) {
        column.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    private void configureDriverNameColumn(TableColumn<Driver, String> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DefaultStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        column.setOnEditCommit(event -> {
            Driver driver = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportService.updateDriver(driver);
        });
    }

    private void configureCarCodeColumn(TableColumn<Car, Integer> column) {
        column.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    private void configureCarModelColumn(TableColumn<Car, String> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DefaultStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("model"));
        column.setOnEditCommit(event -> {
            Car car = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportService.updateCar(car);
        });
    }
}