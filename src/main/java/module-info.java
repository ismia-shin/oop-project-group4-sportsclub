module com.group4.sportsclub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.group4.sportsclub;
    requires java.desktop;


    // SAMIHA'S PACKAGES
    opens com.group4.sportsclub.Samiha.FinancialOfficer to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Physician to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Member to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Other to javafx.fxml, javafx.base;

    // SUZANE'S PACKAGE
    opens com.group4.sportsclub.Suzane to javafx.fxml;
    opens com.group4.sportsclub.Suzane.FacilitiesManager to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Suzane.MarketingManager to javafx.fxml, javafx.base;

    // ABID'S PACKAGES
    opens com.group4.sportsclub.Abid.MembershipHolder to javafx.fxml;
    opens com.group4.sportsclub.Abid.OperationsManager to javafx.fxml;

    // COMMON
    opens com.group4.sportsclub.Common to javafx.fxml, javafx.base;

    // MAIN
    opens com.group4.sportsclub to javafx.fxml;
    exports com.group4.sportsclub;
}