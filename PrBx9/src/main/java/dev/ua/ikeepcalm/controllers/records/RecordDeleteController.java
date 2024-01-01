package dev.ua.ikeepcalm.controllers.records;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordDeleteController extends Controller {

    @FXML
    public TextField regionName;
    @FXML
    public Button deleteButton;

    @FXML
    private void onDeleteRecordButtonClicked() {
        String name = regionName.getText();
        if (name.isEmpty()) {
            error("Please, enter a name!");
        } else {
            if (RecordManager.getInstance().deleteRecord(name)) {
                success("Record deleted successfully!");
            } else {
                warn("A record with this name doesn't exist!");
            }
        }
    }


}
