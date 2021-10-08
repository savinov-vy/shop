package ru.savinov.shop.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER", "USER"),
    ADMIN("ROLE_ADMIN", "ADMIN");

    private String value;
    private String label;

    public static RoleType findByValue(final String value) {
        return Arrays.stream(values())
                .filter(roleType -> roleType.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong detail value: %s", value)));
    }

    public static RoleType findByLabel(String label) {
        return Arrays.stream(values())
                .filter(roleType -> roleType.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong NoteFilterType label: %s", label)));
    }
}
