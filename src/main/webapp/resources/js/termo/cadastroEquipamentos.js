$(document).ready( function () {
    var $form = $('#formTermo');
    var $equipamentosContainer = $('#equipamentoCadastrado');
    var $btnBuscarEquipamento = $('#btn-buscarEquipamento');
    var $btnEquipamentoSelecionado = $('.btnLinkId');
    var $inputEquipamentoPorNS = $('#busca-equipamentoPorNS');
    var $inputEquipamentoPorTombo = $('#busca-equipamentoTombo');
    var ctx = $('#ctx').val();
    var listaEquipamentos = [];
    var equipamentoSelecionado;
    var equipamento = {};
    var xTriggered = 0;
    var url = "";
    var $containerInputsEquipamento = $('#container-inputs-equipamento');


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

    // Pesquisa equipamento por numero de série a partir de letra digitada
    $inputEquipamentoPorNS.keyup(function ( event ) {
        var texto = $inputEquipamentoPorNS.val();
        url = "/manutencao" +"/" +'buscarEquipamentos?texto='+texto;
        pesquisar(texto,url);
    });

    // Pesquisa equipamento por tombo a partir de letra digitada
    $inputEquipamentoPorTombo.keyup(function ( event ) {
        var texto = $inputEquipamentoPorTombo.val();
        var url = "/manutencao" +"/" +'buscarEquipamentosPorTombo?tombo='+texto;
        pesquisar(texto,url);
    });

    // Solicitação ao servidor de dados do equipamento via JSON
    function pesquisar(texto, url) {
        $.ajax({
            dataType: 'json',
            type: 'GET',
            url: url
        }).done(function (data) {
            $('#equip-body').empty();
            if (data.length == 0) {
                $('#nenhumEquipamento').text("Nenhum Equipamento Encontrado");
            } else {
                $('#nenhumEquipamento').text("");
                listaEquipamentos = data;
                var pos = 0;
                // recuperar equipamento atraves de listener numa variavel equipamento
                listaEquipamentos.forEach(function (equipamento) {
                    // console.log(dado.id + "\nNome: " + dado.nome + "\nTOMBO: " + dado.tombo + "\nNS: " + dado.nserie +
                    // "\nDescricao: " + dado.descricao + "\nStatus: " + dado.status);
                    //equipamentos.push(dado.nome);
                    $('#equip-body').prepend("<tr id='trequip-"+equipamento.id+"'>"
                        +"<td>"+equipamento.nome+"</td>"
                        +"<td>"+equipamento.tombo+"</td>"
                        +"<td>"+equipamento.nserie+"</td>"
                        +"<td>"+customizarStatus(equipamento)+"</td>"
                        +"<td class='text-center btnLinkId'><a class='btns-eq' posicao='" + pos + "' id-equip='"+equipamento.id+"' href='#'"
                        +" alt='Selecionar'><i class='glyphicon glyphicon-ok'></i></a></td>"
                        +"</tr>");
                    pos++;
                });
            }
        }).fail(function () {

        }).always(function () {
        });
    }

    function customizarStatus(equipamento) {
        if(equipamento.status == 'OK') {
            return "<span class='label label-success'>OK</span>";
        } else if(equipamento.status == 'Em Conserto' || equipamento.status == 'EM_CONSERTO') {
            return "<span class='label label-warning'>Em Conserto</span>";
        } else if(equipamento.status == 'Quebrado' || equipamento.status == 'QUEBRADO') {
            return "<span class='label label-danger'>Quebrado</span>";
        }
    }

    // Ao selecionar equipamento, os dados da linha da tabela serão adicionados a um vetor
    $(document).on('click','.btns-eq',function () {
        var id = $(this).attr('id-equip');
        var posicao = $(this).attr('posicao');

        criarInputHidden(listaEquipamentos[posicao], posicao);
        gerarInputsEquipamento(listaEquipamentos[posicao], posicao);

        $('#equipamentoCadastrado').removeAttr("hidden");

        // limparInputs();
        // editando = false;
        //
        // $btnsEditar = $(".editar-equipamento");
        // $btnsRemover = $(".remover-equipamento");
        // atribuirListennerBtnEdicao($btnsEditar);
        // atribuirListennerBtnRemocao($btnsRemover);
    });

    function criarInputHidden(equipamento, pos) {
        $containerInputsEquipamento.prepend("<input " +
            "hidden id='equipamento-termo" + pos + "' name='termo.equipamentos[" + pos + "].id' " +
            "value='" + equipamento.id + "' />");
    }

    function gerarInputsEquipamento(equipamento) {
        // $equipamentosContainer.empty();
        $equipamentosContainer.prepend(
            "<div id='child"+equipamento.id+"' class='panel panel-primary'>" +
                "<div class='panel-heading'>" +
                "<h3 class='panel-title'>Equipamento</h3></div>"+
                "<div class='panel-body'>"+
                "<a id='editar-equipamento' class='editar-equipamento' id-equip='"+equipamento.id+"' href='#myModal' data-toggle='modal'" +
                "style='float: right;'><i class='fa fa-pencil-square-o'></i></a>" +
                "<a id='remover-equipamento' class='remover-equipamento' href='#' " +
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
        $btnsRemover = $('.remover-equipamento');
        atribuirListennerBtnRemocao($btnsRemover);
        console.log("AQUI TUDO BEM");
    }

    function remover(posicao) {
        listaEquipamentos.splice(posicao,1);
        $equipamentosContainer.empty();
        $containerInputsEquipamento.empty();
        var cont = 0;
        listaEquipamentos.forEach(function (equipamento) {
            criarInputsHidden($form, equipamento, cont);
            cont = cont + 1;
        });
    }

    function atribuirListennerBtnRemocao($btnRemocao) {
        $btnRemocao.off('click');
        $btnRemocao.each(function () {
            $(this).click(function () {
                var posicao = $(this).attr('posicao');
                posicaoEditavel = posicao;
                remover(posicao);
            });
        });
    }

    $(document).on('click', '.editar-tarefa', function () {
        console.log("ID equip: " + $(this).attr('id-equip'));
    });

});