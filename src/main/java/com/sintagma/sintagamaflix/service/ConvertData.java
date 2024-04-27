package com.sintagma.sintagamaflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> nomeClass) {
        try {
            return mapper.readValue(json, nomeClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
