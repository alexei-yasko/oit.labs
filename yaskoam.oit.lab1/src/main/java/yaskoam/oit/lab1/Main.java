package yaskoam.oit.lab1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private Face face;

    private GraphicsContext context;

    private Timer timer;

    private Button playButton;

    private Button stopButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        face = newFace();
        initUi(primaryStage);
    }

    private void initUi(Stage primaryStage) {
        primaryStage.setTitle("OIT lab 1");

        BorderPane borderPane = new BorderPane();

        Canvas canvas = new Canvas(500, 500);
        borderPane.setCenter(canvas);
        context = canvas.getGraphicsContext2D();
        clearCanvas();
        drawFace();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 150, 15, 150));
        hBox.setSpacing(10);
        borderPane.setTop(hBox);

        playButton = new Button("Play");
        playButton.setPrefSize(100, 20);
        playButton.setOnAction((event) -> play());

        stopButton = new Button("Stop");
        stopButton.setPrefSize(100, 20);
        stopButton.setDisable(true);
        stopButton.setOnAction((event) -> stopTimer());

        hBox.getChildren().addAll(playButton, stopButton);

        primaryStage.setScene(new Scene(borderPane, Color.WHITE));

        primaryStage.show();
    }

    private Face newFace() {
        return new Face(250, 250, 200, 150);
    }

    private void drawFace() {
        face.draw(context);
    }

    private void clearCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
    }

    private void play() {
        stopButton.setDisable(false);
        playButton.setDisable(true);

        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                face.moveEyes();
                face.resizeMouth();

                Platform.runLater(() -> { clearCanvas(); drawFace(); });
            }
        }, 0, 100);
    }

    private void stopTimer() {
        stopButton.setDisable(true);
        playButton.setDisable(false);

        timer.cancel();
    }
}