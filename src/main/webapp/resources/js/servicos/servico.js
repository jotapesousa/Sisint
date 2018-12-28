$(document).ready( function () {

    var pathRemocao = $('#btnRemoverServico').attr('href');

    $('.remover-servico').click( function () {
        var id = $(this).attr('id-servico');
        var url = pathRemocao + id;
        $('#btnRemoverServico').attr('href', url);
    });
});