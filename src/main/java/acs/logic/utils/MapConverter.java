package acs.logic.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.neo4j.core.convert.Neo4jConversionService;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyToMapConverter;

import java.util.Map;

public class MapConverter implements Neo4jPersistentPropertyToMapConverter {
    private ObjectMapper jackson;

    @Override
    public Map decompose(Object property, Neo4jConversionService neo4jConversionService) {
    
        return null;
    }

    @Override
    public Object compose(Map source, Neo4jConversionService neo4jConversionService) {
        return null;
    }
}
