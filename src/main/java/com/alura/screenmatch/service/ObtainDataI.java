package com.alura.screenmatch.service;

public interface ObtainDataI {
    <T> T obtainingData(String json, Class <T> clase);
}
