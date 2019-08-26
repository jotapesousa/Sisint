$(document).ready( function () {
    var pathDesativar = $('#btnDesativar').attr('href');

    // Caminho para desativar usuario selecionado é atribuido ao campo HREF do botão de id 'btnDesativar'(DESATIVAR)
    $('.desativar-usuario').click( function () {
        var id = $(this).attr("id-usuario");
        var url = pathDesativar + id;
        console.log(url);
        $('#btnDesativar').attr('href', url);
        console.log("url: " + $('#btnDesativar').attr('href'));
    });
});