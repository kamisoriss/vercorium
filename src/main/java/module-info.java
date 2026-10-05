module fr.javafxcours.vercorium {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.javafxcours.vercorium to javafx.fxml;
    exports fr.javafxcours.vercorium;
}