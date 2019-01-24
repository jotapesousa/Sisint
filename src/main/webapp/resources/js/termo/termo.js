$(document).ready( function () {

    $("#numeroTermo").maskMoney({
        precision: 3,
        decimal: ''
    });

    $('#anoTermo').val(new Date().getFullYear());

    $('#btn-gerarns').click(function() {
        var ns = 'PC' + moment().year() + moment().unix() + 'RN';
        $('#nserie-equipamento').val(ns);
    });

    $('#termo-setor').change( function () {
        if ($('#termo-setor').val()) {
            // console.log($('#termo-setor :selected').text());
            $('#confirmarTermo').removeAttr("disabled");
        } else {
            $('#confirmarTermo').attr("disabled", "disabled");
        }
    });

    $('#confirmarTermo').click( function () {
        var termo = {
            codigoSei: $('#codSeiTermo').val(),
            matriculaServidor: $('#matServidorTermo').val(),
            nomeServidor: $('#nomeServidorTermo').val(),
            setor: $('#termo-setor').val()
        };

        $.ajax({
            url: "/termo/salvarAjax",
            type: 'POST',
            data: { "termo.codigoSei" : termo.codigoSei,
                    "termo.matriculaServidor" : termo.matriculaServidor,
                    "termo.nomeServidor" : termo.nomeServidor,
                    "termo.setor.id" : termo.setor
            }
        }).done(function (data) {
            $('#numeroTermo').val(data.id);
            $('#confirmarTermo').hide();
            $('#inserirEq').show();
            $('#salvarTermo').show();
            $('#codSeiTermo').attr("disabled", "disabled");
            $('#matServidorTermo').attr("disabled", "disabled");
            $('#nomeServidorTermo').attr("disabled", "disabled");
            $('#termo-setor').attr("disabled", "disabled");
            // equipamento.id = data.id;
            //
            // var cont = 0;
            // $containerInputsEquipamento.empty();
            // listaEquipamentos.forEach(function (equipamento) {
            //     criarInputsHidden($form, equipamento, cont);
            //     cont = cont + 1;
            // });

            // gerarInputsEquipamento(equipamento, cont);
            // $('#equipamentoCadastrado').removeAttr("hidden");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log("Erro");
            alert("O serviço não foi salvo!");
        });
    });


});