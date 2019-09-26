package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.enums.PickResult;

public interface PickResultExtracterService {

    PickResult pickResult(Fixture fixture, MarketValue marketValue);

}
