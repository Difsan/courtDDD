package org.example.court.domain.commonValues;

import org.example.court.generic.ValueObject;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class Phone implements ValueObject<String> {
    private final String value;

    public Phone(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()|| this.value.length()!=10 ||
        !this.value.startsWith("3")|| !this.value.matches("[0-9]+")){
            throw new IllegalArgumentException("Phone is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
