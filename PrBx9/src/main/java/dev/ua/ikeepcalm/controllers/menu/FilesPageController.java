package dev.ua.ikeepcalm.controllers.menu;

import dev.ua.ikeepcalm.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class FilesPageController extends Controller {
    @FXML
    public Button createButton;
    @FXML
    public Button loadButton;
    @FXML
    public Button deleteButton;
    @FXML
    private StackPane stackPane;

    @FXML
    private void onCreateFileButtonClicked() {
        super.loadPage(stackPane,"files/create-page.fxml");
        super.flushLastUsedButton(createButton);
    }

    @FXML
    private void onLoadFileButtonClicked() {
        super.loadPage(stackPane,"files/load-page.fxml");
        super.flushLastUsedButton(loadButton);
    }

    @FXML
    private void onDeleteFileButtonClicked() {
        super.loadPage(stackPane, "files/delete-page.fxml");
        super.flushLastUsedButton(deleteButton);
    }

}
