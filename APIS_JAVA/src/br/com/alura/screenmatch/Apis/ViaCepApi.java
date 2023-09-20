package br.com.alura.screenmatch.Apis;

import br.com.alura.screenmatch.modelos.Cep;
import br.com.alura.screenmatch.modelos.CepViacep;
import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViaCepApi {

    public CepViacep cepViacep( String cep) {
        Scanner scanner = new Scanner(System.in);

        URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");

        HttpRequest request = HttpRequest.newBuilder() // caminho da requisição
                .uri((endereco))
                .build();


        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), CepViacep.class);
        } catch (IOException | InterruptedException e ) {
            throw new RuntimeException( "não consegui obter esse cep" + e.getMessage());
        }



        }
    }


