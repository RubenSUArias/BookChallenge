package com.alura.challengeBook.challengeBook.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumoAPI {
    public static String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        System.out.println("1");
        HttpResponse<String> response = null;
        try {
            System.out.println(2);
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(3);
        String json = response.body();
//        System.out.println(4);
//        System.out.println(json);
        return json;
    }

}
