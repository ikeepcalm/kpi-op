package dev.ua.ikeepcalm.controllers.records;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import dev.ua.ikeepcalm.data.source.RecordWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordEditController extends Controller {

    @FXML
    public TextField regionName;
    @FXML
    public TextField regionArea;
    @FXML
    public TextField regionPopulation;
    @FXML
    public Button createButton;

    private RecordWrapper selectedRecord;

    @FXML
    private void onEditRecordButtonClicked() {
        try {
            String name = regionName.getText();
            double area = Double.parseDouble(regionArea.getText().replace(" ", ""));
            int population = Integer.parseInt(regionPopulation.getText().replace(" ", ""));
            if (population < 0 || area < 0) {
                error("Invalid input. Please enter valid numbers.");
                return;
            }
            RecordWrapper recordWrapper = new RecordWrapper(name, area, population);
            if (RecordManager.getInstance().updateRecord(recordWrapper)) {
                success("Record updated successfully!");
            } else {
                warn("A record with this name doesn't exist!");
            }
        } catch (NumberFormatException e) {
            error("Invalid input. Please enter valid numbers.");
        }
    }

    public void setSelectedRecord(RecordWrapper selectedRecord) {
        this.selectedRecord = selectedRecord;
        regionName.setText(selectedRecord.getName());
        regionArea.setText(String.valueOf(selectedRecord.getArea()));
        regionPopulation.setText(String.valueOf(selectedRecord.getPopulation()));
    }

    @FXML
    private void initialize() {
        if (selectedRecord != null) {
            regionName.setText(selectedRecord.getName());
            regionArea.setText(String.valueOf(selectedRecord.getArea()));
            regionPopulation.setText(String.valueOf(selectedRecord.getPopulation()));
        }
    }

}
