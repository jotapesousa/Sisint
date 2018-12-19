$(document).ready( function () {

    var pathConcluir = $('#btnConcluirTarefa').attr('href');

    // CLICK chamado ao apertar botão para concluir tarefa
    $('.concluir-tarefa').click( function () {
        var id = $(this).attr("id-tarefa");
        var url = pathConcluir + id;
        $('#btnConcluirTarefa').attr('href', url);
    });
});