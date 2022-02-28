package TODO.List.controller;

import TODO.List.service.TarefaService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.Date;

@Controller("/tarefas")
public class TarefaController {

    private static final String LOCAL_ARQUIVO = "./src/resources/listaDeTarefas.json";
    TarefaService tarefaService = new TarefaService(LOCAL_ARQUIVO);

    @Get(uri = "/getallprioridade", produces = MediaType.TEXT_PLAIN)
    public String getAllOrdenadoPrioridade(Boolean crescente) {
        return tarefaService.getAll(crescente);
    }

    @Get(uri = "/getcategoria", produces = MediaType.TEXT_PLAIN)
    public String getCategoria(String categoria, Boolean crescente) {
        return tarefaService.getCategoria(categoria, crescente);
    }

    @Get(uri = "/get", produces = MediaType.TEXT_PLAIN)
    public String get(int id) {
        return tarefaService.getId(id);
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    public String criaTarefa(String nome, String descricao, String dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        if(tarefaService.criaTarefa(nome, descricao, dataDeTermino, nivelDePrioridade, categoria, status)) {
            return "Sucesso";
        } else {
            return "Falha";
        }
    }
}
