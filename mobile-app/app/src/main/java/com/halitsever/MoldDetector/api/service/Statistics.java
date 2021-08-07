package com.halitsever.MoldDetector.api.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Statistics {

    @SerializedName("statistics")
    @Expose
    private String statistics;

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }
}