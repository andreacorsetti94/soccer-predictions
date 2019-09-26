package com.acorsetti.core.model.converter;

import com.acorsetti.core.model.eval.Chance;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChanceAttributeConverter implements AttributeConverter<Chance,Double> {

    @Override
    public Double convertToDatabaseColumn(Chance chance) {
        return chance.getValue();
    }

    @Override
    public Chance convertToEntityAttribute(Double aDouble) {
        return new Chance(aDouble);
    }
}
