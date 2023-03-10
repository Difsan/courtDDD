package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Title implements ValueObject<String> {
    private final String value;

    public Title(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Title is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
