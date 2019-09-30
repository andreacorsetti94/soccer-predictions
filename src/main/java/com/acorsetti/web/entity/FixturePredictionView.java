package com.acorsetti.web.entity;

public class FixturePredictionView {

    private String countryName;
    private String leagueName;
    private String homeTeamName;
    private String awayTeamName;
    private String date;
    private String result;
    private Double hdaHome;
    private Double hdaDraw;
    private Double hdaAway;
    private Double u2_5;
    private Double o2_5;
    private Double bttsYes;
    private Double bttsNo;

    public FixturePredictionView() {
    }

    public FixturePredictionView(String countryName, String leagueName, String date, String result, String homeTeamName, String awayTeamName, Double hdaHome, Double hdaDraw, Double hdaAway, Double u2_5, Double o2_5, Double bttsYes, Double bttsNo) {
        this.countryName = countryName;
        this.leagueName = leagueName;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.date = date;
        this.hdaHome = hdaHome;
        this.hdaDraw = hdaDraw;
        this.hdaAway = hdaAway;
        this.u2_5 = u2_5;
        this.o2_5 = o2_5;
        this.bttsYes = bttsYes;
        this.bttsNo = bttsNo;
        this.result = result;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Double getHdaHome() {
        return hdaHome;
    }

    public void setHdaHome(Double hdaHome) {
        this.hdaHome = hdaHome;
    }

    public Double getHdaDraw() {
        return hdaDraw;
    }

    public void setHdaDraw(Double hdaDraw) {
        this.hdaDraw = hdaDraw;
    }

    public Double getHdaAway() {
        return hdaAway;
    }

    public void setHdaAway(Double hdaAway) {
        this.hdaAway = hdaAway;
    }

    public Double getU2_5() {
        return u2_5;
    }

    public void setU2_5(Double u2_5) {
        this.u2_5 = u2_5;
    }

    public Double getO2_5() {
        return o2_5;
    }

    public void setO2_5(Double o2_5) {
        this.o2_5 = o2_5;
    }

    public Double getBttsYes() {
        return bttsYes;
    }

    public void setBttsYes(Double bttsYes) {
        this.bttsYes = bttsYes;
    }

    public Double getBttsNo() {
        return bttsNo;
    }

    public void setBttsNo(Double bttsNo) {
        this.bttsNo = bttsNo;
    }

    public boolean isPredictionEmpty(){
        return hdaHome == null && hdaDraw == null && hdaAway == null && u2_5 == null && o2_5 == null
                && bttsNo == null && bttsYes == null;
    }
}
