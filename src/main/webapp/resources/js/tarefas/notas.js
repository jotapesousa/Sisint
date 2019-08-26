$(document).ready( function () {
    var pathSalvarNota = $('#btnAddNota').attr('href');
    var tarefaId;

    $('.add_nota').click( function () {
        var id = $(this).attr('id-tarefa');
        $('#id_tarefa_nota').val(id);
    });

    $('#nota_tarefa').keyup( function () {
       if ($('#nota_tarefa').val().length > 5) {
           $('#btnAdicionar').removeAttr('disabled');
       } else {
           $('#btnAdicionar').attr('disabled', 'disabled');
       }
    });

    $('#cancelarNota').click( function () {
        $('#nota_tarefa').val("");
    });

    // $('#btnAddNota').click( function () {
    //     var url = $('#btnAddNota').attr('href');
    //     var descricaoNota = $('#nota_tarefa').val();
    //     console.log(descricaoNota);
    //     console.log(tarefaId);
    //
    //     $.ajax( {
    //         url : url,
    //         type : 'POST',
    //         data : {
    //             "id" : tarefaId,
    //             "descricaoNota" : descricaoNota
    //         }
    //     }).done(function (data) {
    //         console.log(data);
    //     }).fail(function (jqXHR, textStatus, errorThrown) {
    //        alert("A nota não foi adicionada!");
    //     });
    // });


});