
$(document).ready(function () {

    var $form = $('#form-manutencao-equip');
    var $containerInputsEquipamento = $('#container-inputs-equipamento');

    var $equipamentoContainer = $('#equipamentoCadastrado');
    var $btnBuscarEquipamento = $('#btn-buscarEquipamento');
    var $inputEquipamentoPorNS = $('#busca-equipamentoPorNS');
    var $inputEquipamentoPorTombo = $('#busca-equipamentoTombo');
    var ctx = $('#ctx').val();
    var equipamento = {};
    var xTriggered = 0;
    var url = "";

    if ($('#manutencao-id').val()) {
        $('#equipamentoCadastrado').removeAttr("hidden");
    }


    // $btnBuscarEquipamento.click(function () {
    //     var texto = $inputEquipamento.val();
    //     console.log(texto);
    //     pesquisar(texto);
    // });

    // Pesquisa equipamento por numero de série a partir de letra digitada
    $inputEquipamentoPorNS.keyup(function ( event ) {
        var texto = $inputEquipamentoPorNS.val();
        url = "/manutencao" +"/" +'buscarEquipamentos?texto='+texto;
        pesquisar(texto,url);
    });

    // Pesquisa equipamento por tombo a partir de letra digitada
    $inputEquipamentoPorTombo.keyup(function ( event ) {
        console.log(ctx);
        var texto = $inputEquipamentoPorTombo.val();
        var url = "/manutencao" +"/" +'buscarEquipamentosPorTombo?tombo='+texto;
        pesquisar(texto,url);
    });

    // Muda a aba de pesquisa caso o CheckBox para pesquisar por tombo esteja selecionado
    $('#check-ns').change(function () {
        if($(this).is(':checked')) {
            $inputEquipamentoPorNS.show();
            $inputEquipamentoPorTombo.val("");
            $('#equip-body').empty();
            $inputEquipamentoPorTombo.hide();
        }else {
            $inputEquipamentoPorNS.hide();
        }
    });

    // Muda a aba de pesquisa caso o CheckBox para pesquisar por numero de serie esteja selecionado
    $('#check-tombo').change(function () {
        if($(this).is(':checked')) {
            $inputEquipamentoPorTombo.show();
            $inputEquipamentoPorNS.val("");
            $('#equip-body').empty();
            $inputEquipamentoPorNS.hide();
        }else {
            $inputEquipamentoPorTombo.hide();
        }
    });

    function pesquisar(texto, url) {
        $.ajax({
            dataType: 'json',
            type: 'GET',
            url: url
        }).done(function (data) {
            $('#equip-body').empty();
            if (data.length == 0) {
                $('#nenhumEquipamento').text("Nenhum Equipamento Encontrado");
                //console.log($('#nenhumEquipamento').child());
            } else {
                $('#nenhumEquipamento').text("");
                data.forEach(function (dado) {
                    // console.log(dado.id + "\nNome: " + dado.nome + "\nTOMBO: " + dado.tombo + "\nNS: " + dado.nserie +
                    // "\nDescricao: " + dado.descricao + "\nStatus: " + dado.status);
                    //equipamentos.push(dado.nome);
                    $('#equip-body').prepend("<tr id='trequip-"+dado.id+"'>"
                        +"<td>"+dado.nome+"</td>"
                        +"<td>"+dado.tombo+"</td>"
                        +"<td>"+dado.nserie+"</td>"
                        +"<td>"+customizarStatus(dado)+"</td>"
                        +"<td class='text-center btnLinkId'><a class='btns-eq' id-equip='"+dado.id+"' href='#'"
                        +" alt='Selecionar' data-dismiss='modal'><i class='glyphicon glyphicon-ok'></i></a></td>"
                        +"</tr>");
                });
            }
        }).fail(function () {

        }).always(function () {
        });
    }

    function customizarStatus(dado) {
        if(dado.status == 'OK') {
            return "<span class='label label-success'>OK</span>";
        } else if(dado.status == 'Em Conserto' || dado.status == 'EM_CONSERTO') {
            return "<span class='label label-warning'>Em Conserto</span>";
        } else if(dado.status == 'Quebrado' || dado.status == 'QUEBRADO') {
            return "<span class='label label-danger'>Quebrado</span>";
        }
    }

    // Ao selecionar equipamento, os dados da linha da tabela serão adicionados a um vetor
    $(document).on('click','.btns-eq',function () {

        var equipamentos = [];
        var id = $(this).attr('id-equip');
        $('#trequip-'+id).find('td').each(function () {
            equipamentos.push($(this).text());
        });
        equipamento.id = id;
        equipamento.nome = equipamentos[0];
        equipamento.tombo = equipamentos[1];
        equipamento.numSerie = equipamentos[2];
        equipamento.status = equipamentos[3];
        gerarInputsEquipamento(equipamento);
        $('#equipamentoCadastrado').removeAttr("hidden");

    });

    // Criação de NOVO EQUIPAMENTO via ajax
    $(document).on('click','#cadastro-equip',function () {
        console.log("Criar equipamento ajax");
        var url = $('#urlSalvarEquipamento').val();
        var equipamento = {
            nome: $('#nome-equipamento').val(),
            tombo: $('#tombo-equipamento').val(),
            numSerie: $('#nserie-equipamento').val(),
            status: $('#status-equipamento').val(),
            setor: $('#setor-equipamento').val(),
            descricao: $('#descricao-equipamento').val(),
            tipo: $('#tipo-equipamento').val()
        };

        console.log(url);

        $.ajax({
            url: "/equipamento/salvarAjax",
            type: 'POST',
            data: { "equipamento.nome" : equipamento.nome,
                    "equipamento.tombo" : equipamento.tombo,
                    "equipamento.numeroSerie" :equipamento.numSerie,
                    "equipamento.status" : equipamento.status,
                    "equipamento.setor.id" : equipamento.setor,
                    "equipamento.descricao" : equipamento.descricao,
                    "equipamento.tipo" : equipamento.tipo }

        }).done(function (data) {
            console.log("OK ");
            console.log(data);
            equipamento.id = data.id;
            gerarInputsEquipamento(equipamento);
            $('#equipamentoCadastrado').removeAttr("hidden");

        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log("Erro");
            alert("O serviço não foi salvo!");
        });

    });

    function criarInputsHidden($form, equipamento, i) {
        // $containerInputsEquipamento.empty();
        $containerInputsEquipamento.prepend("<input " +
            "hidden id='equip-manutencao' name='manutencao.equipamento.id' value='" + equipamento.id + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.nome' value='" + equipamento.nome + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.tombo' value='" + equipamento.tombo + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.numeroSerie' value='" + equipamento.numeroSerie + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.tipo' value='" + equipamento.tipo.valor + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.deletado' value='" + equipamento.deletado + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.descricao' value='" + equipamento.descricao + "'/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='manutencao.equipamento.status' value='" + equipamento.status.valor + "'/>");

        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].titulo' " +
            "value='" + tarefa.titulo + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].dataFechamento' " +
            "value='" + tarefa.dataFechamento + "'" +
            "/>");

        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].statusTarefa' " +
            "value='" + tarefa.statusTarefa.valor + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].descricao' " +
            "value='" + tarefa.descricao + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].tecnico.id' " +
            "value='" + tarefa.tecnico.id + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].codigoTarefa' " +
            "value='" + tarefa.codigoTarefa + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].dataAbertura' " +
            "value='" + tarefa.dataAbertura + "'" +
            "/>");
        $containerInputsEquipamento.prepend("<input " +
            "hidden name='servico.tarefas[" + i + "].pendente' " +
            "value='" + tarefa.pendente + "'" +
            "/>");
        criarWellTarefa(tarefa, i);
    }


    function gerarInputsEquipamento(equipamento) {
        console.log(equipamento);
        $equipamentoContainer.empty();
        $equipamentoContainer.prepend(
            "<input type='hidden' name='manutencao.equipamento.id' value='" + equipamento.id+ "' />" +
            "<div id='' class='panel panel-primary'>" +
                "<div class='panel-heading'>" +
                    "<h3 class='panel-title'>Equipamento</h3></div>"+
                "<div class='panel-body'>"+
                    "<a id='editar-tarefa' class='editar-tarefa' href='#myModal' data-toggle='modal'" +
                        "style='float: right;'><i class='fa fa-pencil-square-o'></i></a>" +
                    "<a id='remover-tarefa' class='remover-tarefa' href='#' " +
                        "style='margin-right: 4px; float: right;'><i class='fa fa-trash-o'></i></a>" +
                    "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                            "Nome: " + equipamento.nome +"</span><br>"+
                    "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                            "Tombo: " + equipamento.tombo +"</span><br>"+
                    "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                            "Num. Série: " + equipamento.numSerie +"</span><br>"+
                    "<span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>" +
                            "Status: " + customizarStatus(equipamento)+"</span>" +
                "</div>" +
            "</div>"

        );
    }

    var substringMatcher = function(strs) {
        return function findMatches(q, cb) {
            var matches, substringRegex;

            // an array that will be populated with substring matches
            matches = [];

            // regex used to determine if a string contains the substring `q`
            substrRegex = new RegExp(q, 'i');

            // iterate through the pool of strings and for any string that
            // contains the substring `q`, add it to the `matches` array
            $.each(strs, function(i, str) {
                if (substrRegex.test(str)) {
                    matches.push(str);
                }
            });

            cb(matches);
        };
    };

    $('#fecharModal').click( function () {
        $('#busca-equipamentoPorNS').val("");
        $('#busca-equipamentoTombo').val("");
    });

    $(".datePicker").datepicker({
        format: "dd/mm/yyyy"
    });

});