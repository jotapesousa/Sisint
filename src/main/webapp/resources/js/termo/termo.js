$(document).ready( function () {


    $('#anoTermo').val(new Date().getFullYear());

    $('#btn-gerarns').click(function() {
        var ns = 'PC' + moment().year() + moment().unix() + 'RN';
        $('#nserie-equipamento').val(ns);
    });
});