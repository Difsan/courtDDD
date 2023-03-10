package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class Email implements ValueObject<String> {
    private final String value;

    public Email(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()||!this.value.contains("@")){
            throw new IllegalArgumentException("Email is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
