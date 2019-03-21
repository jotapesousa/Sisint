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
<tags:layout>

    <jsp:attribute name="cabecalho">
        <link href="${ctx}/resources/plugins/dataPicker/dataPicker.css" rel="stylesheet"/>
        <link href="${ctx}/resources/css/componentes/checkbox.css" rel="stylesheet"/>
        <style>
            label {
                font-weight: 600;
                font-size: 13px;
                color: steelblue;
            }
            .datepicker {
                z-index: 1151 !important;
            }
            .panel-title {
                border-bottom: 1px solid;
            }
            .fonte {
                font-size: 16px;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/js/init.js"></script>
        <%--<script>--%>
            <%--$(document).ready(function() {--%>
                <%--var nome;--%>
                <%--$(".badge").each(function () {--%>
                    <%--nome = $(this).text();--%>
                    <%--nome = moment(nome);--%>
                    <%--nome = moment(nome).format("DD/MM/YYYY HH:mm:ss");--%>
                    <%--$(this).text(nome);--%>
                    <%--console.log("Nome:" +nome);--%>
                <%--})--%>
            <%--});--%>
        <%--</script>--%>
    </jsp:attribute>

    <jsp:body>
            <div class="panel">
                <div class="panel-heading">
                    <h3 align="center">Serviço: ${servico.codigoServico}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label for="titulo-servico">Título</label>
                            <input type="text" class="form-control" id="titulo-servico" value="${servico.titulo}"
                                   name="servico.titulo" disabled/>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="telRetorno-servico">Telefone para Retorno: </label>
                            <input type="text" class="form-control" id="telRetorno-servico" value="${servico.telefoneRetorno}"
                                   name="servico.telefoneRetorno" disabled/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="nomeSolicitante-servico">Nome do solicitante</label>
                            <input type="text" class="form-control" id="nomeSolicitante-servico" value="${servico.nomeSolicitante}"
                                  name="servico.nomeSolicitante" disabled/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="setor-servico">Setor solicitante</label>
                            <input id="setor-servico" type="text" class="form-control" value="${servico.setor.nome}"
                                name="servico.setor.chave" disabled/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="servico-dataAbertura">Data de Abertura: </label>
                            <input id="servico-dataAbertura" class="form-control date-columnInput" value="${servico.dataAbertura}"
                                   name="servico.dataAbertura" disabled />
                        </div>
                        <div class="form-group col-md-3">
                            <label for="servico-dataFinal">Prazo Final: </label>
                            <input id="servico-dataFinal" class="form-control date-columnInput" value="${servico.dataFechamento}" disabled />
                        </div>
                        <div class="form-group col-md-3">
                            <label for="servico-tecnico">Técnico Responsável: </label>
                            <input id="servico-tecnico" class="form-control fonte" name="tarefa.tecnico.nome"
                                   value="${servico.tecnico.nome}" disabled/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="prioridade-servico">Prioridade</label> <br>
                            <span id="prioridade-servico" class="label label-prioridade fonte">${servico.prioridade.chave}</span>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="servico-descricao">Descrição: </label> <br>
                            <span id="servico-descricao" class="fonte">${servico.descricao}</span>
                        </div>
                </div>
                    <c:if test="${not empty servico.tarefas}">
                    <div class="panel-body">
                        <div class="panel-heading">
                            <h3 align="center">Tarefas do Serviço</h3>
                        </div>
                        <div class="row">
                            <c:forEach items="${servico.tarefas}" var="tarefa">
                                <div class="col-md-4">
                                    <div class="caixa-pontilhada col-md-12" style="border: 1px solid #000341;
                                                                        margin-bottom: 16px;
                                                                        border-style: dashed;
                                                                        background-color: rgba(238,242,255,0.97)">
                                        <h4 id="tarefa-codigo" align="center">
                                            <a href="${linkTo[TarefasController].detalhes}?id=${tarefa.id}">Tarefa: ${tarefa.codigoTarefa}</a></h4>
                                        <label for="tarefa-titulo">Titulo da tarefa: </label>
                                        <span id="tarefa-titulo">${tarefa.titulo}</span>
                                        <br>
                                        <label for="tarefa-dataFechamento">Prazo Final: </label>
                                        <span id="tarefa-dataFechamento" class="date-column">${tarefa.dataFechamento}</span>
                                        <br>
                                        <label for="tarefa-dataFechamento">Técnico Responsável: </label>
                                        <span id="tarefa-tecnico">${tarefa.tecnico.nome}</span>
                                        <br>
                                        <label for="tarefa-status">Status da tarefa: </label>
                                        <span id="tarefa-status" class="label label-status">${tarefa.statusTarefa.chave}</span>
                                        <br>
                                        <label for="tarefa-descricao">Descrição: </label>
                                        <span id="tarefa-descricao">${tarefa.descricao}</span>
                                        <br>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>
    </jsp:body>
</tags:layout>