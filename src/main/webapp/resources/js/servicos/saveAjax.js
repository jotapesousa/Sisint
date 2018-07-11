
$(function () {
    console.log('Carregou com sucesso!');
    var tarefas = [];
    var btnSalvarTarefas = $('#btnAdicionar');
    var btnSalvarServico = $('#btnSalvarServico');

    btnSalvarTarefas.click(function(evento) {

        var statusTarefa = $("input[name = 'tarefa.statusTarefa']");
        var descricao = $("textarea[name = 'tarefa.descricao']");
        var prazo = $("input[name = 'tarefa.prazo']");
        var tecnico = $("input[name = 'tarefa.tecnico']");

        var obj = {};
        obj.tecnico = tecnico.val();
        obj.statusTarefa = statusTarefa.val();
        obj.descricao = descricao.val();
        obj.prazo = prazo.val();

        tarefas.push(obj);
        var data = $.ConverterObjetoParaVraptor("tarefa", obj);

        var url = $("#urlSalvar").val();

        statusTarefa.val('');
        descricao.val('');
        prazo.val('');
        tecnico.val('');
    });

    btnSalvarServico.on('click',function(evento) {

        var titulo = $("input[name='servico.titulo']");
        var dataAbertura = $("input[name='servico.dataAbertura']");
        var dataFechamento = $("input[name='servico.dataFechamento']");
        var descricao = $("textarea[name='servico.descricao']");
        var statusServico = $("select[name='servico.statusServico']");

        // console.log(titulo.val() + dataAbertura.val() + dataFechamento.val() + descricao.val());
        // var dateteste =  moment.utc(dataAbertura.val());
        var obj = moment(dataAbertura.val(), 'DD/MM/YYYY').format('YYYY/MM/DD')
        var dateteste =  moment.utc(obj);
        var obj = dateteste.toISOString();
        var objeto = {};
        objeto.titulo = titulo.val();
        objeto.descricao = descricao.val();
        objeto.tarefas = tarefas;
        objeto.dataFechamento = obj;
        objeto.dataAbertura = obj;
        objeto.statusServico = statusServico.val();
        // console.log(objeto.statusServico)

        console.log(moment.locale());



        var servico = $.ConverterObjetoParaVraptor("servico", objeto);

        console.log("Teste form" + objeto);

        var url = $("#urlSalvar").val();

        data = servico;

        $.ajax({

            url: url,

            type: 'POST',

            data: data

        }).done(function(data) {
            /* executada em caso de sucesso*/
            // window.location.href = "http://localhost:8080/servicos/lista"

        }).fail(function(jqXHR, textStatus, errorThrown) {
            /* executada em caso de erro*/
            alert("O serviço não foi salvo!");

        });
    });

});