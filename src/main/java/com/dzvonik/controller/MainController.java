package com.dzvonik.controller;

import com.dzvonik.util.CustomLogger;
import com.dzvonik.util.FileUtils;
import com.dzvonik.view.MainView;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MainView implements Initializable {

    private static final CustomLogger LOGGER = CustomLogger.getInstance();

    @FXML
    MenuItem openFileMenuItem;

    @FXML
    public void openFileButtonClick(ActionEvent actionEvent) {
        Scene scene = openFileMenuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    String filePath = selectedFile.toPath().toString();
                    return FileUtils.openFile(filePath);
                }
            };

            task.setOnSucceeded(event -> {
                String text = task.getValue();
                setText(text);
            });

            task.setOnFailed(event -> {
                Exception exception = (Exception) task.getException();
                LOGGER.logError("Error open file: ", exception);
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

    }
}
