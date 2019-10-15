$(document).ready(function () {
    var urlBase = $('#ctx').val();


    function iniciarDashLinhas(data) {
        var categorias = [];
        var informacao = {
            name: "Chamados Abertos",
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

    function iniciarDashPie(data) {
        // Build the chart
        var info = [];
        data.servicos.forEach(function (servico) {
            var dado = {
                name : servico.setor,
                y: servico.porcentagem
            }
            info.push(dado);
        });
        Highcharts.chart('container-pizza', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Porcentagem de Serviços por Setor'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: info
            }]
        });
    }

    function iniciarDashBarra(data) {

        var info = [];
        data.servicos.forEach(function (informacao) {
            var dado = {
                name: informacao.setor,
                data: [informacao.quantidade]

            }
            info.push(dado)
        });

        Highcharts.chart('container-barra', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Quantidade de Chamados por Setor'
            },
            xAxis: {
                categories: [
                    ''
                ],
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total de Chamados'
                }
            },
            // tooltip: {
            //     headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            //     pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            //         '<td style="padding:0"><b>{point.y:.1f} veículos</b></td></tr>',
            //     footerFormat: '</table>',
            //     shared: true,
            //     useHTML: true
            // },
            series: info
        });
    }

    $.ajax({
        dataType: 'json',
        type: 'GET',
        // url: "http://localhost:8080/pcrn_sisint_war_exploded/info",
        url: "info",
        success: function (data) {
            console.log(data);
            iniciarDashLinhas(data);
            // iniciarDashPie(data);
            // iniciarDashBarra(data);

        }
    }).fail(function () {
        alert("Ops, algum problema ao solicitar os dados.")
    }).always(function () {

    });
});
