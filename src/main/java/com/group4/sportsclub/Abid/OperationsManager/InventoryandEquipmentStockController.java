package com.group4.sportsclub.Abid.OperationsManager;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class InventoryandEquipmentStockController
{
    @javafx.fxml.FXML
    private TableView inventoryTable;
    @javafx.fxml.FXML
    private TableColumn colSupplier;
    @javafx.fxml.FXML
    private TableColumn colItem;
    @javafx.fxml.FXML
    private ComboBox itemComboBox;
    @javafx.fxml.FXML
    private TableColumn colCurrentStock;
    @javafx.fxml.FXML
    private TableColumn colReorderLevel;
    @javafx.fxml.FXML
    private TextArea orderConfirmationArea;
    @javafx.fxml.FXML
    private TextField reorderQtyField;
    @javafx.fxml.FXML
    private TableColumn colStatus;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onPlaceOrder(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onRefreshInventory(ActionEvent actionEvent) {
    }
}