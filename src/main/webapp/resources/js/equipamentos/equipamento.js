

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

});