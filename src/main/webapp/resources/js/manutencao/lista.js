
$(document).ready( function () {
    var $nome;
    var $tombo;
    var $nSerie;
    var $descricao;
    var $deletado;
    var statusEquipamento;
    var setor;
    var checarConserto = $('#checkConserto').val();
    var $labelStatus = $(this).find('.labelStatus');

    // Cria label no Status do SERVIÇO
    $labelStatus.each(function () {
        var descricao =  $(this).text();
        console.log(descricao);
        if(descricao == 'Concluído') {
            $(this).addClass('label label-success');
        } else if(descricao == 'Em Manutenção') {
            $(this).addClass('label label-info');
        } else if(descricao == 'Aguardando Manutenção') {
            $(this).addClass('label label-warning');
        }
    });


    // Ao clicar em ASSUMIR MANUTENCAO este método irá incluir a ID da manutenção no HREF
    $('.assumir-manutencao').each( function () {
        $(this).click( function () {
            var id = $(this).attr("id-manutencao");
            var urlAssumir = $('#urlAssumir').val();
            var novaUrlAssumir = urlAssumir +"?id="+id;
            $('#btnAssumirManutencao').attr("href", novaUrlAssumir);
            console.log($('#btnAssumirManutencao').attr("href"));
        });
    });

    // Ao clicar em CONCLUIR MANUTENCAO este método irá incluir a ID da manutenção no HREF
    $('.concluir-manutencao').each( function () {
        $(this).click( function () {
            var id = $(this).attr("id-manutencao");
            console.log("CHECK CONSERTO: " + checarConserto);
            var urlAssumir = $('#urlConcluir').val();
            var novaUrlAssumir = urlAssumir +"?id="+id;
            $('#btnConcluirManutencao').attr("href", novaUrlAssumir);
            console.log($('#btnConcluirManutencao').attr("href"));
        });
    });

    // Ao clidar no botão CONCLUIR do modal CONCLUIR este método irá incluir o CHECKBOX de consertado ou não no HREF
    $('#btnConcluirManutencao').each( function () {
        $(this).click( function () {
           var novaUrlConcluir = $('#btnConcluirManutencao').attr("href");
           var descricaoFinal = $('#descricaoFinal-manutencao').val();

           novaUrlConcluir += "&checkConserto=" + checarConserto + "&descricaoFinal=" + descricaoFinal;
            $('#btnConcluirManutencao').attr("href", novaUrlConcluir);
            console.log($('#btnConcluirManutencao').attr("href"));
        });
    });

    // Ao clicar no botão REMOVER este método irá adicionar o valor do atributo URL-REMOVER no atributo HREF do BTN-REMOVER
    $('.removerManutencao').click( function () {
        var valorUrl = $(this).attr("url-remover");
        console.log(valorUrl);
        $('#btnRemover').attr("href", valorUrl);
    });

    // Troca do valor do CHECKBOX de Consertado ou não
    $('#checkConserto').change(function () {
        if($(this).is(':checked')) {
            $('#checkConserto').val("OK");
        }else {
            $('#checkConserto').val("QUEBRADO");
        }
        checarConserto = $('#checkConserto').val();
        console.log("CHECK CONSERTO: " + checarConserto);
        console.log($('#checkConserto').val());
    });

});