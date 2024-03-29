/**
 * Created by samue on 15/09/2017.
 */

$(document).ready(function () {

    var $span =  $(this).find('.label');
    var $labelStatus = $(this).find('.label-status');
    var $labelPrioridade = $(this).find('.label-prioridade');

    // Cria label no Status do SERVIÇO
    $labelStatus.each(function () {
        var descricao =  $(this).text();
        if(descricao == 'Concluído') {
            $(this).addClass('label label-success');
        } else if(descricao == 'Em Execução') {
            $(this).addClass('label label-info');
        } else if(descricao == 'Aguardando Execução') {
            $(this).addClass('label label-warning');
        }
    });

    // Cria label na prioridade do SERVIÇO
    $labelPrioridade.each(function () {
        var descricao =  $(this).text();
        if(descricao == 'Baixa') {
            $(this).addClass('label label-success');
        } else if(descricao == 'Média') {
            $(this).addClass('label label-warning');
        } else if(descricao == 'Alta') {
            $(this).addClass('label label-danger');
        }
    });

    $(".datePicker").datepicker({
        format: "dd/mm/yyyy"
    });

    $(".date-column").each(function () {
        var formatacao = $(this).text().split('-');
        var data = new Date(formatacao[0], formatacao[1] - 1, formatacao[2]);
        var novaData = data.toLocaleDateString();
        $(this).text(novaData);
    });

    $(".date-columnInput").each( function () {
        var formatacao = $(this).val().split('-');
        var data = new Date(formatacao[0], formatacao[1] - 1, formatacao[2]);
        var novaData = data.toLocaleDateString();
        $(this).val(novaData);
    });

    $('.alert').fadeOut(7000);
});





