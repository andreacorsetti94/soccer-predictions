package com.acorsetti.model.keys;

import com.acorsetti.model.Markets;

import java.io.Serializable;

public class BetCompositeKey implements Serializable {

    private String algoId;
    private String fixtureId;
    private Markets.MarketValue market;

}
