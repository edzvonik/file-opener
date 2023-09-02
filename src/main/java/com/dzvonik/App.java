package com.dzvonik;

import com.dzvonik.util.CustomLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final CustomLogger LOGGER = CustomLogger.getInstance();

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
        } catch (Exception e) {
            throw new RuntimeException("Error load Main.fxml", e);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Тестовое задание");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}