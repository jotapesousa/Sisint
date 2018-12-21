$(document).ready( function () {


    var pathSalvarNota = $('#btnAddNota').attr('href');

    $('.add_nota').click( function () {
       var id = $(this).attr('id-tarefa');
       $('#id_tarefa_nota').val(id);
    });
});