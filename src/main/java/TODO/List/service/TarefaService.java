package TODO.List.service;

import TODO.List.schemas.Tarefa;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TarefaService {

    private static String LOCAL_ARQUIVO = "*/";

    public String getAll(Boolean crescente, Boolean ordenar) {
        return "";
    }

    public String getId(int id) {
        return "";
    }

    public String getCategoria(String categoria) {
        return "";
    }

    private JSONArray getConteudoArquivoJSON() {

        return new JSONArray("");
    }

    public boolean criaTarefa(String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        Tarefa tarefa = new Tarefa(nome, descricao, dataDeTermino, nivelDePrioridade, categoria, status);
        JSONArray tarefasJSON = getConteudoArquivoJSON();
        tarefasJSON.put(converterTarefaParaJSONObject(tarefa));
        tarefasJSON = ordenaTarefas(tarefasJSON);
        return persistirDados(tarefasJSON);
    }

    private boolean persistirDados(JSONArray tarefasJSON) {

        if() {
            return atualizaArquivo(tarefasJSON);
        } else {
           return criaArquivo(tarefasJSON);
        }
    }

    private boolean obtemArquivo() {
        return true;
    }

    private boolean criaArquivo(JSONArray tarefasJSON) {
        try {
            FileWriter file = new FileWriter("E:/output.json");
            file.write(tarefasJSON.toString());
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean atualizaArquivo(JSONArray tarefasJSON) {
        return true;
    }

    private JSONArray converteListaTarefasParaJSONArray(ArrayList<Tarefa> tarefas) {
        JSONArray tarefasJSON = new JSONArray();
        for(Tarefa tarefa : tarefas) {
            tarefasJSON.put(converterTarefaParaJSONObject(tarefa));
        }
        return tarefasJSON;
    }

    @NotNull
    private JSONObject converterTarefaParaJSONObject(Tarefa tarefa) {
        JSONObject tarefaJSON = new JSONObject();
        tarefaJSON.put("nome", tarefa.getNome());
        tarefaJSON.put("descricao", tarefa.getDescricao());
        tarefaJSON.put("termino", tarefa.getDataDeTermino());
        tarefaJSON.put("prioridade", tarefa.getNivelDePrioridade());
        tarefaJSON.put("categoria", tarefa.getCategoria());
        tarefaJSON.put("status", tarefa.getStatus());
        return tarefaJSON;
    }

    private JSONArray ordenaTarefas(JSONArray tarefasJSON) {
        return tarefasJSON;
    }
}
