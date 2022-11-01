module com.example.organizerclients {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.organizerclients to javafx.fxml, javafx.graphics, javafx.base;
    exports com.example.organizerclients to javafx.fxml, javafx.graphics;
    exports com.example.organizerclients.Controller to javafx.fxml, javafx.graphics;
    opens com.example.organizerclients.Controller to javafx.base, javafx.fxml, javafx.graphics;
    exports com.example.organizerclients.Model to javafx.fxml, javafx.graphics;
    opens com.example.organizerclients.Model to javafx.base, javafx.fxml, javafx.graphics;
}