/**
 * Created by samue on 15/09/2017.
 */

$(document).ready(function () {

    var $span =  $(this).find('.label');
    var status = {
        concluido: "3 - Concluído",
        emExecucao: "2 - Em execução",
        cancelado: "2 - Em execução",
        aguardando: "1 - Aguardando execução",
    };
    var prioridade = {
        alta: "Alta",
        media: "Média",
        baixa: "Baixa",
    };
    $span.each(function () {
        var descricao =  $(this).text();
        if(descricao == status.concluido || descricao == 'Concluído') {
            $(this).addClass('label-success');
            $(this).removeClass('label-info');
            $(this).text(status.concluido);
        } else if(descricao == status.emExecucao || descricao == prioridade.baixa || descricao == 'Em execução'){
            $(this).addClass('label-info');
            $(this).removeClass('label-success');
            if(descricao = descricao == 'Em execução'){
                $(this).text(status.emExecucao);
            }
        } else if(descricao == status.cancelado || descricao == prioridade.alta || descricao == 'Cancelado'){
            $(this).addClass('label-danger');
        } else if(descricao == status.aguardando || descricao == prioridade.media || descricao == 'Aguardando execução'){
            $(this).addClass('label-warning');
            if(descricao = descricao == 'Aguardando execução') {
                $(this).text(status.aguardando);
            }

        }
    });

    $(".datePicker").datepicker({
        format: "dd/mm/yyyy"
    });

    $(".date-column").each(function () {
        var data =  $(this).text();
        data = moment(data, 'YYYY-MM-DD').format('DD/MM/YYYY');
        $(this).text(data);
    });
    $('.alert').fadeOut(7000);
});





