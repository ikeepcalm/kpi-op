package dev.ua.ikeepcalm.controllers.records;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import dev.ua.ikeepcalm.data.source.RecordWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordInsertController extends Controller {

    @FXML
    public TextField regionName;
    @FXML
    public TextField regionArea;
    @FXML
    public TextField regionPopulation;
    @FXML
    public Button insertButton;
    @FXML
    private void onInsertRecordButtonClicked() {
        try {
            String name = regionName.getText();
            double area = Double.parseDouble(regionArea.getText().replace(" ", ""));
            int population = Integer.parseInt(regionPopulation.getText().replace(" ", ""));
            if (population < 0 || area < 0) {
                error("Invalid input. Please enter valid numbers.");
                return;
            }
            RecordWrapper recordWrapper = new RecordWrapper(name, area, population);
            RecordManager.getInstance().insertRecord(recordWrapper);
            success("Record inserted successfully!");
        } catch (NumberFormatException e) {
            error("Invalid input. Please enter valid numbers.");
        }
    }

}
