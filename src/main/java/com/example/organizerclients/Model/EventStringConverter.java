package com.example.organizerclients.Model;

import javafx.util.StringConverter;

public class EventStringConverter<T> extends StringConverter<T> {
    @Override
    public String toString(Object o) {
        return o.toString();
    }

    @Override
    public T fromString(String s) {
        return null;
    }
}
