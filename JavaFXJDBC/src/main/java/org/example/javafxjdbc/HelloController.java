package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class HelloController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public void initialize() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Anzahl Länder");

        series.getData().addAll(CountryData.getCountriesPerDecade());
        barChart.getData().add(series);
    }
}
