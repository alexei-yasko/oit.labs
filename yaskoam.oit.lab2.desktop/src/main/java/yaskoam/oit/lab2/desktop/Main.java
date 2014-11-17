package yaskoam.oit.lab2.desktop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yaskoam.oit.lab2.service.TestDataGenerator;
import yaskoam.oit.lab2.service.TransportService;
import yaskoam.oit.lab2.service.model.Transportation;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TransportService transportService = applicationContext.getBean(TransportService.class);
        AppSettings.get().setTransportService(transportService);

        generateTestDataIfNeeded();

        Parent root = new MainPanel();
        primaryStage.setTitle("Редактор перевозок");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void generateTestDataIfNeeded() {
        TransportService transportService = AppSettings.get().getTransportService();
        if (transportService.getAll(Transportation.class).isEmpty()) {
            transportService.saveOrUpdate(TestDataGenerator.generateTestData());
        }
    }
}