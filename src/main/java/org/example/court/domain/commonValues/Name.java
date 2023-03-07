package main.java.org.example.court.domain.commonValues;

import main.java.org.example.court.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {
    private final String value;

    public Name(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Name is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
