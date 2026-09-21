module fr.javafxcours.vercorium {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.javafxcours.vercorium to javafx.fxml;
    exports fr.javafxcours.vercorium;
}