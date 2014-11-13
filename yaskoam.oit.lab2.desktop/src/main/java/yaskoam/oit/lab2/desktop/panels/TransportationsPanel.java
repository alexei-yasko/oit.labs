package yaskoam.oit.lab2.desktop.panels;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import yaskoam.oit.lab2.desktop.AppSettings;
import yaskoam.oit.lab2.desktop.BaseComponent;
import yaskoam.oit.lab2.desktop.support.DoubleTextFieldConstraint;
import yaskoam.oit.lab2.desktop.support.UiUtils;
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

    public DatePicker newDateDatePicker;
    public ComboBox<Driver> newDriverComboBox;
    public ComboBox<Car> newCarComboBox;
    public TextField newWeightTextField;
    public TextField newLengthTextField;

    public TextField rateTextField;
    public TextField costTextField;

    private TransportService transportService;

    private ObservableList<Transportation> transportations;

    private ObservableList<Driver> drivers;

    private ObservableList<Car> cars;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        transportations = FXCollections.observableArrayList();
        drivers = FXCollections.observableArrayList();
        cars = FXCollections.observableArrayList();

        transportService = AppSettings.get().getTransportService();

        tableView.setItems(transportations);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removeTransportations(tableView.getSelectionModel().getSelectedItems());
            }
        });

        configureNumberColumn(numberColumn);
        configureDateColumn(dateColumn);
        configureDriverColumn(driverColumn);
        configureCarColumn(carColumn);
        configureWeightColumn(weightColumn);
        configureLengthColumn(lengthColumn);

        newWeightTextField.textProperty().addListener(new DoubleTextFieldConstraint(newWeightTextField));
        newLengthTextField.textProperty().addListener(new DoubleTextFieldConstraint(newWeightTextField));

        newDriverComboBox.setConverter(new DriverStringConverter(transportService));
        newDriverComboBox.setItems(drivers);
        newCarComboBox.setConverter(new CarStringConverter(transportService));
        newCarComboBox.setItems(cars);

        rateTextField.textProperty().addListener(new DoubleTextFieldConstraint(rateTextField));

        updateData();
    }

    public void updateData() {
        transportations.setAll(transportService.getAll(Transportation.class));
        drivers.setAll(transportService.getAll(Driver.class));
        cars.setAll(transportService.getAll(Car.class));
    }

    public void saveNewTransportation() {
        LocalDate date = newDateDatePicker.getValue();
        Driver driver = newDriverComboBox.getValue();
        Car car = newCarComboBox.getValue();
        double weight = UiUtils.getDoubleValue(newWeightTextField);
        double length = UiUtils.getDoubleValue(newLengthTextField);

        Transportation transportation = new Transportation(date, driver, car, weight, length);

        transportService.saveOrUpdate(transportation);

        updateData();
    }

    public void calculateCost() {
        double rate = UiUtils.getDoubleValue(rateTextField);
        List<Transportation> selected = tableView.getSelectionModel().getSelectedItems();

        double cost = transportService.calculateCost(selected, rate);

        NumberFormat.getInstance().format(cost);
        DecimalFormat formatter = new DecimalFormat("0.00");

        costTextField.setText(formatter.format(cost));
    }

    private void removeTransportations(List<Transportation> transportations) {
        transportService.removeAll(transportations);
        updateData();
    }

    private void configureDateColumn(TableColumn<Transportation, LocalDate> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new LocalDateStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("date"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setDate(event.getNewValue());
            transportService.saveOrUpdate(transportation);
        });
    }

    private void configureNumberColumn(TableColumn<Transportation, String> column) {
        column.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    private void configureDriverColumn(TableColumn<Transportation, Driver> column) {
        column.setCellFactory(param -> new ComboBoxTableCell<>(new DriverStringConverter(transportService), drivers));
        column.setCellValueFactory(new PropertyValueFactory<>("driver"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setDriver(event.getNewValue());
            transportService.saveOrUpdate(transportation);
        });
    }

    private void configureCarColumn(TableColumn<Transportation, Car> column) {
        column.setCellFactory(param -> new ComboBoxTableCell<>(new CarStringConverter(transportService), cars));
        column.setCellValueFactory(new PropertyValueFactory<>("car"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setCar(event.getNewValue());
            transportService.saveOrUpdate(transportation);
        });
    }

    private void configureWeightColumn(TableColumn<Transportation, Double> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DoubleStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("weight"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setWeight(event.getNewValue());
            transportService.saveOrUpdate(transportation);
        });
    }

    private void configureLengthColumn(TableColumn<Transportation, Double> column) {
        column.setCellFactory(param -> new TextFieldTableCell<>(new DoubleStringConverter()));
        column.setCellValueFactory(new PropertyValueFactory<>("length"));
        column.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setLength(event.getNewValue());
            transportService.saveOrUpdate(transportation);
        });
    }

    private class LocalDateStringConverter extends StringConverter<LocalDate> {

        private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        @Override
        public String toString(LocalDate date) {
            return date != null ? date.format(dateFormatter) : null;
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
            return driver != null ? Integer.toString(driver.getCode()) : null;
        }

        @Override
        public Driver fromString(String driverCode) {
            return transportService.get(Driver.class, Integer.parseInt(driverCode));
        }
    }

    private class CarStringConverter extends StringConverter<Car> {

        private TransportService transportService;

        private CarStringConverter(TransportService transportService) {
            this.transportService = transportService;
        }

        @Override
        public String toString(Car car) {
            return car != null ? Integer.toString(car.getCode()) : null;
        }

        @Override
        public Car fromString(String carCode) {
            return transportService.get(Car.class, Integer.parseInt(carCode));
        }
    }
}