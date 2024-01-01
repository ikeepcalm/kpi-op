package dev.ua.ikeepcalm.controllers.menu;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordHolder;
import dev.ua.ikeepcalm.data.RecordManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomePageController extends Controller {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Button homeButton;
    @FXML
    public Button filesButton;

    @FXML Button exitButton;

    @FXML
    public Button recordsButton;
    @FXML
    private StackPane stackPane;

    @FXML
    private void onFilesButtonClicked() {
        super.loadPage(stackPane, "menu/files-page.fxml");
        super.flushLastUsedButton(filesButton);
    }

    @FXML
    private void onRecordsButtonClicked() {
        if (RecordHolder.getInstance().isFileLoaded()) {
            super.loadPage(stackPane, "menu/records-page.fxml");
            super.flushLastUsedButton(recordsButton);
        } else {
            error("No file loaded. Please load a file first > Files > Load File");
        }
    }

    @FXML
    private void onHomeButtonClicked() {
        super.setPage(borderPane, "menu/home-page.fxml");
        super.flushLastUsedButton(homeButton);
    }

    @FXML
    private void onExitButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit, or Cancel to stay.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
            Platform.exit();
            System.exit(0);
        }
    }
}
