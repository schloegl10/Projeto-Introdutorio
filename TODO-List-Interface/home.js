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