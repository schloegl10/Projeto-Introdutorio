const uri = 'http://localhost:8080/tarefas/';
let todos = [];
const uriGetAll = 'http://localhost:8080/tarefas/getallprioridade?';
const uriCriacao = 'http://localhost:8080/tarefas/cria?'
var myFunc = function () {
  const dados = {
    crescente: true
  };
  var url = uriGetAll + new URLSearchParams(dados).toString();

  fetch(url, {
    method: 'GET',
    mode: 'no-cors',
    headers: {
      'Accept': '*/*',
      'Content-Type': 'text/plain'
    }
  })
    .then(response => response.json())
    .then(data => {
      validaResposta(data);
    })
    .catch(error => console.error("", error));
}();

function buscaTarefas() {
    const crescente = document.getElementById('crescente').checked;
    const id = document.getElementById('id').value.trim();
    const categoria = document.getElementById('categoria').value.trim();
    var dados;
    var uriRequisicao;
    if(categoria != '') {
        dados = {
            categoria: categoria,
            crescente: crescente
        }
        uriRequisicao = uri + 'getcategoria?'
    } else if (id != '') {
              dados = {
                  id: id
              }
              uriRequisicao = uri + 'get?'
    } else if (categoria == '') {
              dados = {
                  crescente: crescente
              }
              uriRequisicao = uri + 'getallprioridade?'
    }


  var url = uriRequisicao + new URLSearchParams(dados).toString();

  fetch(url, {
    method: 'GET',
    mode: 'no-cors',
    headers: {
      'Accept': '*/*',
      'Content-Type': 'text/plain'
    }
  })
    .then(response => response.json())
    .then(data => {
      validaResposta(data);
    })
    .catch(error => console.error("", error));
}


function validaResposta(data) {
  if (data.match('erros: ')) {
    exibeErro(data);
  } else {
    exibTarefas(data);
  }
}

function exibTarefas(data) {
  document.getElementById('tarefas').innerText = data;
  document.getElementById('tarefasForm').style.display = 'block';
  document.getElementById('erroDiv').style.display = 'none';
}

function exibeErro(data) {
  document.getElementById('erro').innerText = data;
  document.getElementById('tarefasForm').style.display = 'none';
  document.getElementById('erroDiv').style.display = 'block';
}

function criaTarefa() {
    const nome = document.getElementById('nome').value.trim();
    const descricao = document.getElementById('descricao').value.trim();
    const dataDeTermino = document.getElementById('dataDeTermino').value.trim();
    const nivelDePrioridade = document.getElementById('nivelDePrioridade').value.trim();
    const categoria = document.getElementById('categoria').value.trim();
    const status = document.getElementById('status').value.trim();
    var regexNivelPrioridade = /^[12345]$/;
    var nivelDePrioridadeRegex = nivelDePrioridade.match(regexNivelPrioridade);
    var regexData = /^\d{2}\/\d{2}\/\d{4}$/;
    var dataRegex = dataDeTermino.match(regexData);
    var dataAtual = new Date();
    if(nivelDePrioridadeRegex == null) {
        exibeErro('Nivel De Prioridade não é um número entre 1 e 5');
        return;
    }
    if(dataRegex == null) {
        exibeErro('Data não está no formato dd/MM/yyyy');
        return;
    }
    var dataDeTerminoDate = new Date(dataDeTermino);
    if(dates.compare(dataAtual, dataDeTerminoDate) > 0) {
        exibeErro('Data é anterior ao dia atual');
        return;
    }
    const dados = {
        nome: nome,
        descricao: descricao,
        dataDeTermino: dataDeTermino,
        nivelDePrioridade: nivelDePrioridade,
        categoria: categoria,
        status: status
    };


  var url = uriCriacao + new URLSearchParams(dados).toString();

  fetch(url, {
    method: 'POST',
    mode: 'no-cors',
    headers: {
      'Accept': '*/*',
      'Content-Type': 'text/plain'
    }
  })
    .then(response => response.json())
    .then(data => {
      validaResposta(data);
    })
    .catch(error => console.error("", error));
}

var dates = {
    convert:function(d) {
        return (
            d.constructor === Date ? d :
            d.constructor === Array ? new Date(d[0],d[1],d[2]) :
            d.constructor === Number ? new Date(d) :
            d.constructor === String ? new Date(d) :
            typeof d === "object" ? new Date(d.year,d.month,d.date) :
            NaN
        );
    },
    compare:function(a,b) {
        // Compare two dates (could be of any type supported by the convert
        // function above) and returns:
        //  -1 : if a < b
        //   0 : if a = b
        //   1 : if a > b
        // NaN : if a or b is an illegal date
        // NOTE: The code inside isFinite does an assignment (=).
        return (
            isFinite(a=this.convert(a).valueOf()) &&
            isFinite(b=this.convert(b).valueOf()) ?
            (a>b)-(a<b) :
            NaN
        );
    }
}