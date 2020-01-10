package com.acorsetti.core.newodds;

import com.acorsetti.core.model.odds.MarketOdds;

import java.time.LocalDateTime;
import java.util.List;

public class BookmakerOdds {

    private String fixtureId;
    private List<MarketOdds> marketOdds;
    private String bookmakerName;
    private LocalDateTime updatedAt;

    public BookmakerOdds(String bookmakerName, String fixtureId, List<MarketOdds> marketOdds, LocalDateTime updatedAt) {
        this.fixtureId = fixtureId;
        this.marketOdds = marketOdds;
        this.bookmakerName = bookmakerName;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public List<MarketOdds> getMarketOdds() {
        return marketOdds;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getBookmakerName() {
        return bookmakerName;
    }

    @Override
    public String toString() {
        return "BookmakerOdds{" +
                "fixtureId='" + fixtureId + '\'' +
                ", marketOdds=" + marketOdds +
                ", bookmakerName='" + bookmakerName + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
