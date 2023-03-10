package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Type implements ValueObject<String> {
    private final String value;

    public Type(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Type is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
