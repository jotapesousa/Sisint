/*
 * Created by samuel on 15/09/2017.
 */
//Variável global salvar tarefas

$(document).ready(function () {
    var ctx = "";

    var idServico = $('#servico-id').val();
    var ctx = $('#ctx').val();
    var $form = $('#form-servico-tarefa');
    var $tarefasContainer = $('#tarefas-cadastradas');
    var tarefas = [];
    var $btnsEditar;
    var editando = false;
    var posicaoEditavel;
    var $btnsRemover = $('.remover-tarefa');
    var btnRemTarefa = $('#btnRemTarefa').attr("href");
    var listaTarefas = [];
    var id;
    var titulo;
    var telRetorno;
    var solicitante;
    var prioridade;
    var setor;
    var tecnico;
    var tecnicoNome;
    var descricao;
    var dataFechamento;
    var status;
    var prazo;
    var dateteste;
    var pendente;

    $('#btnTarefaPadrao').click(function (e) {
        e.preventDefault();

        if($('#btnTarefaPadrao').attr('disabled')) {
            console.log("Tarefa DISABLED");
        } else {
            console.log("Tarefa ENABLED");
        }

        var tituloServico = $('#titulo-servico').val();
        var dataFechamento = $('#data-fechamento-servico').val();
        var tecnicoResponsavel = $('#tecnico-servico').val();
        var descricaoServico = $('#servico-descricao').val();

        $('#titulo-tarefa').val(tituloServico);
        $('#data-fechamento-tarefa').val(dataFechamento);
        $('#tecnico-tarefa').val(tecnicoResponsavel);
        $('#descricao-tarefa').val(descricaoServico);
        $('#status-tarefa').val("EM_ESPERA");

        $('#btnSalvarTarefa').trigger('click');
        // $('#form-servico-tarefa').submit();
    });

    $('#btnAdicionarTarefa').click(function (e) {
        e.preventDefault();
        editando = false;
        carregarInputs();
        limparInputs();
    });

    var url = "";
    var urlLogs = ctx + "/listaLogs?id=" + idServico;
    var $containerInputsTarefa = $('#container-inputs-tarefa');

    $('#btnSalvarTarefa').click(function (e) {
        e.preventDefault();
        carregarInputs();

        prazo = moment(dataFechamento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
        var tarefa = {
            id: id.val(),
            titulo: titulo.val(),
            statusTarefa: (status.val() == null) ? null : {
                valor: status.val()
            },
            dataFechamento: prazo,
            descricao: descricao.val(),
            tecnico: {
                id: tecnico.val(),
                nome: tecnicoNome
            },
            pendente: pendente.is(':checked'),
            codigoTarefa:"",
            dataAbertura:""
        };

        $tarefasContainer.empty();
        if (!editando) {
            listaTarefas.push(tarefa);
        } else {
            tarefa.id = listaTarefas[posicaoEditavel].id;
            tarefa.codigoTarefa = listaTarefas[posicaoEditavel].codigoTarefa;
            tarefa.dataAbertura = listaTarefas[posicaoEditavel].dataAbertura;
            listaTarefas[posicaoEditavel] = tarefa;
        }
        var cont = 0;
        $containerInputsTarefa.empty();
        listaTarefas.forEach(function (tarefa) {
            criarInputsHidden($form, tarefa, cont);
            cont = cont + 1;
        });

        limparInputs();
        editando = false;

        $btnsEditar = $(".editar-tarefa");
        $btnsRemover = $(".remover-tarefa");
        atribuirListennerBtnEdicao($btnsEditar);
        atribuirListennerBtnRemocao($btnsRemover);

        $('#form-servico-tarefa').submit();
    });

    requisicaoTarefas();

    function requisicaoTarefas() {
        url = ctx + "/listaTarefas?id=" + idServico;

        $.ajax({
            dataType: 'json',
            type: 'GET',
            url: url
        }).done(function (data) {
            // console.log(data);
            listaTarefas.concat(criarTarefasEInserirNaLista(data));
            var cont = 0;
            listaTarefas.forEach(function (tarefa) {
                criarInputsHidden($form, tarefa, cont);
                cont = cont + 1;
            });
            console.log("tarefas: " + listaTarefas.length);
        }).fail(function () {
        }).always(function () {
            $btnsEditar = $(".editar-tarefa");
            atribuirListennerBtnEdicao($btnsEditar);
            // tabela.spin(false);
        });
    }

    function criarTarefasEInserirNaLista(data) {
        data.forEach(function (dado) {
            var tarefa = {
                id: (dado.id),
                titulo: dado.titulo,
                statusTarefa: (dado.statusValor == null) ? null : {
                    valor: dado.statusValor
                },
                dataFechamento: dado.dataFechamento,
                descricao: dado.descricao,
                tecnico: {
                    id: dado.tecnicoId,
                    nome: dado.tecnicoNome
                },
                codigoTarefa: dado.codigoTarefa,
                dataAbertura: dado.dataAbertura,
                pendente: dado.pendente
            };
            listaTarefas.push(tarefa)
        });
        return listaTarefas;
    };

    function criarInputsHidden($form, tarefa, i) {
        // $containerInputsTarefa.empty();
        $containerInputsTarefa.prepend("<input " +
            "hidden id='tarefa-servico-" + i + "' name='servico.tarefas[" + i + "].id' " +
            "value='" + tarefa.id + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].titulo' " +
            "value='" + tarefa.titulo + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].dataFechamento' " +
            "value='" + tarefa.dataFechamento + "'" +
            "/>");

        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].statusTarefa' " +
            "value='" + tarefa.statusTarefa.valor + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].descricao' " +
            "value='" + tarefa.descricao + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].tecnico.id' " +
            "value='" + tarefa.tecnico.id + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].codigoTarefa' " +
            "value='" + tarefa.codigoTarefa + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].dataAbertura' " +
            "value='" + tarefa.dataAbertura + "'" +
            "/>");
        $containerInputsTarefa.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].pendente' " +
            "value='" + tarefa.pendente + "'" +
            "/>");
        criarWellTarefa(tarefa, i);
    }

    function criarWellTarefa(tarefa, i) {
        var classe = criarLabelStatus(tarefa.statusTarefa.valor);
        var pendencia = tarefa.pendente;
        var possuiPendencia ="";
        if(pendencia === true || pendencia === 'true'){
            possuiPendencia = "<span class='list-group-item-text label label-danger'>Possui Pendencia</span>";
        }
        $tarefasContainer.prepend(
            "<div id='list-tarefa' class='list-group-item' >"+
                "<a id='editar-tarefa' class='editar-tarefa' href='#myModal' data-toggle='modal' posicao='"+ i +"' style='float: right;'>" +
                    "<i class='fa fa-pencil-square-o'></i></a>" +
                "<a id='remover-tarefa' class='remover-tarefa' href='#modalRemTarefa' data-toggle='modal' posicao='"+ i +"' style='margin-right: 4px; float: right;'>" +
                    "<i class='fa fa-trash-o'></i></a>" +
                "<h4 class='list-group-item-heading'>"+tarefa.titulo+"</h4>" +
                "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                    "Técnico: "+ tarefa.tecnico.nome + " </span><br>" +
                "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                    "Previsão: "+moment(tarefa.dataFechamento, "YYYY-MM-DD").format("DD/MM/YYYY")+"</span><br>"+
                "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                    "Status: " +
                    "<span class='label label-status "+classe+"'>"+chaveStatus(tarefa.statusTarefa.valor)+"</span></span>"+ possuiPendencia +
                "<p class='list-group-item-text'>Descrição: "+tarefa.descricao+"</p>"+
            "</div>"
        );
        $btnsRemover = $('.remover-tarefa');
        atribuirListennerBtnRemocao($btnsRemover);
    }

    function criarLabelStatus(status) {
        if(status === 'EM_EXECUCAO') {
            return 'label-info'
        } else if (status === 'CONCLUIDO') {
            return 'label-success'
        } else if (status === 'EM_ESPERA') {
            return 'label-warning'
        } else if (status === 'CANCELADO'){
            return 'label-danger'
        }
    }

    function atribuirListennerBtnEdicao($btnEditar) {
        $btnsEditar.off('click');
        $btnsEditar.each(function () {
            $(this).click(function () {
                var posicao = $(this).attr('posicao');
                posicaoEditavel = posicao;
                edicao(posicao);
            });
        });
    }

    function remover(posicao) {
        var idRemover = listaTarefas[posicao].id;
        var urlRemover = btnRemTarefa + idRemover;
        console.log(urlRemover);
        $('#btnRemTarefa').attr("href", urlRemover);
    }

    function atribuirListennerBtnRemocao($btnRemocao, tarefa, i) {
        $btnRemocao.off('click');
        $btnRemocao.each(function () {
            $(this).click(function () {
                var posicao = $(this).attr('posicao');
                var idTarefa = "'#tarefa-servico-"+posicao+"'";
                posicaoEditavel = posicao;
                remover(posicao);
            });
        });
    }

    function edicao(posicao) {
        carregarInputs();
        editando = true;
        tarefa = listaTarefas[posicao];
        id.val(tarefa.id);
        titulo.val(tarefa.titulo);
        tecnico.val(tarefa.tecnico.id);
        descricao.val(tarefa.descricao);
        status.val(tarefa.statusTarefa.valor);
        dataFechamento.val(moment(tarefa.dataFechamento).format('DD/MM/YYYY'));
    }

    function carregarInputs() {
        id = $("input[name='tarefa.id']");
        titulo = $("input[name='tarefa.titulo']");
        tecnico = $("select[name='tarefa.tecnico']");
        tecnicoNome = $("#tecnico-tarefa :selected").text();
        descricao = $("textarea[name='tarefa.descricao']");
        dataFechamento = $("input[name='tarefa.dataFechamento']");
        status = $("select[name='tarefa.statusTarefa']");
        pendente = $("input[name='tarefa.pendente']");

        prazo = moment(dataFechamento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
        dateteste = moment.utc(prazo);
        prazo = dateteste.toISOString();

        // console.log("tecnico nome: " + tecnicoNome);
    }

    // function carregarInputsServico() {
    //     id = $("input[name='servico.id']");
    //     titulo = $("input[name='servico.titulo']");
    //     telRetorno = $("input[name='servico.telefoneRetorno']");
    //     solicitante = $("input[name='servico.nomeSolicitante']");
    //     setor = $("select[name='servico.setor.id']");
    //     tecnico = $("select[name='servico.tecnico.id']");
    //     tecnicoNome = $("#servico-tecnico :selected").text();
    //     descricao = $("textarea[name='servico.descricao']");
    //     dataFechamento = $("input[name='servico.dataFechamento']");
    //     prioridade = $("select[name='servico.prioridade']");
    //
    //     prazo = moment(dataFechamento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
    //     dateteste = moment.utc(prazo);
    //     prazo = dateteste.toISOString();
    //
    //     // console.log("tecnico nome: " + tecnicoNome);
    // }

    function limparInputs() {
        id.val("");
        titulo.val("");
        tecnico.val("");
        descricao.val("");
        dataFechamento.val("");
        status.val("");
    }

    function chaveStatus(valor) {
        if(valor === 'CANCELADO'){
            return 'Cancelado';
        } else if (valor === 'EM_ESPERA') {
            return 'Aguardando Execução'
        } else if (valor === 'EM_EXECUCAO') {
            return 'Em execução';
        } else if (valor === 'CONCLUIDO') {
            return 'Concluído';
        }
    }

    $(".datePicker").datepicker({
        format: "dd/mm/yyyy"
    });

});