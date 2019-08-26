$(document).ready( function () {
    var pathConcluir = $('#btnConcluirTarefa').attr('href');

    // Caminho para concluir tarefa selecionada é atribuida ao campo HREF do botão de id 'btnConcluirTarefa'(CONCLUIR)
    $('.concluir-tarefa').click( function () {
        var id = $(this).attr("id-tarefa");
        var url = pathConcluir + id;
        $('#btnConcluirTarefa').attr('href', url);
        // console.log("url: " + $('#btnConcluirTarefa').val());
    });

    // Verifica inputs em branco do formulário de Nova Tarefa ao clicar no botão de id 'btnAdicionarTarefa'(ADICIONAR TAREFA)
    $('#btnAdicionarTarefa').click( function () {
       verifcarInputsTarefa();
    });

    $('#titulo-tarefa').keyup( function (event) {
       verifcarInputsTarefa();
    });

    $('#data-fechamento-tarefa').datepicker().on('changeDate', function (ev) {
        verifcarInputsTarefa();
    });

    $('#status-tarefa').change( function (event) {
       verifcarInputsTarefa();
    });

    $('#tecnico-tarefa').change( function (event) {
       verifcarInputsTarefa();
    });

    $('#descricao-tarefa').keyup( function (event) {
       verifcarInputsTarefa();
    });

    function verifcarInputsTarefa() {
        if ($('#titulo-tarefa').val() == "" || $('#data-fechamento-tarefa').val() == "" ||
            $('#status-tarefa').val() == "" || $('#tecnico-tarefa').val() == "" || $('#descricao-tarefa').val() == ""){
            $('#btnSalvarTarefa').attr("disabled", "disabled");
            $('#campoObrigatorio').text("*Todos os campos devem ser preenchidos.");
        } else {
            $('#btnSalvarTarefa').removeAttr("disabled");
            $('#campoObrigatorio').text("");
        }
    }
});