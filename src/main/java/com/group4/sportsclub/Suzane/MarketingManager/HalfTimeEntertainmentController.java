package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class HalfTimeEntertainmentController {
    @FXML private ListView<String> matchListView;
    @FXML private TextField performerField;
    @FXML private TextArea requirementsArea;
    @FXML private DatePicker rehearsalDate;
    @FXML private TableView<Act> rosterTable;
    @FXML private TableColumn<Act, String> colMatch, colAct, colStatus;

    private ObservableList<Act> acts = FXCollections.observableArrayList();
    private final String FILE_NAME = "Marketing/entertainment.bin";

    @FXML public void initialize() {
        matchListView.getItems().addAll("Vs Abahani (12 Dec)", "Vs Mohammedan (18 Dec)", "Vs Sheikh Russel (25 Dec)");

        colMatch.setCellValueFactory(new PropertyValueFactory<>("match"));
        colAct.setCellValueFactory(new PropertyValueFactory<>("act"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        acts.addAll(DataHandler.loadData(FILE_NAME));
        rosterTable.setItems(acts);
    }

    @FXML public void handleAssign() {
        if(matchListView.getSelectionModel().getSelectedItem() != null && !performerField.getText().isEmpty()) {
            String match = matchListView.getSelectionModel().getSelectedItem();
            String performer = performerField.getText();

            acts.add(new Act(match, performer, "Confirmed"));
            DataHandler.saveData(FILE_NAME, acts); // Save

            performerField.clear();
            requirementsArea.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Performer Assigned!");
            alert.show();
        }
    }

    public static class Act implements Serializable {
        private String match, act, status;
        public Act(String match, String act, String status) { this.match = match; this.act = act; this.status = status; }
        public String getMatch() { return match; }
        public String getAct() { return act; }
        public String getStatus() { return status; }
    }
}