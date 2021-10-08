package ru.savinov.shop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.savinov.shop.entities.User;

import java.io.IOException;

public class Converter {

    public static User toJavaObject(String strJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(strJSON, User.class);
    }
}