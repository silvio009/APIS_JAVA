package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.Apis.Omdbapi;
import br.com.alura.screenmatch.Apis.ViaCepApi;
import br.com.alura.screenmatch.Arquivos.GeradorDeArquivos;
import br.com.alura.screenmatch.modelos.Cep;
import br.com.alura.screenmatch.modelos.CepViacep;

import java.io.IOException;
import java.util.Scanner;

public class PrincipalBusca {

    // fazendo uma busca pelo filme the Batman
    public static void main(String[] args) throws IOException, InterruptedException {
        //Omdbapi omdbapi = new Omdbapi();
        //omdbapi.BuscaOmdbapi();


        Scanner scanner = new Scanner(System.in);
        ViaCepApi viaCepApi = new ViaCepApi();

        System.out.println("Digite um numero de cep:");
        var cep = scanner.nextLine();


        try {

            CepViacep cepViacep = viaCepApi.cepViacep("06755300");
            System.out.println(cepViacep);
            GeradorDeArquivos geradorDeArquivos = new GeradorDeArquivos();
            geradorDeArquivos.salvaArquivo(cepViacep);




        }catch (RuntimeException e ){
            System.out.println(e.getMessage());
            System.out.println("finalizando a aplicação");
        }
    }


}
