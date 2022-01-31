package TODO.List.controller;

import TODO.List.service.TarefaService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.Date;

@Controller("/tarefas")
public class TarefaController {

    TarefaService tarefaService = new TarefaService();

    @Get(uri = "/getallprioridade", produces = MediaType.TEXT_PLAIN)
    public String getAllOrdenadoPrioridade(Boolean crescente, Boolean ordenar) {
        return tarefaService.getAll(crescente, ordenar);
    }

    @Get(uri = "/getcategoria", produces = MediaType.TEXT_PLAIN)
    public String getCategoria(String categoria) {
        return tarefaService.getCategoria(categoria);
    }

    @Get(uri = "/get", produces = MediaType.TEXT_PLAIN)
    public String get(int id) {
        return tarefaService.getId(id);
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    public String criaTarefa(String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        return tarefaService.criaTarefa(nome, descricao, dataDeTermino, nivelDePrioridade, categoria, status);
    }
}
