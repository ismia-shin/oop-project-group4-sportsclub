package com.group4.sportsclub;

import com.group4.sportsclub.Suzane.MainLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(main.class.getResource("/com/group4/sportsclub/Common/MemberLoginPage.fxml"));
        Parent root = loader.load();

        stage.setTitle("Brother's Union Club Management System");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}