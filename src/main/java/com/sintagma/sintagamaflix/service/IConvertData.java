package com.sintagma.sintagamaflix.service;
//Estou cirando uma classe generica T cujo nome é nomeClass
public interface IConvertData {
    <T> T getData(String json, Class<T> nomeClass);
}
