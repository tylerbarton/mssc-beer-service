package com.tylerbarton.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Example custom deserializer using Jackson for JSON strings
 */
public class LocalDateDeserializer extends StdDeserializer {

    /**
     * Custom constructor that passes in the class type to the StdDserializer
     */
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    /**
     * Custom deserializer for dates
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JacksonException
     */
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return LocalDate.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.BASIC_ISO_DATE);
    }
}
