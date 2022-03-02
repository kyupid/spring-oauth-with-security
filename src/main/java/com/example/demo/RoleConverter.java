package com.example.demo;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.getKey();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Role.class).stream()
                .filter(e -> e.getKey().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
