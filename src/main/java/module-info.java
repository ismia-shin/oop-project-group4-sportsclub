module com.group4.sportsclub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.group4.sportsclub to javafx.fxml;
    exports com.group4.sportsclub;
}