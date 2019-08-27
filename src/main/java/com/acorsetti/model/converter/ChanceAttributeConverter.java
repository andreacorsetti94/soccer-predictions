package com.acorsetti.model.converter;

import com.acorsetti.model.eval.Chance;

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
