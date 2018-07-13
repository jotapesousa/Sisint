
$(document).ready( function () {
    var $span =  $(this).find('.status-manutencao');
    var $nome;
    var $tombo;
    var $nSerie;
    var $descricao;
    var $deletado;
    var statusEquipamento;
    var setor;

    // Cria label no Status da MANUTENÇÃO
    $span.each(function () {
        var descricao =  $(this).text();
        if(descricao == 'Concluído') {
            $(this).addClass('label label-success');
        } else if(descricao == 'Em Manutencao') {
            $(this).addClass('label label-info');
        } else if(descricao == 'Aguardando Manutencao') {
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
            var checarConserto = $('#checkConserto').val();
            var urlAssumir = $('#urlConcluir').val();
            var novaUrlAssumir = urlAssumir +"?id="+id;
            $('#btnConcluirManutencao').attr("href", novaUrlAssumir);
            console.log($('#btnConcluirManutencao').attr("href"));
        });
    });

    $('#checkConserto').change(function () {
        var checarConserto;
        var novaUrl;

        if($(this).is(':checked')) {
            $('#checkConserto').val("OK");
        }else {
            $('#checkConserto').val("QUEBRADO");
        }

        checarConserto = $('#checkConserto').val();
        novaUrl = $('#btnConcluirManutencao').attr("href") + "&checkConserto=" + checarConserto;
        $('#btnConcluirManutencao').attr("href", novaUrl);

        console.log(novaUrl);
    });

});