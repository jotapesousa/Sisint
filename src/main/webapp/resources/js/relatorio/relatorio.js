// Funções para limpar datas de inicio e final do evento
function limparDataInicio() {
    console.log($('#dtDe_relatorio').val());
    $('#dtDe_relatorio').val('');
}

function limparDataFinal() {
    console.log($('#dtAte_relatorio').val());
    $('#dtAte_relatorio').val('');
}

$(document).ready( function () {
    $('.table').DataTable( {
        pageLength:25,
        "order" : [[ 1, "desc" ]],
        "language":
            {
                "sEmptyTable": "Nenhum registro encontrado",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                }
            }
    } );

    var urlFiltro = $('.link-filtrar').attr('url-filtrar');

    $('#filtroSetor').change( function () {
        var idSetor = $(this).val();
        var inicio = $('#dtDe_relatorio').val();
        var fim = $('#dtAte_relatorio').val();
        var urlFinalFiltro = urlFiltro + idSetor + '&dtDe=' + inicio + '&dtAte=' + fim;
        console.log(urlFinalFiltro);
        $('#btnFiltrar').attr('href', urlFinalFiltro);
    })

});