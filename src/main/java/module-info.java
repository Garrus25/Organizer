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
    requires jfxtras.controls;
    requires jfxtras.agenda;
    requires jasypt;
    requires com.fasterxml.jackson.databind;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires java.sql;

    opens com.example.organizerclients to javafx.fxml, javafx.graphics, javafx.base;
    exports com.example.organizerclients to javafx.fxml, javafx.graphics;
    exports com.example.organizerclients.Controller to javafx.fxml, javafx.graphics;
    opens com.example.organizerclients.Controller to javafx.base, javafx.fxml, javafx.graphics;
    exports com.example.organizerclients.Model to javafx.fxml, javafx.graphics;
    opens com.example.organizerclients.Model to javafx.base, javafx.fxml, javafx.graphics;
    exports com.example.organizerclients.Requests to com.fasterxml.jackson.databind;
    opens com.example.organizerclients.Requests to com.fasterxml.jackson.databind;
    exports com.example.organizerclients.Requests.RequestObjects to com.fasterxml.jackson.databind;
    opens com.example.organizerclients.Requests.RequestObjects to com.fasterxml.jackson.databind;

}