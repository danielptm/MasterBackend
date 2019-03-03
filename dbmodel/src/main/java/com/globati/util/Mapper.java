package com.globati.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Mapper {

    private static ObjectMapper instance = null;

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
}
