$(document).ready( function () {

    var pathRemocao = $('#btnRemoverServico').attr('href');
    var ctx = $('#ctx').val();

    $('.remover-servico').click( function () {
        var id = $(this).attr('id-servico');
        var url = pathRemocao + id;
        $('#btnRemoverServico').attr('href', url);
    });


    $('#titulo-servico').keyup( function (event) {
        verificarInputsServico();
    });

    $('#telRetorno-servico').keyup( function (event) {
        verificarInputsServico();
    });

    $('#nomeSolicitante-servico').keyup( function (event) {
        verificarInputsServico();
    });

    $('#setor-servico').change( function (event) {
        var url = ctx + "/buscarSetorJson?id=" + $('#setor-servico').val();
        if ($('#setor-servico').val() != "") {
            $.ajax( {
                url : url,
                dataType : 'json',
                type : 'GET'
            }).done(function (data) {
                // console.log(data.telJson);
                if (data.telJson == "") {
                    $('#telRetorno-servico').val("SEM TELEFONE");
                } else {
                    $('#telRetorno-servico').val(data.telJson);
                }
                verificarInputsServico();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("O setor nao foi encontrado!");
            });
        } else {
            $('#telRetorno-servico').val("");
            verificarInputsServico();
        }
    });

    $('#data-fechamento-servico').datepicker().on('changeDate', function (ev) {
        verificarInputsServico();
    });

    $('#prioridade-servico').change( function (event) {
        verificarInputsServico();
    });

    $('#tecnico-servico').change( function (event) {
        verificarInputsServico();
    });

    $('#servico-descricao').keyup( function (event) {
        verificarInputsServico();
    });

    function verificarInputsServico() {
        if ($('#titulo-servico').val() == "" || $('#telRetorno-servico').val() == "" || $('#nomeSolicitante-servico').val() == "" ||
            $('#setor-servico').val() == "" || $('#data-fechamento-servico').val() == "" || $('#prioridade-servico').val() == "" ||
            $('#tecnico-servico').val() == "" || $('#servico-descricao').val() == "") {
            $('#btnAdicionarTarefa').attr("disabled", "disabled");
        } else {
            $('#btnAdicionarTarefa').removeAttr("disabled");
        }
    }
});