<%--
  Created by IntelliJ IDEA.
  User: samue
  Date: 08/09/2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<tags:layoutSidebar>

    <jsp:attribute name="cabecalho">
        <link href="${ctx}/resources/css/custom.css" rel="stylesheet"/>
        <style>
            .count_top{
                /*color: #1e684a;*/
                color: #44545b;
            }

            .count{
                /*color: #26946f;*/
                color: #758a91;
            }
            .titulo-informacao-dashboard {
                color: #44545b;
            }
            label {
                color: #44545b;
            }
N
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/js/dashboard/dashboard.js"></script>
    </jsp:attribute>
    <jsp:body>
        <%--<a class="btn btn-success" href="${linkTo[ServicosController].imprimirProdutos}" href="#">relatorio</a>--%>
        <%--<div class="row">--%>
        <div class="panel painel-sisint">
            <h3 class="titulo-informacao-dashboard" align="center" style="margin-bottom: 16px;">Serviços</h3>
            <div class="panel-body">
                <div class="col-md-12 tile_count">
                    <div class="col-md-2 col-md-offset-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i> Total de serviços</span>
                        <div class="count">${totalServicos}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i>
                            <a href="${linkTo[ServicosController].servicosAbertos}"> Serviços em aberto</a>
                        </span>
                        <div class="count">${servicosAbertos}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i>
                            <a href="${linkTo[ServicosController].meusServicos}"> Serviços em execução</a>
                        </span>
                        <div class="count green">${servicosExecucao}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i> Total de Tarefas</span>
                        <div class="count">${totalTarefas}</div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="panel painel-sisint">--%>
            <%--<h3 class="titulo-informacao-dashboard" align="center" style="margin-bottom: 16px;">IP's</h3>--%>
            <%--<div class="count">${ipInterno}</div>--%>
            <%--<div class="count">${ipExterno}</div>--%>
            <%--<div class="count">${macAddress}</div>--%>
        <%--</div>--%>
        <div class="panel painel-sisint">
            <h3 class="titulo-informacao-dashboard" align="center" style="margin-bottom: 16px;">Manutenções</h3>
            <div class="panel-body">
                <div class="col-md-12 tile_count">
                    <div class="col-md-2 col-md-offset-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i> Total de Manutenções</span>
                        <div class="count">${totalManutencoes}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i>
                            <a href="${linkTo[ManutencaoController].lista}"> Manutenções em aberto</a>
                        </span>
                        <div class="count">${manutencoesAbertas}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i>
                            <a href="${linkTo[ManutencaoController].lista}"> Manutencões em execução</a>
                        </span>
                        <div class="count green">${manutencoesExecucao}</div>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-tasks"></i> Equipamentos Cadastrados</span>
                        <div class="count">${totalEquipamentos}</div>
                    </div>
                </div>
            </div>
        </div>
        <%--</div>--%>
        <%--<div class="row">--%>
        <div class="panel painel-sisint" style="min-height: 200px;">
            <div class="panel-body">
                <div class="col-md-12">
                    <%--<div class="col-md-12 caixa-pontilhada">--%>
                        <h3 class="titulo-informacao-dashboard" align="center" style="margin-bottom: 16px;">Informações gerais</h3>
                        <div class="col-md-4 col-md-offset-2" align="left">
                            <label for="centauro">CENTAURO:</label>
                            <span id="centauro">(84) 3231 - 2272</span></br>
                            <label for="cetinf">CETINF:</label>
                            <span id="cetinf">(84) 3232 - 1863</span></br>
                            <label for="cotic">COTIC:</label>
                            <span id="cotic">(84) 3232 - 1050</span></br>
                            <label for="velox">VELOX:</label>
                            <span id="velox">0800 - 0318031</span></br>
                            <label for="email-inst">EMAIL INST:</label>
                            <span id="email-inst">(84) 3232 - 1047</span></br>
                            <label for="itanildo">ITANILDO DEFD:</label>
                            <span id="itanildo">(84) 98734 - 7034</span></br>
                            <label for="ti-ribeira">TI RIBEIRA:</label>
                            <span id="ti-ribeira">(84) 3232 - 2859</span></br>
                            <label for="lab">LAB-LD RONALDO:</label>
                            <span id="lab">(84) 3605 - 9646</span></br>
                        </div>
                        <div class="col-md-4" align="left">
                            <label for="email-institucional">Email institucional:</label>
                            <span id="email-institucional">tipc@rn.gov.br</span></br>
                            <label for="ergon">Informações para o funcionamento do Ergon:</label>
                            <span id="ergon">Deve ser iniciado pelo internet explorer 11, e com <a href="${ctx}/resources/util/jre-7u80-windows-i586.exe" download="">JAVA 7 </a> instalado na máquina.</span>
                        </div>

                    <%--</div>--%>
                </div>
            </div>
        </div>
        <div class="col-md-5">
            <div class="panel">
                <div class="panel-body">
                    <div id="container-pie" style="height:250px;"></div>
                </div>
            </div>
        </div>
        <div class="col-md-7">
            <div class="panel">
                <div id="container-barra" style="height:250px;">
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel">
                <div class="panel-body">
                    <div id="container-linha" style="height:200px;">
                    </div>
                </div>
            </div>
        </div>
        <%--</div>--%>
    </jsp:body>
</tags:layoutSidebar>
