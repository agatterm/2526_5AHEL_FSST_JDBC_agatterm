module org.example.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.javafxjdbc to javafx.fxml;
    exports org.example.javafxjdbc;
}