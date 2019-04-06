package com.globati.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Mapper {

    private static ObjectMapper instance = null;
    private static ObjectMapper converterInstance = null;

    protected Mapper() {

    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
            instance.enable(SerializationFeature.INDENT_OUTPUT);
        }
    }

    public static ObjectMapper getMapper() {
        if(instance == null) {
            createInstance();
        }
        return instance;
    }

    public static ObjectMapper getConverterMapper() {
        if (converterInstance == null) {
            converterInstance = new ObjectMapper();
        }
        return converterInstance;
    }
}
