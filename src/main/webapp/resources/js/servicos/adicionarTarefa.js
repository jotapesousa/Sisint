$(document).ready(function () {

    var editando = false;
    var id;
    var titulo;
    var tecnico;
    var descricao;
    var dataFechamento;
    var status;
    var pendente;
    var prazo;
    var dateteste;

    $('#btnAdicionarTarefa').click(function (e) {
        e.preventDefault();
        editando = false;
        carregarInputs();
        limparInputs();
    });

    $('#btnSalvarTarefa').click( function (e) {
        e.preventDefault();
        carregarInputs();

        console.log(id.val());
        console.log(titulo.val());
        console.log(tecnico.val());

    })

    function carregarInputs() {
        id = $("input[name='tarefa.id']");
        titulo = $("input[name='tarefa.titulo']");
        tecnico = $("select[name='tarefa.tecnico']");
        descricao = $("textarea[name='tarefa.descricao']");
        dataFechamento = $("input[name='tarefa.dataFechamento']");
        status = $("select[name='tarefa.statusTarefa']");
        pendente = $("input[name='tarefa.pendente']");

        prazo = moment(dataFechamento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
        dateteste = moment.utc(prazo);
        prazo = dateteste.toISOString();
        console.log("passou");
    }

    function limparInputs() {
        id.val("");
        titulo.val("");
        tecnico.val("");
        descricao.val("");
        dataFechamento.val("");
        status.val("");
    }



});