package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Pages implements ValueObject<Integer> {
    private final Integer value;

    public Pages(Integer value) {
        this.value = Objects.requireNonNull(value);
        if (this.value==null || this.value >5){
            throw new IllegalArgumentException("Pages is not valid");}
    }

    @Override
    public Integer value() {
        return value;
    }
}
