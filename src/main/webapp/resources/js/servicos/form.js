/**
 * Created by samue on 15/09/2017.
 */

$(function () {
    var btnSalvarTarefas = $('#btnt');
    var btnServico = $('#btnServico');

    // btnServico.click(function(evento) {
    //     evento.preventDefault();
    //
    //     var id = $("input[name='servico.id']");
    //     var titulo = $("input[name='servico.titulo']");
    //     var dataFechamento = $("input[name='servico.dataFechamento']");
    //     var descricao = $("textarea[name='servico.descricao']");
    //     var prioridade = $("select[name='servico.prioridade']");
    //     var tecnico = $("select[name='servico.tecnico.id']");
    //     var setor = $("select[name='servico.setor.id']");
    //
    //     var obj = moment(dataFechamento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
    //     var dateteste =  moment.utc(obj);
    //     var obj = dateteste.toISOString();
    //     // console.log(obj);
    //     var tecnicoResp = {};
    //     tecnicoResp.id = tecnico.val();
    //     var setorSolicitante = {};
    //     setorSolicitante.id = setor.val();
    //
    //
    //     var tarefas = {};
    //     tarefas.titulo = titulo.val();
    //     tarefas.dataFechamento = obj;
    //     tarefas.descricao = descricao.val();
    //     tarefas.tecnico = tecnicoResp;
    //
    //     // console.log(tarefas.titulo);
    //     // console.log(tarefas.dataFechamento);
    //     // console.log(tarefas.descricao);
    //     // console.log(tarefas.tecnico);
    //
    //     var servico = {};
    //     servico.id = id.val();
    //     servico.titulo = titulo.val();
    //     servico.dataFechamento = obj;
    //     servico.descricao = descricao.val();
    //     servico.prioridade = prioridade.val();
    //     servico.tecnico = tecnicoResp;
    //     servico.tarefas = tarefas;
    //     servico.setor = setorSolicitante;
    //
    //     var url = $("#urlSalvar").val();
    //
    //     console.log("url1: " + url);
    //
    //     // $.ajax({
    //     //     url: url,
    //     //     type: 'POST',
    //     //     data: servico
    //     // }).done(function(data) {
    //     // }).fail(function(jqXHR, textStatus, errorThrown) {
    //     //     console.log("Erro");
    //     //     alert("O serviço não foi salvo!");
    //     // });
    // });


    function validarDadosForm() {
        titulo = $("input[name='servico.titulo']").val();
        id = $("input[name='servico.id']").val();
        dataFechamento = $("input[name='servico.dataFechamento']").val();
        descricao = $("textarea[name='servico.descricao']").val();
        prioridade = $("select[name='servico.prioridade']").val();
        tecnico = $("select[name='servico.tecnico']").val();
        setor = $("select[name='servico.setor']").val();
    }

    $(".datePicker").datepicker({
        format: "dd/mm/yyyy"
    });

});