package com.acorsetti.core.model.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long eventId;

    private String fixtureId;
    private int elapsed;
    private String teamId;
    private String teamName;
    private String playerId;
    private String player;
    private String assistId;
    private String assist;
    private String eventType;
    private String detail;

    public Event() {
    }

    public Event(String fixtureId, int elapsed, String teamId, String teamName, String playerId, String player, String assistId, String assist, String eventType, String detail) {
        this.fixtureId = fixtureId;
        this.elapsed = elapsed;
        this.teamId = teamId;
        this.teamName = teamName;
        this.playerId = playerId;
        this.assistId = assistId;
        this.assist = assist;
        this.eventType = eventType;
        this.detail = detail;
        this.player = player;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getAssistId() {
        return assistId;
    }

    public void setAssistId(String assistId) {
        this.assistId = assistId;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public long getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", fixtureId='" + fixtureId + '\'' +
                ", elapsed=" + elapsed +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", player='" + player + '\'' +
                ", assistId='" + assistId + '\'' +
                ", assist='" + assist + '\'' +
                ", eventType='" + eventType + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
