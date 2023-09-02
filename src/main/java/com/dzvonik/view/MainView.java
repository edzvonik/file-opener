package com.dzvonik.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainView {

    @FXML
    private TextArea textArea;

    public void setText(String text) {
        textArea.setText(text);
    }

}
