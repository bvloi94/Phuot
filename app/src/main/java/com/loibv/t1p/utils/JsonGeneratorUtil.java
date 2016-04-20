package com.loibv.t1p.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by vanloibui on 4/18/16.
 */
public class JsonGeneratorUtil {

    public static class NumericBooleanSerializer extends JsonSerializer<Integer> {

        @Override
        public void serialize(Integer num, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
            generator.writeString(num==1?"true":"false");
        }
    }

    public static class NumericBooleanDeserializer extends JsonDeserializer<Integer> {

        @Override
        public Integer deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            if ("true".equals(parser.getText()))
                return 1;
            return 0;
        }
    }
}


