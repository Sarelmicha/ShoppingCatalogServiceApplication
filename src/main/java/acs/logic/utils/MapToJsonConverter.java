package acs.logic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.typeconversion.AttributeConverter;
import java.util.Map;

public class MapToJsonConverter implements AttributeConverter<Map<String,Object>, String> {
    private ObjectMapper jackson;

    public MapToJsonConverter() {
        this.jackson = new ObjectMapper();
    }

    @Override
    public String toGraphProperty(Map<String, Object> value) {
//         use jackson for marshalling the attributes
        try {
            return this.jackson
                    .writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> toEntityAttribute(String value) {
//         use jackson for unmarshalling the json
        try {
            return this.jackson.readValue(value, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
