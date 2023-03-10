package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Nit implements ValueObject<String> {
    private final String value;

    public Nit(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()|| this.value.length()!=10 ||
                !this.value.matches("[0-9]+")){
            throw new IllegalArgumentException("Nit is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
