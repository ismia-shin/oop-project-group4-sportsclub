package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.Serializable;
import java.time.LocalDate;

public class NewsletterController {
    @FXML private TextField subjectField;
    @FXML private ComboBox<String> distCombo;
    @FXML private TextArea contentArea;

    // We use a simple list to store history, though not displayed in a table in the basic UI,
    // we save it so the logic is complete.
    private ObservableList<NewsLog> history = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/MarketingManager/newsletter_history.bin";

    @FXML public void initialize() {
        distCombo.getItems().addAll("All Members", "Subscribers", "Board Members");
        history.addAll(DataHandler.loadData(FILE_NAME));
    }

    @FXML public void handlePreview() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Preview");
        alert.setHeaderText(subjectField.getText());
        alert.setContentText(contentArea.getText());
        alert.show();
    }

    @FXML public void handleSend() {
        if(!subjectField.getText().isEmpty() && distCombo.getValue() != null) {
            // Add to history and save
            history.add(new NewsLog(LocalDate.now(), subjectField.getText(), distCombo.getValue()));
            DataHandler.saveData(FILE_NAME, history);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Newsletter Sent and Logged!");
            alert.show();

            subjectField.clear();
            contentArea.clear();
        }
    }

    public static class NewsLog implements Serializable {
        private LocalDate date; private String subject, list;
        public NewsLog(LocalDate date, String subject, String list) { this.date = date; this.subject = subject; this.list = list; }
    }
}