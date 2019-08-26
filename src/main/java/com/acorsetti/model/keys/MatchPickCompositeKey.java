package com.acorsetti.model.keys;

import com.acorsetti.model.Markets;

import java.io.Serializable;


public class MatchPickCompositeKey implements Serializable {

    private String fixtureId;
    private Markets.MarketValue marketValue;
}
