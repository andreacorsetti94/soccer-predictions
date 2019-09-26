package com.acorsetti.core.model.dto;

public class LeagueDto {

    private String leagueId;
    private String leagueName;
    private String countryName;
    private String country_code;
    private String seasonYear;
    private String seasonStartDate;
    private String seasonEndDate;
    private String seasonLogoUrl;
    private String flag;
    private boolean standings;

    public LeagueDto() {
    }

    public LeagueDto(String leagueId, String leagueName, String countryName, String country_code,
                     String seasonYear, String seasonStartDate, String seasonEndDate, String seasonLogoUrl, String flag, boolean standings) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.country_code = country_code;
        this.seasonYear = seasonYear;
        this.seasonStartDate = seasonStartDate;
        this.seasonEndDate = seasonEndDate;
        this.seasonLogoUrl = seasonLogoUrl;
        this.flag = flag;
        this.standings = standings;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(String seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public String getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(String seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public String getSeasonLogoUrl() {
        return seasonLogoUrl;
    }

    public void setSeasonLogoUrl(String seasonLogoUrl) {
        this.seasonLogoUrl = seasonLogoUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isStandings() {
        return standings;
    }

    public void setStandings(boolean standings) {
        this.standings = standings;
    }
}
