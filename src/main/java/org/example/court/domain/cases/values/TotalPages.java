package main.java.org.example.court.domain.cases.values;

import main.java.org.example.court.generic.ValueObject;

import java.util.Objects;

public class TotalPages implements ValueObject<Integer> {
    private final Integer value;

    public TotalPages(Integer value) {
        this.value = Objects.requireNonNull(value);
        if (this.value==null){
            throw new IllegalArgumentException("Pages is not valid");}
    }

    @Override
    public Integer value() {
        return value;
    }
}
