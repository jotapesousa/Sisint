$(document).ready( function () {
    var $equipamentosContainer = $('#equipamentosTombados');
    var $inputEquipamentoPorTombo = $('#tomboEquip');
    var ctx = $('#ctxTermo').val();
    var tipoEquip = "";
    var equipamentosTermo = [];


    $("#numeroTermo").maskMoney({
        precision: 3,
        decimal: ''
    });

    $('#anoTermo').val(new Date().getFullYear());

    $('#btn-gerarns').click(function () {
        var ns = 'PC' + moment().year() + moment().unix() + 'RN';
        $('#nserie-equipamento').val(ns);
    });

    $('#termo-setor').change(function () {
        if ($('#termo-setor').val()) {
            console.log("termo-setor");
            // console.log($('#termo-setor :selected').text());
            $('#confirmarTermo').removeAttr("disabled");
            $('#tomboEquip').removeAttr("disabled");
        } else {
            console.log("sem nada");
            $('#confirmarTermo').attr("disabled", "disabled");
            $('#tomboEquip').attr("disabled", "disabled");
        }
    });

    $('#tipo-equip').change(function (event) {
        if ($('#tipo-equip').val()) {
            $('#tomboEquip').removeAttr("disabled");
            tipoEquip = $('#tipo-equip :selected').val();
        } else {
            $('#tomboEquip').attr("disabled", "disabled");
        }
    });

    function customizarStatus(dado) {
        if (dado.status == 'OK') {
            return "<span class='label label-success'>OK</span>";
        } else if (dado.status == 'Em Conserto' || dado.status == 'EM_CONSERTO') {
            return "<span class='label label-warning'>Em Conserto</span>";
        } else if (dado.status == 'Quebrado' || dado.status == 'QUEBRADO') {
            return "<span class='label label-danger'>Quebrado</span>";
        }
    }


    // Pesquisa equipamento por tombo a partir de letra digitada
    $inputEquipamentoPorTombo.keyup(function (event) {
        var tipoEquipamento = $('#tipo-equip :selected').val();
        var texto = $inputEquipamentoPorTombo.val();
        var url = ctx + "/equipamento/buscarEquipamentosPorTombo?tombo=" + texto + "&tipoEquip=" + tipoEquip;
        pesquisar(url);
    });

    function pesquisar(url) {
        // console.log(url);
        $.ajax({
            dataType: 'json',
            type: 'GET',
            url: url
        }).done(function (data) {
            // console.log(data);
            $('#equip-body').empty();
            if (data.length == 0) {
                $('#nenhumEquipamento').text("Nenhum Equipamento Encontrado");
                //console.log($('#nenhumEquipamento').child());
            } else {
                $('#nenhumEquipamento').text("");
                data.forEach(function (dado) {
                    $('#equip-body').prepend("<tr id='trequip-" + dado.id + "'>"
                        + "<td>" + dado.nome + "</td>"
                        + "<td>" + tipoEquip + "</td>"
                        + "<td>" + dado.tombo + "</td>"
                        + "<td>" + dado.nserie + "</td>"
                        + "<td>" + customizarStatus(dado) + "</td>"
                        + "<td class='text-center btnLinkId'><a class='btns-eq' id-equip='" + dado.id + "' href='#'"
                        + " alt='Selecionar' data-dismiss='modal'><i class='glyphicon glyphicon-ok'></i></a></td>"
                        + "</tr>");
                });
            }
        }).fail(function () {
            console.log("ERRO");
        });
    }

    // Ao selecionar equipamento, os dados da linha da tabela serão adicionados a um vetor
    $(document).on('click','.btns-eq',function () {
        var termo = {
            id: $('#termoId').val(),
            numero: $('#numeroTermo').val(),
            ano: $('#anoTermo').val(),
            setorId: $('#termo-setor').val(),
            equipamentos: equipamentosTermo
        }
        var equipamentosAttr = [];
        var id = $(this).attr('id-equip');
        $('#trequip-'+id).find('td').each(function () {
            equipamentosAttr.push($(this).text());
        });

        var equipamento = { };
        equipamento.id = id;
        equipamento.nome = equipamentosAttr[0];
        equipamento.tipo = equipamentosAttr[1];
        equipamento.tombo = equipamentosAttr[2];
        equipamento.numSerie = equipamentosAttr[3];
        equipamento.status = equipamentosAttr[4];

        equipamentosTermo.push(equipamento.id);

        salvarAjax(termo);
        gerarInputsEquipamento(equipamento);
        // $('#equipamentoCadastrado').removeAttr("hidden");
    });


    function gerarInputsEquipamento(equipamento) {
        // $equipamentosContainer.empty();
        $equipamentosContainer.prepend(
            "<tr>"+
                "<td>"+equipamento.nome+"</td>"+
                "<td>"+equipamento.tipo+"</td>"+
                "<td>"+equipamento.tombo+"</td>"+
                "<td>"+equipamento.numSerie+"</td>"+
                "<td>"+customizarStatus(equipamento.status)+"</td>"+
                "<td>"+
                    "<a id='editar-tarefa' class='editar-tarefa' href='#myModal' data-toggle='modal'" +
                        "style='float: right;'><i class='fa fa-pencil-square-o'></i></a>" +
                    "<a id='remover-tarefa' class='remover-tarefa' href='#' " +
                        "style='margin-right: 4px; float: right;'><i class='fa fa-trash-o'></i></a>" +
                "</td>"+
            "</tr>"
        );
        $('#table-equipamentos').removeAttr("hidden");
    }

    function salvarAjax(termo) {
        var urlSalvar = ctx + "/termo/salvarAjax";

        console.log(termo);
        $.ajax({
            url: urlSalvar,
            type: 'POST',
            data: {
                "termo.id": termo.id,
                "termo.numero": termo.numero,
                "termo.ano:": termo.ano,
                "termo.setor.id":termo.setorId,
                "termo.equipamentos": termo.equipamentos
            }
        }).done(function(data) {
            console.log(data);
            /* executada em caso de sucesso*/
            // window.location.href = "http://localhost:8080/servicos/lista"

        }).fail(function(jqXHR, textStatus, errorThrown) {
            /* executada em caso de erro*/
            console.log("ERRO AO SALVAR TERMO VIA AJAX")

        });
    }

});