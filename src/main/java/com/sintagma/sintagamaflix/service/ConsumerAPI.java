package com.sintagma.sintagamaflix.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Esta é minha classe para consumo das API's
public class ConsumerAPI {
    public String getData(String addressUrl) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(addressUrl)) //Defini uma variável "addressUrl" do tipo String como PARÂMETRO do método "getData"
                // para ser chamada como ARGUMENTO na classe principal com o endereço da API
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json; //O método getData vai retornar uma String "json" com os dados obtidos na API
    }

}
