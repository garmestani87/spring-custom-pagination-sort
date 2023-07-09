package com.garm.pagination.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.garm.pagination.utils.RegexUtils.*;

public class LocalDeserializer extends StdDeserializer<Object> {

    protected LocalDeserializer() {
        super(Object.class);
    }

    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        Object o = parser.readValueAs(Object.class);
        if (o.getClass().equals(String.class)) {
            if (isValidDate(String.valueOf(o))) {
                return LocalDate.parse(String.valueOf(o), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else if (isValidDateTime(String.valueOf(o))) {
                return LocalDateTime.parse(String.valueOf(o), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } else if (isValidTime(String.valueOf(o))) {
                return LocalTime.parse(String.valueOf(o), DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        }
        return o;
    }
}