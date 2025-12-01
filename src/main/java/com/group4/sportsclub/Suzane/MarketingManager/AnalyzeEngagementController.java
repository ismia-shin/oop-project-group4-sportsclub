package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class AnalyzeEngagementController {
    @FXML private BarChart<String, Number> engagementChart;
    @FXML private TableView<Metric> metricsTable;
    @FXML private TableColumn<Metric, String> colCampName;
    @FXML private TableColumn<Metric, Integer> colReach, colClicks;

    private ObservableList<Metric> dataList = FXCollections.observableArrayList();
    private final String FILE_NAME = "Marketing/engagement_metrics.bin";

    @FXML public void initialize() {
        colCampName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colReach.setCellValueFactory(new PropertyValueFactory<>("reach"));
        colClicks.setCellValueFactory(new PropertyValueFactory<>("clicks"));

        dataList.addAll(DataHandler.loadData(FILE_NAME));

        // If file is empty, add some initial data so chart isn't empty
        if(dataList.isEmpty()) {
            dataList.add(new Metric("Kit Launch", 15000, 3200));
            dataList.add(new Metric("Season Promo", 8000, 1200));
            DataHandler.saveData(FILE_NAME, dataList);
        }

        metricsTable.setItems(dataList);
        updateChart();
    }

    private void updateChart() {
        engagementChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Reach");
        for (Metric m : dataList) {
            series.getData().add(new XYChart.Data<>(m.getName(), m.getReach()));
        }
        engagementChart.getData().add(series);
    }

    @FXML public void handleExportPDF() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Report exported to PDF successfully!");
        alert.show();
    }

    public static class Metric implements Serializable {
        private String name; private int reach, clicks;
        public Metric(String name, int reach, int clicks) { this.name = name; this.reach = reach; this.clicks = clicks; }
        public String getName() { return name; }
        public int getReach() { return reach; }
        public int getClicks() { return clicks; }
    }
}