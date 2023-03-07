package main.java.org.example.court.domain.commonValues;

import main.java.org.example.court.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class CreateDate implements ValueObject<LocalDate> {
    private LocalDate value;

    public CreateDate(LocalDate value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
