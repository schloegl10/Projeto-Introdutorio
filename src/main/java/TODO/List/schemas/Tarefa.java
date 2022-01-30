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

    public void setNivelDePrioridade(Prioridade nivelDePrioridade) {
        this.nivelDePrioridade = nivelDePrioridade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStatus(Status status) {
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

    public Prioridade getNivelDePrioridade() {
        return nivelDePrioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public Status getStatus() {
        return status;
    }
}
