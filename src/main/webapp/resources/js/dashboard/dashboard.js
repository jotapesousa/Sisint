$(document).ready(function () {
    var urlBase = $('#ctx').val();

    console.log(urlBase);

    function iniciarDashLinhas(data) {
        var categorias = [];
        var informacao = {
            servicosAbertos: "Serviços Abertos",
            servicosFechados: "Serviços Fechados",
            data: []
        };
        data.dates.forEach(function (dado) {
            informacao.data.push(dado.quantidade);
            categorias.push(dado.date);
        });
        Highcharts.chart('container-linha', {

            chart: {
                type: 'line'
            },
            title: {
                text: 'Número de chamados por Mês'
            },
            xAxis: {
                categories: categorias
            },
            yAxis: {
                title: {
                    text: 'Quantidade'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [informacao]

        });

    }

    $.ajax({
        dataType: 'json',
        type: 'POST',
        url: urlBase + "Siscva/informacoes",
        data: {

        }
    }).done(function (data) {
        iniciarDashLinhas(data);
        // console.log(data);
    }).fail(function () {
        alert("Ops, algum problema ao solicitar os dados.")
    }).always(function () {

    });
});
