package com.group4.sportsclub.Suzane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MainLayoutController {

    @FXML private Label headerTitle;
    @FXML private VBox sideMenuBox;
    @FXML private StackPane contentArea;

    @FXML private Label userNameLabel;
    @FXML private Label roleLabel;
    @FXML private Circle profileCircle;

    private String currentUserId;

    @FXML
    public void handleProfileImageChange(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                profileCircle.setFill(new ImagePattern(image));
                saveProfileImage(selectedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProfileImage(File sourceFile) {
        try {
            File destDir = new File("data/ProfilePictures");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            String fileName = currentUserId + ".png";
            File destFile = new File(destDir, fileName);

            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSavedProfileImage() {
        try {
            File file = new File("data/ProfilePictures/" + currentUserId + ".png");
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                profileCircle.setFill(new ImagePattern(image));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Common/MemberLoginPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Brother's Union - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initMarketingManager(String userId) {
        this.currentUserId = userId;

        sideMenuBox.getChildren().clear();
        headerTitle.setText("Marketing Dashboard");

        userNameLabel.setText("Welcome, " + userId);
        roleLabel.setText("Marketing Manager");

        loadSavedProfileImage();

        addMenuButton("🚀 New Kit Launch", "MarketingManager/NewKitLaunch.fxml");
        addMenuButton("📊 Campaign Analytics", "MarketingManager/AnalyzeEngagement.fxml");
        addMenuButton("👕 Merchandise Stock", "MarketingManager/MerchandiseInventory.fxml");
        addMenuButton("🎤 Half-time Event", "MarketingManager/HalfTimeEntertainment.fxml");
        addMenuButton("🎟 Ticket Renewal", "MarketingManager/SeasonTicketRenewal.fxml");
        addMenuButton("🤝 Sponsor Partners", "MarketingManager/SponsorPartnership.fxml");
        addMenuButton("📧 Newsletter", "MarketingManager/Newsletter.fxml");
        addMenuButton("😊 Social Sentiment", "MarketingManager/SocialSentiment.fxml");
    }

    public void initFacilitiesManager(String userId) {
        this.currentUserId = userId;

        sideMenuBox.getChildren().clear();
        headerTitle.setText("Facilities Dashboard");

        userNameLabel.setText("Welcome, " + userId);
        roleLabel.setText("Facilities Manager");

        loadSavedProfileImage();

        addMenuButton("🌱 Pitch Maintenance", "FacilitiesManager/PitchMaintenance.fxml");
        addMenuButton("⚽ Equipment Inventory", "FacilitiesManager/EquipmentInventory.fxml");
        addMenuButton("👮 Security Staffing", "FacilitiesManager/SecurityStaffing.fxml");
        addMenuButton("⚠️ Issue Reporting", "FacilitiesManager/IssueReporting.fxml");
        addMenuButton("🎉 Non-Match Events", "FacilitiesManager/NonMatchEvents.fxml");
        addMenuButton("✅ Safety Compliance", "FacilitiesManager/Compliance.fxml");
        addMenuButton("🏨 Hospitality Suites", "FacilitiesManager/HospitalitySuites.fxml");
        addMenuButton("🧹 Post-Match Cleanup", "FacilitiesManager/Cleanup.fxml");
    }

    private void addMenuButton(String buttonText, String fxmlPath) {
        Button btn = new Button(buttonText);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("menu-button");

        btn.setOnAction(e -> {
            loadView(fxmlPath);
            headerTitle.setText("|  " + buttonText.replaceAll("[^a-zA-Z ]", "").trim());
        });

        sideMenuBox.getChildren().add(btn);
    }

    private void loadView(String fxmlPath) {
        try {
            String fullPath = "/com/group4/sportsclub/Suzane/" + fxmlPath;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fullPath));
            Parent view = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load view: " + fxmlPath);
        }
    }
}