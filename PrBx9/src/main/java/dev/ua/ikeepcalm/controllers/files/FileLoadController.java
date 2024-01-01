package dev.ua.ikeepcalm.controllers.files;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;

public class FileLoadController extends Controller {

    @FXML
    public TextField filePath;
    @FXML
    public TextField fileName;
    @FXML
    public Button loadButton;

    @FXML
    private void onLoadFileButtonClicked() {
        String fileNameText = fileName.getText();
        String filePathText = filePath.getText();

        if (!fileNameText.isEmpty() && !filePathText.isEmpty()) {
            String fullPath = filePathText + File.separator + fileNameText;
            File fileToLoad = new File(fullPath);
            if (fileToLoad.exists()) {
                if (RecordManager.getInstance().initializeRecords(fileToLoad)){
                    success("File loaded successfully from: " + fullPath);
                } else {
                    error("Failed to load file from: " + fullPath);
                }
            } else {
                warn("File does not exist at: " + fullPath);
            }
        } else {
            warn("File name or path cannot be empty!");
        }
    }
}
