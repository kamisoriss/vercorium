package fr.javafxcours.vercorium;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VercoriumApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VercoriumApplication.class.getResource("view/Simulation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vercorium");
        stage.setScene(scene);
        stage.show();
    }
}
