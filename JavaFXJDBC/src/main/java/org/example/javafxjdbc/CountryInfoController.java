package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

//TODO: berechnen Sie das den Durchschnitt der Jahre der Unabhängigkeit (der Auswal)
//  Version 1: Iteration über die Collection (List)
//  Version 2: Verwendung der STREAM-API für die Berechnung des Durchschnitts
public class CountryInfoController {

    public Label averageyear;
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ComboBox<String> dropdown;

    private List<CountryExtension> allCountries;
  

    @FXML
    public void initialize() {

        allCountries = CountryData.loadAllCountries();

        dropdown.getItems().addAll(
                "Europe",
                "North America",
                "South America",
                "Asia",
                "Africa",
                "Australia",
                "Antarktica"
        );


        dropdown.setValue("Europe");

        dropdown.setOnAction(e -> {
            String selected = dropdown.getValue();
            updateChart(selected);
        });

        updateChart(null);
    }


    private int calculateAverageIndepYear(String continentFilter) {

        int sum = 0;
        int count = 0;

        for (CountryExtension c : allCountries) {
            Integer year = c.getIndepYear();
            if (year == null || year <= 0) continue;

            if (continentFilter != null && !continentFilter.equals(c.getContinent())) continue;

            sum += year;
            count++;
        }

        if (count == 0) return 0;

        double avg = (double) sum / count;
        return (int) Math.round(avg);
    }





    private void updateChart(String continentFilter) {

        Map<Integer, Integer> decadeCount = new HashMap<>();

        for (CountryExtension c : allCountries) {

            Integer year = c.getIndepYear();
            if (year == null || year <= 0) continue;

            if (continentFilter != null && !continentFilter.equals(c.getContinent())) continue;

            int decade = (year / 10) * 10;
            decadeCount.put(decade, decadeCount.getOrDefault(decade, 0) + 1);
        }

        String avg = String.valueOf(calculateAverageIndepYear(continentFilter));
        averageyear.setText(avg);

        barChart.getData().clear();
        barChart.getXAxis().setAutoRanging(true);
        barChart.getYAxis().setAutoRanging(true);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Anzahl der Länder");

        decadeCount.keySet().stream().sorted().forEach(decade -> {
            int count = decadeCount.get(decade);
            series.getData().add(new XYChart.Data<>(decade + "s", count));
        });

        barChart.getData().clear();
        barChart.getData().add(series);
        barChart.layout();
        barChart.setAnimated(false);


    }
}
