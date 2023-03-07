package main.java.org.example.court.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
