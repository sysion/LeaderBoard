package com.sysionng.gadsleaderboard;

import com.google.gson.annotations.SerializedName;

public class GadsModel {
    @SerializedName("name")
    private String name;

    @SerializedName("hours")
    private String hours;

    @SerializedName("score")
    private String score;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public GadsModel() {
    }

    public GadsModel(String name, String hours, String score, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hour) {
        this.hours = hours;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}//
