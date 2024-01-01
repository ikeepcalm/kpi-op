package dev.ua.ikeepcalm.controllers.records;

import dev.ua.ikeepcalm.Application;
import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordHolder;
import dev.ua.ikeepcalm.data.RecordManager;
import dev.ua.ikeepcalm.data.source.RecordWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class RecordChoiceController extends Controller {

    @FXML
    public StackPane stackPane;
    @FXML
    public ComboBox<RecordWrapper> comboBox;
    @FXML
    public Button editButton;

    @FXML
    private void onChooseButtonClicked() {
        RecordWrapper selectedRecord = comboBox.getValue();
        if (selectedRecord != null) {
            handleSelectedRecord(selectedRecord);
        } else {
            error("Please select a record to edit!");
        }
    }

    private void handleSelectedRecord(RecordWrapper selectedRecord) {
        String nextPage = "records/edit-page.fxml";
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(nextPage));
        BorderPane loadedPane;
        try {
            loadedPane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RecordEditController editPageController = loader.getController();
        editPageController.setSelectedRecord(selectedRecord);
        stackPane.getChildren().setAll(loadedPane);
    }

    @FXML
    public void initialize() {
        List<RecordWrapper> loadedRecords;
        try {
            loadedRecords = RecordHolder.getInstance().getLoadedRecordWrappers();
            comboBox.setItems(FXCollections.observableArrayList(loadedRecords));
        } catch (IOException e) {
            error("Error loading records. Contact the developer.");
        }
    }
}
