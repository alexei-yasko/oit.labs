package yaskoam.oit.lab2.desktop.panels;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import yaskoam.oit.lab2.desktop.AppSettings;
import yaskoam.oit.lab2.desktop.BaseComponent;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Transportation;

public class TransportationsPanel extends BaseComponent {

    public TableView<Transportation> tableView;

    public TableColumn<Transportation, String> numberColumn;
    public TableColumn<Transportation, LocalDate> dateColumn;
    public TableColumn<Transportation, String> driverCodeColumn;
    public TableColumn<Transportation, String> carCodeColumn;
    public TableColumn<Transportation, Double> weightColumn;
    public TableColumn<Transportation, Double> lengthColumn;

    private TransportService transportService;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Callback<TableColumn<Transportation, LocalDate>, TableCell<Transportation, LocalDate>> cellFactory =
            param -> new TextFieldTableCell<>(new StringConverter<LocalDate>() {
                @Override
                public String toString(LocalDate date) {
                    return date.format(dateFormatter);
                }

                @Override
                public LocalDate fromString(String string) {
                    return LocalDate.parse(string, dateFormatter);
                }
            });

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        dateColumn.setCellFactory(cellFactory);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setOnEditCommit(event -> {
            Transportation transportation = event.getTableView().getItems().get(event.getTablePosition().getRow());
            transportation.setDate(event.getNewValue());
            transportService.updateTransportation(transportation);
        });

        driverCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDriver().getCode()));
        carCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCar().getCode()));

        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        transportService = AppSettings.get().getTransportService();

        List<Transportation> transportations = transportService.getTransportations();
        ObservableList<Transportation> observableTransportations = FXCollections.observableArrayList(transportations);

        tableView.setItems(observableTransportations);
    }
}