package TODO.List.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.Date;

@Controller("/tarefas")
public class TarefaController {

    @Get(uri = "/getall", produces = MediaType.TEXT_PLAIN)
    public String getAll() {
        return "";
    }

    @Get(uri = "/getallprioridade", produces = MediaType.TEXT_PLAIN)
    public String getAllOrdenadoPrioridade(Boolean crescente) {
        return "";
    }

    @Get(uri = "/getcategoria", produces = MediaType.TEXT_PLAIN)
    public String getCategoria(String categoria) {
        return "";
    }

    @Get(uri = "/get", produces = MediaType.TEXT_PLAIN)
    public String get(int id) {
        return "";
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    public String getAll(String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        return "";
    }
}
