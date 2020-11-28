package by.ttre16.briana.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static by.ttre16.briana.controller.AbstractControllerTest.objectMapper;

public class MessageConverter {
    public static <T> List<T> readValues(String json, Class<T> clazz) {
        try {
            return  objectMapper
                    .readerFor(clazz)
                    .<T>readValues(json)
                    .readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> T readFromJson(MvcResult action, Class<T> clazz)
            throws UnsupportedEncodingException {
        return readValue(
                action.getResponse().getContentAsString(),
                clazz
        );
    }
}
