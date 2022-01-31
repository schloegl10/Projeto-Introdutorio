package TODO.List.schemas;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(name="Tarefa", description="Tarefa a ser realizada")
public class Tarefa {
    private String nome;
    private String descricao;
    private Date dataDeTermino;
    private String nivelDePrioridade;
    private String categoria;

    public Tarefa(String nome, String descricao, Date dataDeTermino, String nivelDePrioridade, String categoria, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeTermino = dataDeTermino;
        this.nivelDePrioridade = nivelDePrioridade;
        this.categoria = categoria;
        this.status = status;
    }

    private String status;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataDeTermino(Date dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public void setNivelDePrioridade(String nivelDePrioridade) {
        this.nivelDePrioridade = nivelDePrioridade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataDeTermino() {
        return dataDeTermino;
    }

    public String getNivelDePrioridade() {
        return nivelDePrioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getStatus() {
        return status;
    }
}
