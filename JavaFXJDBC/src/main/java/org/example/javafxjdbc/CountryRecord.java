package org.example.javafxjdbc;

public class CountryRecord {
    private final String continent;
    private final Integer indepYear;

    public CountryRecord(String continent, Integer indepYear) {
        this.continent = continent;
        this.indepYear = indepYear;
    }

    public String getContinent() {
        return continent;
    }

    public Integer getIndepYear() {
        return indepYear;
    }
}
