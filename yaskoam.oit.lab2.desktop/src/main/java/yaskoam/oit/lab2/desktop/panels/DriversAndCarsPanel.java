package yaskoam.oit.lab2.desktop.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import yaskoam.oit.lab2.desktop.AppSettings;
import yaskoam.oit.lab2.desktop.BaseComponent;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.service.model.Driver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public TableColumn<Car, byte[]> carPhotoColumn;

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
        configureCarPhotoColumn(carPhotoColumn);

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
        Car car = new Car(newCarModelTextField.getText(), choseCarPhoto());
        transportService.saveCar(car);
        updateData();
        transportationsPanel.updateData();
    }

    private byte[] choseCarPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите фото");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
            "Images (*.png, *.jpg, *.bmp, *.gif)", "*.png", "*.jpg", "*.bmp", "*.gif");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File photoFile = fileChooser.showOpenDialog(new Stage());

        byte[] photo = new byte[0];

        if (photoFile != null) {
            FileInputStream imageStream = null;
            try {
                imageStream = FileUtils.openInputStream(photoFile);
                photo = IOUtils.toByteArray(imageStream);
            }
            catch (IOException e) {
                throw new IllegalStateException(e);
            }
            finally {
                IOUtils.closeQuietly(imageStream);
            }
        }

        return photo;
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

    private void configureCarPhotoColumn(TableColumn<Car, byte[]> column) {
        column.setCellFactory(param -> new TableCell<Car, byte[]>() {

            @Override
            public void updateItem(byte[] photo, boolean empty) {
                if (photo != null && !empty) {
                    ImageView imageview = new ImageView(new Image(new ByteArrayInputStream(photo)));
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    setGraphic(imageview);
                }
                else {
                    setGraphic(null);
                }
            }
        });

        column.setCellValueFactory(new PropertyValueFactory<>("photo"));
    }
}