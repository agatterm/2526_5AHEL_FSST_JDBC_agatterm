package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class HelloController {

    @FXML
    private BarChart<String, Number> barChart;


    private List<XYChart.Data<String, Number>> countryData;
    //Collection

    @FXML
    public void initialize() {


        countryData = CountryData.getCountriesPerDecade();


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Countrydata");
        //Serie erstellen


        series.getData().addAll(countryData);
        barChart.getData().add(series);
        //Meine Aufgabenstellung 7 zu der Serie hinzufügen

    }
}
