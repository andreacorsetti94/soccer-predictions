package com.acorsetti.core.model.converter;

import com.acorsetti.core.model.odds.OddsValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OddsValueAttributeConverter implements AttributeConverter<OddsValue, Double> {

    @Override
    public Double convertToDatabaseColumn(OddsValue oddsValue) {
        return oddsValue.getValue();
    }

    @Override
    public OddsValue convertToEntityAttribute(Double aDouble) {
        return new OddsValue(aDouble);
    }
}
