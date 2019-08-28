package com.acorsetti.model.keys;

import com.acorsetti.model.enums.MarketValue;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public class BetCompositeKey implements Serializable {

    private String algoId;
    private String fixtureId;

    @Enumerated(EnumType.STRING)
    private MarketValue market;

}
