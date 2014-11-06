package yaskoam.oit.lab2.desktop.panels;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import yaskoam.oit.lab2.desktop.AppSettings;
import yaskoam.oit.lab2.desktop.BaseComponent;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;
import yaskoam.oit.lab2.service.model.Transportation;

public class TransportationsPanel extends BaseComponent {

    public TableView<Transportation> tableView;

    public TableColumn<Transportation, String> numberColumn;
    public TableColumn<Transportation, LocalDate> dateColumn;
    public TableColumn<Transportation, Driver> driverColumn;
    public TableColumn<Transportation, Car> carColumn;
    public TableColumn<Transportation, Double> weightColumn;
    public TableColumn<Transportation, Double> lengthColumn;

    private TransportService transportService;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        transportService = AppSettings.get().getTransportService();

        configureNumberColumn(numberColumn);
        configureDateColumn(dateColumn);
        configureDriverColumn(driverColumn);
        configureCarColumn(carColumn);
        configureWeightColumn(weightColumn);
        configureLengthColumn(lengthColumn);

        tableView.getItems().setAll(transportService.getTransportations());
    }

    private void configureDateColumn(TableColumn<Transportation, LocalDate> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new LocalDateStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("date"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setDate(event.getNewValue());
            transportService.updateTransportation(transportation);
        });
    }

    private void configureNumberColumn(TableColumn<Transportation, String> column) {
        column.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    private void configureDriverColumn(TableColumn<Transportation, Driver> column) {
        ObservableList<Driver> drivers = FXCollections.observableArrayList(transportService.getDrivers());
        column.setCellFactory(param -> new ComboBoxTableCell<>(new DriverStringConverter(transportService), drivers));

        column.setCellValueFactory(new PropertyValueFactory<>("driver"));

        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setDriver(event.getNewValue());
            transportService.updateTransportation(transportation);
        });
    }

    private void configureCarColumn(TableColumn<Transportation, Car> column) {
        ObservableList<Car> cars = FXCollections.observableArrayList(transportService.getCars());
        column.setCellFactory(param -> new ComboBoxTableCell<>(new CarStringConverter(transportService), cars));

        column.setCellValueFactory(new PropertyValueFactory<>("car"));

        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setCar(event.getNewValue());
            transportService.updateTransportation(transportation);
        });
    }

    private void configureWeightColumn(TableColumn<Transportation, Double> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DoubleStringConverter()));

        column.setCellValueFactory(new PropertyValueFactory<>("weight"));

        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setWeight(event.getNewValue());
            transportService.updateTransportation(transportation);
        });
    }

    private void configureLengthColumn(TableColumn<Transportation, Double> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DoubleStringConverter()));

        column.setCellValueFactory(new PropertyValueFactory<>("length"));

        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setLength(event.getNewValue());
            transportService.updateTransportation(transportation);
        });
    }

    private class LocalDateStringConverter extends StringConverter<LocalDate> {

        private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        @Override
        public String toString(LocalDate date) {
            return date.format(dateFormatter);
        }

        @Override
        public LocalDate fromString(String stringDate) {
            return LocalDate.parse(stringDate, dateFormatter);
        }
    }

    private class DriverStringConverter extends StringConverter<Driver> {

        private TransportService transportService;

        private DriverStringConverter(TransportService transportService) {
            this.transportService = transportService;
        }

        @Override
        public String toString(Driver driver) {
            return driver.getCode();
        }

        @Override
        public Driver fromString(String driverCode) {
            return transportService.getDriver(driverCode);
        }
    }

    private class CarStringConverter extends StringConverter<Car> {

        private TransportService transportService;

        private CarStringConverter(TransportService transportService) {
            this.transportService = transportService;
        }

        @Override
        public String toString(Car car) {
            return car.getCode();
        }

        @Override
        public Car fromString(String carCode) {
            return transportService.getCar(carCode);
        }
    }
}