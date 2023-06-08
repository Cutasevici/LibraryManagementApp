module com.example.myproject2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.myproject2 to javafx.fxml;
    exports com.example.myproject2;
}