package TODO.List.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarefaService {

    private static final String LOCAL_ARQUIVO = "*/";
    private static final String PRIORIDADE = "prioridade";

    public String getAll(Boolean crescente) {
        JSONArray tarefas = obtemConteudoArquivo();
        tarefas = ordenaTarefas(tarefas, crescente);
        return tarefas.toString();
    }

    public String getId(int id) {
        JSONArray tarefas = obtemConteudoArquivo();
        JSONObject tarefa = buscaTarefaId(id, tarefas);
        return (tarefa != null) ? tarefa.toString() : "";
    }

    private JSONObject buscaTarefaId(int id, JSONArray tarefas) {
        for (int i = 0; i < tarefas.length(); i++) {
            if ((int) tarefas.getJSONObject(i).get("id") == id) {
                return tarefas.getJSONObject(i);
            }
        }
        return null;
    }

    public String getCategoria(String categoria, Boolean crescente) {
        JSONArray tarefas = obtemConteudoArquivo();
        tarefas = buscaTarefasCategoria(categoria, tarefas);
        tarefas = ordenaTarefas(tarefas, crescente);
        return tarefas.toString();
    }

    private JSONArray buscaTarefasCategoria(String categoria, JSONArray tarefas) {
        JSONArray tarefasCategoria = new JSONArray();
        for (int i = 0; i < tarefas.length(); i++) {
            if ((String) tarefas.getJSONObject(i).get("categoria") == categoria) {
                tarefasCategoria.put(tarefas.getJSONObject(i));
            }
        }
        return tarefasCategoria;
    }

    public boolean criaTarefa(String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        JSONArray tarefasJSON = obtemConteudoArquivo();
        JSONObject tarefa = criarTarefaJSONObject(tarefasJSON.length() + 1, nome, descricao, dataDeTermino, nivelDePrioridade, categoria, status);
        tarefasJSON.put(tarefa);
        tarefasJSON = ordenaTarefas(tarefasJSON, true);
        return persistirDados(tarefasJSON);
    }

    private boolean persistirDados(JSONArray tarefasJSON) {
        return persistirNoArquivo(tarefasJSON);
    }

    private JSONArray obtemConteudoArquivo() {
        FileReader file = obtemArquivo();
        if (file != null) {
            JSONTokener tokener = new JSONTokener(file);
            return new JSONArray(tokener);
        } else {
            return new JSONArray();
        }
    }

    private FileReader obtemArquivo() {
        try {
            return new FileReader(LOCAL_ARQUIVO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean persistirNoArquivo(JSONArray tarefasJSON) {
        try {
            FileWriter file = new FileWriter(LOCAL_ARQUIVO);
            file.write(tarefasJSON.toString());
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @NotNull
    private JSONObject criarTarefaJSONObject(int id, String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        JSONObject tarefaJSON = new JSONObject();
        tarefaJSON.put("id", id);
        tarefaJSON.put("nome", nome);
        tarefaJSON.put("descricao", descricao);
        tarefaJSON.put("termino", dataDeTermino);
        tarefaJSON.put("prioridade", nivelDePrioridade);
        tarefaJSON.put("categoria", categoria);
        tarefaJSON.put("status", status);
        return tarefaJSON;
    }

    private JSONArray ordenaTarefas(JSONArray tarefasJSON, boolean crescente) {
        JSONArray tarefasOrdenadas = new JSONArray();

        List<JSONObject> listaTarefas = new ArrayList<>();
        for (int i = 0; i < tarefasJSON.length(); i++) {
            listaTarefas.add(tarefasJSON.getJSONObject(i));
        }
        listaTarefas.sort((a, b) -> {
            String valA = (String) a.get(PRIORIDADE);
            String valB = (String) b.get(PRIORIDADE);
            if (crescente) {
                return valA.compareTo(valB);
            } else {
                return valB.compareTo(valA);
            }
        });
        for (int i = 0; i < tarefasJSON.length(); i++) {
            tarefasOrdenadas.put(listaTarefas.get(i));
        }
        return tarefasOrdenadas;
    }
}
