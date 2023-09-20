package br.com.alura.screenmatch.Arquivos;

import br.com.alura.screenmatch.Apis.ViaCepApi;
import br.com.alura.screenmatch.modelos.CepViacep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeArquivos {

    public void salvaArquivo(CepViacep cepViacep) throws IOException {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();



        FileWriter escrita = new FileWriter(cepViacep.cep()+ ".json");
        escrita.write(gson.toJson(cepViacep));
        escrita.close();
    }

}
