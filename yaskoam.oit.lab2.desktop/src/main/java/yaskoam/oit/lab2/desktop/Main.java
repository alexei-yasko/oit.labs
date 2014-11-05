package yaskoam.oit.lab2.desktop;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new MainPanel();
        primaryStage.setTitle("Transportations editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}