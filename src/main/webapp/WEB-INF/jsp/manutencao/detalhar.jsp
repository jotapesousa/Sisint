<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 25/05/2018
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/dataTables/datatables.js"><c:out value=""/></script>
        <script src="${ctx}/resources/plugins/dataTables/Buttons-1.4.2/js/buttons.html5.js"><c:out value=""/></script>
        <script src="${ctx}/resources/js/servicos/lista.js"></script>
        <script src="${ctx}/resources/plugins/moment/date-time-moment.js"></script>
        <script src="${ctx}/resources/js/manutencao/form.js"></script>
        <script>
            $(document).ready(function () {

            });


        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <h4 align="center">Detalhes de Manutenção</h4>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="row">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="col-md-12">
                                <label for="manutencao-titulo">Nome do Solicitante: </label>
                                <span id="manutencao-titulo">${manutencao.nomeSolicitante}</span>
                                <br>
                                <label for="manutencao-setor">Setor do Solicitante: </label>
                                <span id="manutencao-setor">${manutencao.setor.nome}</span>
                                <br>
                                <label for="manutencao-tecnico">Técnico reponsável: </label>
                                <span id="manutencao-tecnico">${manutencao.tecnico.nome}</span>
                                <br>
                                <label for="manutencao-dataAbertura">Data de abertura: </label>
                                <span id="manutencao-dataAbertura">${manutencao.dataAbertura}</span>
                                <br>
                                <label for="manutencao-dataFechamento">Data de fechamento: </label>
                                <span id="manutencao-dataFechamento">${manutencao.dataFechamento}</span>
                                <br>
                                <label for="manutencao-descricao">Descrição da Manutenção: </label>
                                <span id="manutencao-descricao">${manutencao.descricao}</span>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<c:if test="${not empty manutencao.equipamento}">--%>
                    <div class="row">
                        <div class="panel">
                            <div class="panel-body">
                                    <div class="caixa-pontilhada col-md-12" style="border: 1px solid #000341;
                                         margin-bottom: 16px;
                                         border-style: dashed;
                                         background-color: rgba(238,242,255,0.97)">
                                        <h4 id="tarefa-codigo" align="center">Nome do Equipamento: ${manutencao.equipamento.nome}</h4>
                                        <label for="equipamento-tombo">Tombo: </label>
                                        <span id="equipamento-tombo">${manutencao.equipamento.tombo}</span>
                                        <br>
                                        <label for="equipamento-nserie">Data de finalização: </label>
                                        <span id="equipamento-nserie">${manutencao.equipamento.numeroSerie}</span>
                                        <br>
                                        <label for="equipamento-setor">Setor do Equipamento: </label>
                                        <span id="equipamento-setor">${manutencao.equipamento.setor.nome}</span>
                                        <br>
                                        <label for="equipamento-status">Status do Equipamento: </label>
                                        <span id="equipamento-status">${manutencao.equipamento.status.chave}</span>
                                        <br>
                                        <label for="equipamento-descricao">Descrição: </label>
                                        <span id="equipamento-descricao">${manutencao.equipamento.descricao}</span>
                                        <br>
                                    </div>
                            </div>
                        </div>
                    </div>
                <%--</c:if>--%>
                <button href="" class="btn btn-default" onclick="history.back(1)">Voltar</button>
            </div>

            </div>
        </div>
    </jsp:body>
</tags:layout>
