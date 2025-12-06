package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class MerchandiseInventoryController {
    @FXML private TableView<MerchItem> merchTable;
    @FXML private TableColumn<MerchItem, String> colItemName, colCategory, colStatus;
    @FXML private TableColumn<MerchItem, Integer> colStock;
    @FXML private TextField restockField, discountField;
    @FXML private Label totalItemsLabel, lowStockLabel;

    private ObservableList<MerchItem> inventory = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/MarketingManager/merchandise.bin";

    @FXML public void initialize() {
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        inventory.addAll(DataHandler.loadData(FILE_NAME));
        if(inventory.isEmpty()) {
            inventory.add(new MerchItem("Home Jersey 2025", "Apparel", 50, "In Stock"));
            inventory.add(new MerchItem("Scarf", "Accessories", 10, "Low Stock"));
            DataHandler.saveData(FILE_NAME, inventory);
        }

        merchTable.setItems(inventory);
        updateStats();
    }

    @FXML public void handleRestock() {
        MerchItem s = merchTable.getSelectionModel().getSelectedItem();
        if(s!=null && !restockField.getText().isEmpty()) {
            int addQty = Integer.parseInt(restockField.getText());
            s.setStock(s.getStock() + addQty);
            if(s.getStock() > 15) s.setStatus("In Stock");

            merchTable.refresh();
            updateStats();
            DataHandler.saveData(FILE_NAME, inventory); // Save
            restockField.clear();
        }
    }

    @FXML public void handleApplyPromotion() {
        MerchItem s = merchTable.getSelectionModel().getSelectedItem();
        if(s!=null && !discountField.getText().isEmpty()) {
            s.setStatus("SALE " + discountField.getText() + "% OFF");
            merchTable.refresh();
            DataHandler.saveData(FILE_NAME, inventory); // Save
            discountField.clear();
        }
    }

    private void updateStats() {
        totalItemsLabel.setText(String.valueOf(inventory.size()));
        long low = inventory.stream().filter(i -> i.getStock() < 15).count();
        lowStockLabel.setText(String.valueOf(low));
    }

    public static class MerchItem implements Serializable {
        private String name, category, status; private int stock;
        public MerchItem(String name, String category, int stock, String status) {
            this.name = name; this.category = category; this.stock = stock; this.status = status;
        }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public int getStock() { return stock; }
        public void setStock(int s) { this.stock = s; }
        public String getStatus() { return status; }
        public void setStatus(String s) { this.status = s; }
    }
}