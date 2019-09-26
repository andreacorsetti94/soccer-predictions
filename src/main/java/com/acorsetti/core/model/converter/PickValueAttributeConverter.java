package com.acorsetti.core.model.converter;

import com.acorsetti.core.model.eval.PickValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PickValueAttributeConverter implements AttributeConverter<PickValue, Double> {

    @Override
    public Double convertToDatabaseColumn(PickValue pickValue) {
        return pickValue.getValue();
    }

    @Override
    public PickValue convertToEntityAttribute(Double aDouble) {
        return new PickValue(aDouble);
    }
}
