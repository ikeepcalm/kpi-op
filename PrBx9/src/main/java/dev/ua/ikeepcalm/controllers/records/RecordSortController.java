package dev.ua.ikeepcalm.controllers.records;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import dev.ua.ikeepcalm.data.source.SortOrder;
import dev.ua.ikeepcalm.data.source.SortType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class RecordSortController extends Controller {

    @FXML
    private ChoiceBox<String> orderChoice;

    @FXML
    private ComboBox<String> fieldChoice;

    @FXML
    private void onSortButtonClicked() {
        String order = orderChoice.getValue().replace(" ", "_").toUpperCase();
        String field = fieldChoice.getValue().replace(" ", "_").toUpperCase();
        RecordManager.getInstance().sortRecords(SortType.valueOf(field), SortOrder.valueOf(order));
        success("Records sorted successfully!");
    }

    @FXML
    private void initialize() {
        orderChoice.setItems(FXCollections.observableArrayList("Ascending", "Descending"));
        fieldChoice.setItems(FXCollections.observableArrayList("By area", "By population"));
        orderChoice.setValue("Ascending");
        fieldChoice.setValue("By area");
    }
}
