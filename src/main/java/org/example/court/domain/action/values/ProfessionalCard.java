package org.example.court.domain.action.values;

import org.example.court.generic.ValueObject;

import java.util.Objects;

public class ProfessionalCard implements ValueObject<String> {
    private final String value;

    public ProfessionalCard(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()|| this.value.length()!=8
        || !this.value.matches("[0-9]+")){
            throw new IllegalArgumentException("Professional Card is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
