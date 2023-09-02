package com.dzvonik.controller;

import com.dzvonik.util.CustomLogger;
import com.dzvonik.util.FileUtils;
import com.dzvonik.view.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
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

        String filePath = fileChooser.showOpenDialog(stage).toPath().toString();

        try {
            String text = FileUtils.openFile(filePath);
            setText(text);
        } catch (IOException e) {
            LOGGER.logError("Error open file");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
