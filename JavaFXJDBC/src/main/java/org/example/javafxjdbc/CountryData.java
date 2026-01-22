package org.example.javafxjdbc;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryData {


    public static List<XYChart.Data<String, Number>> getCountriesPerDecade() {
        List<XYChart.Data<String, Number>> list = new ArrayList<>();

        String sql =
                "SELECT (indepyear / 10) * 10 AS decade, COUNT(*) AS count " +
                        "FROM country " +
                        "WHERE indepyear IS NOT NULL AND indepyear > 0 " +
                        "GROUP BY decade " +
                        "ORDER BY decade";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://xserv:5432/world2", "reader", "reader");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            //Führt den SQL Code aus und speichert das ergebnis in rs

            while (rs.next()) {
                String decade = rs.getInt("decade") + "s";
                int count = rs.getInt("count");

                XYChart.Data<String, Number> data =
                        new XYChart.Data<>(decade, count);
                        //legt die Achsen für das Diagramm fest


                data.nodeProperty().addListener((obs, oldNode, node) -> { //node = ein einzelner Balken | wird aufgerufen wenn ein einzelner Balken gerendert wird
                    if (node != null) { //obs = die beobachtete Property | oldNode = alter Wert (0)
                            node.setStyle("-fx-background-color: green;");

                        Tooltip.install(node, //Tooltip = Zusatzinfo, hier für node also den Balken
                                new Tooltip("Dekade: " + decade + "\nLänder: " + count));


                        Label label = new Label(String.valueOf(count));
                        label.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: white");
                        //Font size und Style für das Textfeld auf den Balken setzen


                        ((javafx.scene.layout.StackPane) node).getChildren().add(label);
                        //Stackpane legt elemente übereinander, node an sich hat kein .getchildren
                        //getchildren = liste aller elemente in StackPane
                        //addLabel = fügt den Bereich für den Zusatztext hizu
                    }
                });

                list.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
