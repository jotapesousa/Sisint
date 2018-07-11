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
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script type="text/javascript">
            $(document).ready(function() {
                console.log("asasa");
                var nome;
                $(".badge").each(function () {
                    nome = $(this).text();
                    nome = moment(nome);
                    nome = moment(nome).format("DD/MM/YYYY HH:mm:ss");
                    $(this).text(nome);
                    console.log(nome);
                })
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="col-md-8">
        <div class="panel">
            <div class="panel-heading">
                <h4 align="center">Serviço: ${servico.codigoServico}</h4>
            </div>
            <div class="panel-body">
                <div class="col-md-6">
                    <label for="servico-titulo">Título serviço: </label>
                    <span id="servico-titulo">${servico.titulo}</span>
                    <br>
                    <label for="servico-descricao">Descrição do serviço: </label>
                    <span id="servico-descricao">${servico.descricao}</span>
                    <br>
                    <label for="servico-tecnico">Técnico reponsável: </label>
                    <span id="servico-tecnico">${servico.tecnico.nome}</span>
                    <br>
                    <label for="servico-setor">Setor atendido: </label>
                    <span id="servico-setor">${servico.setor.nome}</span>
                    <br>
                    <label for="servico-prioridade">Prioridade do serviço: </label>
                    <span id="servico-prioridade">${servico.prioridade.chave}</span>
                    <br>
                    <label for="servico-codigo">Código do serviço: </label>
                    <span id="servico-codigo">${servico.codigoServico}</span>
                    <br>
                    <label for="servico-dataAbertura">Data de abertura: </label>
                    <span id="servico-dataAbertura">${servico.dataAbertura}</span>
                    <br>
                    <label for="servico-dataFechamento">Data de fechamento: </label>
                    <span id="servico-dataFechamento">${servico.dataFechamento}</span>
                    <br>
                </div>
                <div class="col-md-6">

                </div>
            </div>
        </div>
        </div>
        <c:if test="${not empty servico.tarefas}">
        <div class="col-md-4">
            <div class="panel">
                <div class="panel-body">
                    <c:forEach items="${servico.tarefas}" var="tarefa">
                    <div class="caixa-pontilhada col-md-12" style="border: 1px solid #000341;
                    margin-bottom: 16px;
                    border-style: dashed;
                    background-color: rgba(238,242,255,0.97)">
                        <h4 id="tarefa-codigo" align="center">Tarefa: ${tarefa.codigoTarefa}</h4>
                        <label for="tarefa-titulo">Titulo da tarefa: </label>
                        <span id="tarefa-titulo">${tarefa.titulo}</span>
                        <br>
                        <label for="tarefa-dataFechamento">Data de finalização: </label>
                        <span id="tarefa-dataFechamento">${tarefa.dataFechamento}</span>
                        <br>
                        <label for="tarefa-status">Status da tarefa: </label>
                        <span id="tarefa-status">${tarefa.statusTarefa.chave}</span>
                        <br>
                        <label for="tarefa-descricao">Descrição: </label>
                        <span id="tarefa-descricao">${tarefa.descricao}</span>
                        <br>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        </c:if>
    </jsp:body>
</tags:layout>
