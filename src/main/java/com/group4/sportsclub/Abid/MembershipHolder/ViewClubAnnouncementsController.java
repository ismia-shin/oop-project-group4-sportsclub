package com.group4.sportsclub.Abid.MembershipHolder;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ViewClubAnnouncementsController
{
    @javafx.fxml.FXML
    private TableView tblAnnouncements;
    @javafx.fxml.FXML
    private TableColumn colCategory;
    @javafx.fxml.FXML
    private TableColumn colDate;
    @javafx.fxml.FXML
    private TextArea txtAnnouncementDetails;
    @javafx.fxml.FXML
    private TableColumn colTitle;
    @javafx.fxml.FXML
    private TableColumn colStatus;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleSaveForLater(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleMarkAsRead(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRefresh(ActionEvent actionEvent) {
    }
}