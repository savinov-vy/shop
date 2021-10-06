package ru.savinov.spring.shop.common_dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER", "USER"),
    ADMIN("ROLE_ADMIN", "ADMIN");

    private String value;
    private String label;

    public static Role findByValue(final String value) {
        return Arrays.stream(values())
                .filter(role -> role.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong detail value: %s", value)));
    }

    public static Role findByLabel(String label) {
        return Arrays.stream(values())
                .filter(role -> role.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong NoteFilterType label: %s", label)));
    }
}
