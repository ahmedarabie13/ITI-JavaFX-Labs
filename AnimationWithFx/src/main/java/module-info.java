module com.arabie {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.arabie to javafx.fxml;
    exports com.arabie;
}