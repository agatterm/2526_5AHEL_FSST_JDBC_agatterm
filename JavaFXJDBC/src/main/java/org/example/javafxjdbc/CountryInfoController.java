package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryInfoController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ComboBox<String> dropdown;   // fx:id="dropdown" aus dem FXML

    private List<CountryExtension> allCountries;

    @FXML
    public void initialize() {

        allCountries = CountryData.loadAllCountries();

        dropdown.getItems().addAll(
                "All",
                "Europe",
                "North America",
                "South America",
                "Asia",
                "Africa",
                "Australia",
                "ANtarktica"
        );


        dropdown.setValue("Europe");

        dropdown.setOnAction(e -> {
            String selected = dropdown.getValue();
            updateChart(selected);
        });

        updateChart(null);
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

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(continentFilter == null ? "Alle" : continentFilter);

        decadeCount.keySet().stream().sorted().forEach(decade -> {
            int count = decadeCount.get(decade);
            series.getData().add(new XYChart.Data<>(decade + "s", count));
        });

        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
