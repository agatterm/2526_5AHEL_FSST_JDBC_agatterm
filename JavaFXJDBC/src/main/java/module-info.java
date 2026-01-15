module org.example.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxjdbc to javafx.fxml;
    exports org.example.javafxjdbc;
}