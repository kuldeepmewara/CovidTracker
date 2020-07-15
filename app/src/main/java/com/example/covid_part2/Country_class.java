package com.example.covid_part2;

/**
 * Created by Mom on 03-04-2020.
 */

public class Country_class implements Comparable< Country_class >
{

    String totalcases,deaths,recovered;
    String country;
    String img;

    public Country_class(String totalcases, String deaths, String recovered, String country, String img) {
        this.totalcases = totalcases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.country = country;
        this.img = img;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public int compareTo(Country_class o) {
        return this.getTotalcases().compareTo(o.getTotalcases());
    }
}
