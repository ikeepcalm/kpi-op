package dev.ua.ikeepcalm.controllers.files;

import dev.ua.ikeepcalm.controllers.Controller;
import dev.ua.ikeepcalm.data.RecordManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;

public class FileDeleteController  extends Controller {

    @FXML
    public TextField filePath;
    @FXML
    public TextField fileName;
    @FXML
    public Button deleteButton;

    @FXML
    private void onDeleteFileButtonClicked() {
        String fileNameText = fileName.getText();
        String filePathText = filePath.getText();
        if (!fileNameText.isEmpty() && !filePathText.isEmpty()) {
            String fullPath = filePathText + File.separator + fileNameText;
            File fileToDelete = new File(fullPath);
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    success("File deleted successfully at: " + fullPath);
                    RecordManager.getInstance().wipeRecords();
                } else {
                    error("Failed to delete file at: " + fullPath);
                }
            } else {
                warn("File does not exist at: " + fullPath);
            }
        } else {
            warn("File name or path cannot be empty!");
        }
    }
}
