package com.acorsetti.model.dto;

public class TeamDto {
    private String teamId;
    private String teamName;
    private String imgUrl;

    public TeamDto() {
    }

    public TeamDto(String teamId, String teamName, String imgUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.imgUrl = imgUrl;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
