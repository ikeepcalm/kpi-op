package dev.ua.ikeepcalm.controllers.menu;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class RecordsPageController extends Controller {
    @FXML
    public TextArea recordsContent;
    @FXML
    public Button createButton;
    @FXML
    public Button editButton;
    @FXML
    public Button sortButton;
    @FXML
    public Button insertButton;
    @FXML
    public Button deleteButton;
    @FXML
    private StackPane stackPane;

    @FXML
    private void onCreateButtonClicked() {
        super.loadPage(stackPane,"records/create-page.fxml");
        super.flushLastUsedButton(createButton);
    }

    @FXML
    private void onEditButtonClicked() {
        super.loadPage(stackPane,"records/choice-page.fxml");
        super.flushLastUsedButton(editButton);
    }

    @FXML
    private void onSortButtonClicked() {
        super.loadPage(stackPane,"records/sort-page.fxml");
        super.flushLastUsedButton(sortButton);
    }

    @FXML
    private void onInsertButtonClicked() {
        if (RecordManager.getInstance().isSorted()) {
            super.loadPage(stackPane,"records/insert-page.fxml");
            super.flushLastUsedButton(insertButton);
        } else {
            error("Records must be sorted before inserting a new record. Please sort the records first > Records > Sort Records");
        }
    }

    @FXML
    private void onDeleteButtonClicked() {
        super.loadPage(stackPane,"records/delete-page.fxml");
        super.flushLastUsedButton(deleteButton);
    }

    @FXML
    private void initialize() {
        try {
            recordsContent.setText(RecordManager.getInstance().getRecordsAsString());
        } catch (IOException e) {
            error("An error occurred while loading the records!");
        }
    }

}
