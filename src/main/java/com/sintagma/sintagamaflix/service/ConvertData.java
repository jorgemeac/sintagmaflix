package com.sintagma.sintagamaflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> nameClass) {
        try {
            return mapper.readValue(json, nameClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

/* Esta classe implementa a INTERFACE "IConvertData", instancia um ObjectMapper chamado "mapper" e subscreve
o método genérico da INTERFACE retornando os dados do json de acordo com o classe nameClass definido em um
dos Records, a depender da necessidade.
*/
