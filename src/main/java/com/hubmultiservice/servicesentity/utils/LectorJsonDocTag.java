package com.hubmultiservice.servicesentity.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LectorJsonDocTag {
    


    public List<String> readKeys(byte[] jsonContent) throws IOException {
        List<String> keys = new ArrayList <>();
        ObjectMapper mapper = new ObjectMapper();
        JsonParser parser = mapper.createParser(jsonContent);

        while (!parser.isClosed()) {
            JsonToken token = parser.nextToken();
            if (token == JsonToken.FIELD_NAME) {
                String key = parser.getCurrentName();
                keys.add(key);
            }
        }

        return keys;
    }
}
