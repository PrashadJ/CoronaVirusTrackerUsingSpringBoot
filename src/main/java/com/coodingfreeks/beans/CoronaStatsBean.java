package com.coodingfreeks.beans;

import com.coodingfreeks.Utils.CommonUtils;

import java.text.DecimalFormat;

/**
 * @author Prashad
 */
public class CoronaStatsBean {

    private String country;
    private double avgCases;
    private double maxCases;
    private double totalCases;

    public CoronaStatsBean(String country, double avgCases, double maxCases,double totalCases) {
        this.country = country;
        this.avgCases = avgCases;
        this.maxCases = maxCases;
        this.totalCases=totalCases;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getAvgCases() {
        return Double.parseDouble(new DecimalFormat("#.##").format(avgCases));
    }

    public void setAvgCases(double avgCases) {
        this.avgCases = avgCases;
    }

    public long getTotalCases() {
        return (long)totalCases;
    }

    public void setTotalCases(double totalCases) {
        this.totalCases = totalCases;
    }

    public double getMaxCases() {
        return maxCases;
    }

    public void setMaxCases(double maxCases) {
        this.maxCases = maxCases;
    }

    @Override
    public String toString() {
        return CommonUtils.gson.toJson(this);
    }
}
