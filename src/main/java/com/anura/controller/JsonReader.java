package com.anura.controller;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonReader {
    public <T> T readJson(Class<T> clazz, String filename) throws IOException {
        return readJson(clazz, com.google.gson.stream.JsonReader.class.getClassLoader().getResourceAsStream(filename));
    }
    public <T> T readJson(Class<T> clazz, InputStream stream) throws IOException{
        Gson gson = new Gson();
        try(Reader reader = new InputStreamReader(stream)){
            return gson.fromJson(reader, clazz);
        }
    }
}