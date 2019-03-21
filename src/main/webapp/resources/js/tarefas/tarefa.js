$(document).ready( function () {
    var pathConcluir = $('#btnConcluirTarefa').attr('href');

    // Caminho para concluir tarefa selecionada é atribuida ao campo HREF do botão de id 'btnConcluirTarefa'(CONCLUIR)
    $('.concluir-tarefa').click( function () {
        var id = $(this).attr("id-tarefa");
        var url = pathConcluir + id;
        $('#btnConcluirTarefa').attr('href', url);
    });

    // Verifica inputs em branco do formulário de Nova Tarefa ao clicar no botão de id 'btnAdicionarTarefa'(ADICIONAR TAREFA)
    $('#btnAdicionarTarefa').click( function () {
       verifcarInputsTarefa();
    });

    //
    $('#titulo-tarefa').keyup( function (event) {
       console.log("titulo tarefa: " + $('#titulo-tarefa').val());
       verifcarInputsTarefa();
    });

    $('#data-fechamento-tarefa').datepicker().on('changeDate', function (ev) {
        console.log("data finalizacao tarefa: " + $('#data-fechamento-tarefa').val());
        verifcarInputsTarefa();
    });

    $('#status-tarefa').change( function (event) {
       console.log("Status tarefa: " + $('#status-tarefa').val());
       verifcarInputsTarefa();
    });

    $('#tecnico-tarefa').change( function (event) {
       console.log("tecnico tarefa: " + $('#tecnico-tarefa').val());
       verifcarInputsTarefa();
    });

    $('#descricao-tarefa').keyup( function (event) {
       console.log("Descricao tarefa: " + $('#descricao-tarefa').val());
       verifcarInputsTarefa();
    });

    function verifcarInputsTarefa() {
        if ($('#titulo-tarefa').val() == "" || $('#data-fechamento-tarefa').val() == "" ||
            $('#status-tarefa').val() == "" || $('#tecnico-tarefa').val() == "" || $('#descricao-tarefa').val() == ""){
            $('#btnSalvarTarefa').attr("disabled", "disabled");
        } else {
            $('#btnSalvarTarefa').removeAttr("disabled");
        }
    }
});