package org.example.court.domain.action.values;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Address implements ValueObject<String> {
    private final String value;

    public Address(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()|| this.value.length()> 100){
            throw new IllegalArgumentException("Address is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
