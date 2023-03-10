package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class CreateDate implements ValueObject<LocalDate> {
    private LocalDate value;

    public CreateDate() {
        this.value = LocalDate.now();
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
