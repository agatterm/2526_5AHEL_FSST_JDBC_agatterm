package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;

import java.sql.DatabaseMetaData;

public class HelloController {

    String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
    private DatabaseMetaData DriverManager;
    Connection conn = DriverManager.getConnection(url);
    public BarChart barChart;
    @FXML
    private Label welcomeText;



}