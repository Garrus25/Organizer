package com.example.organizerclients.Requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SaveDataAsJson {

    public static <T> String saveDataAsJson(T data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String json = mapper.writeValueAsString(data);
        return json;
    }
}
