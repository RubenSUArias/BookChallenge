package com.alura.challengeBook.challengeBook.service;

public interface IconvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
