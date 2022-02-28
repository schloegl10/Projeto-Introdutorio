package TODO.List;

import TODO.List.service.TarefaService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

public class TarefaServiceTest {
    private static final String LOCAL_ARQUIVO = "./src/test/resources/listaDeTarefas.json";
    private static final String LOCAL_ARQUIVO_CRIACAO = "./src/test/resources/listaDeTarefasCriacao.json";
    TarefaService tarefaService = new TarefaService(LOCAL_ARQUIVO);
    private final String tarefaTest1 = "{\"prioridade\":\"5\",\"categoria\":\"4321\",\"nome\":\"aaa\",\"id\":1,\"termino\":\"12-12-2012\",\"descricao\":\"tarefa teste\",\"status\":\"123456\"}";
    private final String tarefaTest2 = "{\"prioridade\":\"1\",\"categoria\":\"1234\",\"nome\":\"aaa\",\"id\":2,\"termino\":\"12-12-2011\",\"descricao\":\"tarefa teste 2\",\"status\":\"123456\"}";
    private final String tarefaTest3 = "{\"prioridade\":\"2\",\"categoria\":\"1234\",\"nome\":\"aaa\",\"id\":3,\"termino\":\"12-12-2011\",\"descricao\":\"tarefa teste 2\",\"status\":\"123456\"}";
    @Test
    public void getIdTest() {
        String resultado = tarefaService.getId(1);
        assert Objects.equals(resultado, tarefaTest1);
    }

    @Test
    public void getCategoriaTest() {
        String resultado = tarefaService.getCategoria("4321", true);
        String resultadoEsperado = "[" + tarefaTest1 + "]";
        assert Objects.equals(resultado, resultadoEsperado);
    }

    @Test
    public void getCategoriaTest2() {
        String resultado = tarefaService.getCategoria("1234", true);
        String resultadoEsperado = "[" + tarefaTest2 + "," + tarefaTest3 + "]";
        assert Objects.equals(resultado, resultadoEsperado);
    }

    @Test
    public void getCategoriaTest3() {
        String resultado = tarefaService.getCategoria("1234", false);
        String resultadoEsperado = "[" + tarefaTest3 + "," + tarefaTest2 + "]";
        assert Objects.equals(resultado, resultadoEsperado);
    }

    @Test
    public void getAllTest1() {
        String resultado = tarefaService.getAll(true);
        String resultadoEsperado = "[" + tarefaTest2 + "," + tarefaTest3 + "," + tarefaTest1 + "]";
        assert Objects.equals(resultado, resultadoEsperado);
    }

    @Test
    public void getAllTest2() {
        String resultado = tarefaService.getAll(false);
        String resultadoEsperado = "[" + tarefaTest1 + "," + tarefaTest3 + "," + tarefaTest2 + "]";
        assert Objects.equals(resultado, resultadoEsperado);
    }

    @Test
    public void getcriaTarefaTest() {
        TarefaService tarefaServiceCriaTarefa = new TarefaService(LOCAL_ARQUIVO_CRIACAO);
        File myObj = new File(LOCAL_ARQUIVO_CRIACAO);
        myObj.delete();
        boolean resultado = tarefaServiceCriaTarefa.criaTarefa("aaa", "tarefa teste", "12-12-2012","5","4321","123456");
        assert resultado;
        String resultadoString = tarefaServiceCriaTarefa.getAll(true);
        String resultadoEsperado = "[" + tarefaTest1 + "]";
        assert Objects.equals(resultadoString, resultadoEsperado);
    }
}
