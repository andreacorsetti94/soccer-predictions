package com.acorsetti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class League {

    @Id
    private String leagueId;

    private String leagueName;
    private String countryName;
    private String seasonYear;
    private String seasonStartDate;
    private String seasonEndDate;
    private String seasonLogoUrl;

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    public String getSeasonEndDate() {
        return seasonEndDate;
    }

    public String getSeasonLogoUrl() {
        return seasonLogoUrl;
    }

    public League(){}

    public League(String leagueId, String leagueName, String countryName, String seasonYear,
                  String seasonStartDate, String seasonEndDate, String seasonLogoUrl) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.seasonYear = seasonYear;
        this.seasonStartDate = seasonStartDate;
        this.seasonEndDate = seasonEndDate;
        this.seasonLogoUrl = seasonLogoUrl;
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueId='" + leagueId + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", seasonYear='" + seasonYear + '\'' +
                ", seasonStartDate='" + seasonStartDate + '\'' +
                ", seasonEndDate='" + seasonEndDate + '\'' +
                ", seasonLogoUrl='" + seasonLogoUrl + '\'' +
                '}';
    }
}
