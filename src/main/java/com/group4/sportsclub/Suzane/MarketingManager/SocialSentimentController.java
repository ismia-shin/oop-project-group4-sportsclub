package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.io.Serializable;

public class SocialSentimentController {
    @FXML private ListView<String> sentimentList;
    @FXML private TextArea responseDraft;
    @FXML private PieChart sentimentPieChart;

    private ObservableList<String> mentions = FXCollections.observableArrayList();
    private final String FILE_NAME = "Marketing/mentions.bin";

    @FXML public void initialize() {
        mentions.addAll(DataHandler.loadData(FILE_NAME));

        if(mentions.isEmpty()) {
            mentions.add("User1: Great match yesterday! (Positive)");
            mentions.add("User2: Ticket prices are too high. (Negative)");
            DataHandler.saveData(FILE_NAME, mentions);
        }

        sentimentList.setItems(mentions);

        // Static chart data for demo
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Positive", 70),
                new PieChart.Data("Negative", 30)
        );
        sentimentPieChart.setData(data);
    }

    @FXML public void handlePostResponse() {
        if(!responseDraft.getText().isEmpty()) {
            String newResponse = "Official Reply: " + responseDraft.getText();
            mentions.add(newResponse);
            DataHandler.saveData(FILE_NAME, mentions); // Save logic

            responseDraft.clear();
        }
    }
}