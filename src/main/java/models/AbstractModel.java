package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.YamlObjectMappingException;

public abstract class AbstractModel {

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new YamlObjectMappingException("Not able to map YAML file to the object");
        }
    }
}
