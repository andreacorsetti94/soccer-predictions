package com.acorsetti.service;

import com.acorsetti.model.Fixture;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;

public interface PickResultExtracterService {

    PickResult pickResult(Fixture fixture, MarketValue marketValue);

}
