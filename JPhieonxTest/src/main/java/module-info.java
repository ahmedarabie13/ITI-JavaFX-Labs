module com.arabie {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    opens com.arabie to javafx.fxml;
    exports com.arabie;
}