package main.java.org.example.court.domain.cases.values;

import main.java.org.example.court.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class UpdateDate implements ValueObject<LocalDate> {
    private LocalDate value;

    public UpdateDate(LocalDate value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
