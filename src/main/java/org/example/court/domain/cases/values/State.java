package main.java.org.example.court.domain.cases.values;

import main.java.org.example.court.generic.ValueObject;

import java.util.Objects;

public class State implements ValueObject<String> {
    private final String value;

    public State(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("State is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
