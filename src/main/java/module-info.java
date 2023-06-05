module com.no.omglearning {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires javafx.media;
    requires jimObjModelImporterJFX;
    opens com.no.omglearning to javafx.fxml;
    exports com.no.omglearning;
}