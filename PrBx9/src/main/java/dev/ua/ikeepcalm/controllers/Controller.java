package dev.ua.ikeepcalm.controllers;

import dev.ua.ikeepcalm.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Controller {

    private Button lastUsedButton;

    protected void loadPage(StackPane stackPane, String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(page));
            BorderPane loadedPane = loader.load();
            stackPane.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            error("Error loading page. Contact the developer.");
        }
    }

    protected void setPage(BorderPane borderPane, String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(page));
            BorderPane loadedPane = loader.load();
            borderPane.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            warn("Error loading page. Contact the developer.");
        }
    }

    protected void flushLastUsedButton(Button currentButton) {
        if (lastUsedButton == null) {
            lastUsedButton = currentButton;
        }
        lastUsedButton.setStyle("-fx-background-color: transparent;  -fx-border-color: a9bcd0; -fx-border-width: 0px 0px 2px 0px;");
        currentButton.setStyle("-fx-background-color: #a9bcd0;  -fx-border-color: a9bcd0; -fx-border-width: 0px 0px 2px 0px;");
        lastUsedButton = currentButton;
    }


    protected void warn(String content) {
        showAlert(Alert.AlertType.WARNING, "Warning", content);
    }

    protected void error(String content) {
        showAlert(Alert.AlertType.ERROR, "Error", content);
    }

    protected void success(String content) {
        showAlert(Alert.AlertType.INFORMATION, "Success", content);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
