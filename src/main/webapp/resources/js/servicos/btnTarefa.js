$('#form-servico-tarefa').validator();

$('document').ready( function () {
    var valTecnico = $('#tecnico-servico').val();

    if (valTecnico != "") {
        $('#btnAdicionarTarefa').removeAttr('disabled');
    } else {
        $('#btnAdicionarTarefa').attr('disabled', 'disabled');
    }
});

function salvarTarefa() {
    console.log("SUBMIT para trigger");
    $('#btnTarefaPadrao').trigger('click');
}

function mudancaTecnico() {
    var valTecnico = $('#tecnico-servico').val();

    if (valTecnico != "") {
        $('#btnAdicionarTarefa').removeAttr('disabled');
    } else {
        $('#btnAdicionarTarefa').attr('disabled', 'disabled');
    }
}

function criarAviso() {
    // console.log("Vem aviso ai");
    var elemento_pai = document.querySelector('#corpoModalServico');
    var elemento = document.createElement('p');
    var texto = "";
    var numTarefas = $('#tarefas-cadastradas').children().length;

    // console.log("DIV: " + $('#tarefas-cadastradas').children().length);
    // console.log("numero tarefas :" + numTarefas);

    elemento_pai.innerHTML = "";

    if ($('#tecnico-servico').val() == "") {
        if (numTarefas > 0) {
            // console.log("sem tecnico com tarefas");
            $('#btnSalvarServico').attr('disabled', 'disabled');
            texto = document.createTextNode("Você deve selecionar um Técnico responspável pois criou tarefa(s)");
        } else {
            // console.log("sem tecnico sem tarefas");
            $('#btnSalvarServico').removeAttr('disabled');
            $('#btnTarefaPadrao').attr('disabled', 'disabled');
            $('#btnTarefaPadrao').attr('href', '#');
            texto = document.createTextNode("Você não selecionou nenhum técnico responsável. Deseja salvar o serviço em aberto?");
        }
    } else {
        if (numTarefas > 0) {
            // console.log("com tecnico com tarefas");
            $('#btnTarefaPadrao').attr('disabled', 'disabled');
            $('#btnTarefaPadrao').attr('href', '#');
            $('#btnSalvarServico').removeAttr('disabled');
            texto = document.createTextNode("Técnico selecionado e tarefa(s) criada(s). Você deseja manter os dados?");
        } else {
            // console.log("com tecnico sem tarefas");
            $('#btnTarefaPadrao').removeAttr('disabled');

            $('#btnSalvarServico').attr('disabled', 'disabled');
            texto = document.createTextNode("Você selecionou um técnico mas não adicionou nenhuma tarefa. Deseja salvar serviço com tarefa padrão?");
            // console.log($('#btnTarefaPadrao').attr('href'));
        }
    }
    elemento.appendChild(texto);
    elemento_pai.appendChild(elemento);
}


if ($(".datePicker").val()) {
    var data = moment($(".datePicker").val(), "YYYY-MM-DD").format("DD/MM/YYYY");
    $(".datePicker").val(data);
    // console.log($(".datePicker").val());
}