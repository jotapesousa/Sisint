$(document).ready( function () {
    // Ao clicar no botão REMOVER este método irá adicionar o valor do atributo URL-REMOVER no atributo HREF do BTN-REMOVER
    $('.link-remover').click( function () {
        var valorUrl = $(this).attr("url-remover");
        console.log(valorUrl);
        $('#btn-remover').attr("href", valorUrl);
    });
});