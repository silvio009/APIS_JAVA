package br.com.alura.screenmatch.Apis;

import br.com.alura.screenmatch.Excepitions.ErrorDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
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

public class Omdbapi {

    public static void BuscaOmdbapi() throws IOException {
        String nome = "";
        Scanner scanner = new Scanner(System.in);

        // converter objetos Java em formato JSON (serialização) e vice-versa (desserialização)
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();



        List<Titulo> titulos = new ArrayList<>();
        while (!nome.equalsIgnoreCase("sair")){
            System.out.println("qual filme vc gostaria de buscar:  ");
             nome = scanner.nextLine();


            if (nome.equalsIgnoreCase("sair")){
                break;
            }


            try {

                String endereco = "https://www.omdbapi.com/?t=" +

                        nome.replace(" ", "+") + "&apikey=94c578de";

                HttpClient client = HttpClient.newHttpClient(); // pedido de requisição
                HttpRequest request = HttpRequest.newBuilder() // caminho da requisição
                        .uri(URI.create(endereco))
                        .build();

                // resposta requisição
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                // pegando o corpo msg json
                String json = response.body();

                // avisa que esta em json e converte para uma classe (json -> classe)
                TituloOmdb meuTituloOMD = gson.fromJson(json, TituloOmdb.class);
                System.out.println(meuTituloOMD);

                Titulo meuTitulo = new Titulo(meuTituloOMD);
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);


                // error caso o numero foi invalido

            } catch (NumberFormatException e) {
                System.out.println("Não foi possivel ler o filme solicitado." + "Erro: " + " " + e.getMessage());
            }
            // erro caso a uri estiver incorreta

            catch (IllegalArgumentException e) {
                System.out.println("Não foi possivel ler o filme solicitado, verifique se a uri está correta." + " " + "Erro:" + e.getMessage());
            }
            // utilizando a minha Excepition
            catch (ErrorDeConversaoDeAnoException e) {
                System.out.println(e.getMensagem());
            }
            // termo generico para capturar o erro
            catch (Exception e) {
                System.out.println("Algo deu errado!");
            }


        }
        System.out.println(titulos);



        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("o sistema acabou...");
    }


}

