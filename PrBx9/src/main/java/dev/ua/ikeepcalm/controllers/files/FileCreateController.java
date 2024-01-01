package dev.ua.ikeepcalm.controllers.files;

import dev.ua.ikeepcalm.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class FileCreateController extends Controller {
    @FXML
    public TextField filePath;
    @FXML
    public TextField fileName;
    @FXML
    public Button createButton;

    @FXML
    private void onCreateFileButtonClicked() {
        String fileNameText = fileName.getText();
        String filePathText = filePath.getText();

        if (!fileNameText.isEmpty() && !filePathText.isEmpty()) {
            String fullPath = filePathText + File.separator + fileNameText;
            File fileToCreate = new File(fullPath);
            File folder = new File(filePathText);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    success("Folders created at: " + filePathText);
                } else {
                    error("Failed to create folder at: " + filePathText);
                    return;
                }
            }

            try {
                if (fileToCreate.createNewFile()) {
                    success("File created successfully at: " + fullPath);
                } else {
                    warn("File already exists at: " + fullPath);
                }
            } catch (IOException e) {
                error("An error occurred while creating the file.");
            }
        } else {
            warn("File name or path cannot be empty!");
        }
    }

}
