package org.example.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {


    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    public void initialize() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();



        String sql =
                "SELECT (indepyear / 10) * 10 AS decade, COUNT(*) AS count " +
                        "FROM country " +
                        "WHERE indepyear IS NOT NULL AND indepyear > 0 " +
                        "GROUP BY decade " +
                        "ORDER BY decade";

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://xserv:5432/world2", "reader","reader");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String decade = rs.getInt("decade") + "s";
                int count = rs.getInt("count");
                series.setName("Anzahl der Länder");
                series.getData().add(new XYChart.Data<>(decade, count));

            }

            barChart.getData().add(series);

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
