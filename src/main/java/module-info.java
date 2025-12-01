module com.group4.sportsclub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    // SAMIHA'S PACKAGES (Already correct)
    opens com.group4.sportsclub.Samiha.FinancialOfficer to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Physician to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Member to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Samiha.Other to javafx.fxml, javafx.base;

    // SUZANE'S MAIN PACKAGE
    opens com.group4.sportsclub.Suzane to javafx.fxml;

    // *** FIX IS HERE ***
    // Added ", javafx.base" to these two lines.
    // This allows the TableColumns to read data from your Model classes.
    opens com.group4.sportsclub.Suzane.FacilitiesManager to javafx.fxml, javafx.base;
    opens com.group4.sportsclub.Suzane.MarketingManager to javafx.fxml, javafx.base;

    // ABID'S PACKAGES (If Abid uses Tables, add ", javafx.base" here too)
    opens com.group4.sportsclub.Abid.MembershipHolder to javafx.fxml;
    opens com.group4.sportsclub.Abid.OperationsManager to javafx.fxml;

    // COMMON
    opens com.group4.sportsclub.Common to javafx.fxml, javafx.base;

    // MAIN
    opens com.group4.sportsclub to javafx.fxml;
    exports com.group4.sportsclub;
}