package org.example.javafxjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryData {

    public static List<CountryExtension> loadAllCountries() {

        List<CountryExtension> list = new ArrayList<>();

        String sql = "SELECT continent, indepyear FROM country";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://xserv:5432/world2", "reader", "reader");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String continent = rs.getString("continent");

                // null-safe: indepyear kann NULL sein
                Integer indepYear = (Integer) rs.getObject("indepyear");

                list.add(new CountryExtension(continent, indepYear));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
