package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.io.Serializable;

public class EquipmentInventoryController {
    @FXML private ListView<EquipmentItem> equipList;
    @FXML private ComboBox<String> conditionCombo;
    @FXML private PieChart inventoryChart;

    private ObservableList<EquipmentItem> equipment = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/FacilitiesManager/equipment.bin";

    @FXML public void initialize() {
        conditionCombo.getItems().addAll("Good", "Needs Repair", "Discarded");

        equipment.addAll(DataHandler.loadData(FILE_NAME));
        if(equipment.isEmpty()) {
            equipment.add(new EquipmentItem("Goal Post North", "Good"));
            equipment.add(new EquipmentItem("Training Nets", "Needs Repair"));
            DataHandler.saveData(FILE_NAME, equipment);
        }

        equipList.setItems(equipment);
        updateChart();
    }

    private void updateChart() {
        long good = equipment.stream().filter(e -> e.getStatus().equals("Good")).count();
        long bad = equipment.size() - good;
        inventoryChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Good", good),
                new PieChart.Data("Bad", bad))
        );
    }

    @FXML public void handleUpdateStatus() {
        EquipmentItem selected = equipList.getSelectionModel().getSelectedItem();
        String newStatus = conditionCombo.getValue();

        if(selected != null && newStatus != null) {
            selected.setStatus(newStatus);
            equipList.refresh();
            updateChart();
            DataHandler.saveData(FILE_NAME, equipment); // Save Update
        }
    }

    @FXML public void handleGeneratePO() {
        System.out.println("Purchase Order Generated for damaged items.");
    }

    public static class EquipmentItem implements Serializable {
        private String name, status;
        public EquipmentItem(String name, String status) { this.name = name; this.status = status; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        @Override public String toString() { return name + " (" + status + ")"; }
    }
}