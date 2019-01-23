

$(document).ready( function () {
    var $span =  $(this).find('.status-equip');

    // Cria label para o atributo de ID = status-equip
    $span.each(function () {
        var descricao =  $(this).text();
        if(descricao == 'OK') {
            $(this).addClass('label-success');
            $(this).removeClass('label-info');
        } else if(descricao == 'Em Conserto'){
            $(this).addClass('label-warning');
            $(this).removeClass('label-info');
        } else if(descricao == 'Quebrado'){
            $(this).addClass('label-danger');
        }
    });

    // Ao clicar no botão REMOVER este método irá adicionar o valor do atributo URL-REMOVER no atributo HREF do BTN-REMOVER
    $('.link-remover').click( function () {
        var valorUrl = $(this).attr("url-remover");
        console.log(valorUrl);
        $('#btn-remover').attr("href", valorUrl);
    });


    // Criação de novo EQUIPAMENTO via AJAX
    $(document).on('click','#cadastro-equip',function () {
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

        $.ajax({
            url: "/equipamento/salvarAjax",
            type: 'POST',
            data: { "equipamento.nome" : equipamento.nome,
                "equipamento.tombo" : equipamento.tombo,
                "equipamento.numeroSerie" :equipamento.numSerie,
                "equipamento.status" : equipamento.status,
                "equipamento.setor.id" : equipamento.setor,
                "equipamento.descricao" : equipamento.descricao,
                "equipamento.tipo" : equipamento.tipo
            }
        }).done(function (data) {
            console.log("OK ");
            equipamento.id = data.id;

            var cont = 0;
            $containerInputsEquipamento.empty();
            listaEquipamentos.forEach(function (equipamento) {
                criarInputsHidden($form, equipamento, cont);
                cont = cont + 1;
            });

            gerarInputsEquipamento(equipamento, cont);
            $('#equipamentoCadastrado').removeAttr("hidden");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log("Erro");
            alert("O serviço não foi salvo!");
        });
    });

    function gerarInputsEquipamento(equipamento, i) {
        // $equipamentosContainer.empty();
        $equipamentosContainer.prepend(
            "<div id='child"+equipamento.id+"' class='panel panel-primary'>" +
            "<input type='hidden' id='equipamento" + i + "' name='termo.equipamentos[" + i + "].id' value='"+ equipamento.id +"' />" +
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
    }

});