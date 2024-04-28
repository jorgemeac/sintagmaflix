package com.sintagma.sintagamaflix.service;
//Estou cirando uma classe genérica T cujo nome é "nameClass" nesta INTERFACE
public interface IConvertData {
    <T> T getData(String json, Class<T> nameClass);
}
