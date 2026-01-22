package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;

// TODO: ListView, RadioButtons o.ä. zur Auswahl des Kontinents
//   Anzeige nur für diesen Kontinent
// -> nur 1x notwendige Daten aus der DB laden   in eine Collection
// bei Änderung der Auswahl Filterung der Collection und Aktualisierung des Charts

public class CountryInfoController {

    @FXML
    private BarChart<String, Number> barChart;


    private List<XYChart.Data<String, Number>> countryData;
    //Collection

    @FXML
    public void initialize() {

        //CountryData cd = new CountryData();
        //countryData = cd.getCountriesPerDecade();
        countryData = CountryData.getCountriesPerDecade();


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Countrydata");
        //Serie erstellen

        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: lightskyblue");


        series.getData().addAll(countryData);
        barChart.getData().add(series);
        //Meine Aufgabenstellung 7 zu der Serie hinzufügen

    }
}
