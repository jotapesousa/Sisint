<%--
  Created by IntelliJ IDEA.
  User: samue
  Date: 08/09/2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">

    </jsp:attribute>
    <jsp:attribute name="rodape">
        <!-- Gerador de Código -->
        <script>
            $('#btn-gerarns').click(function() {
                var ns = 'PC' + moment().year() + moment().unix() + 'RN';

                $('#ns-equipamento').val(ns);
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <h4 align="center">Cadastro de Equipamento</h4>
            </div>
            <div class="panel-body">
                <form id="formTarefa" class="form-horizontal" action="${linkTo[EquipamentoController].salvar}" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="equipamento.id" value="${equipamento.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Nome: </label>
                        <div class="col-sm-10">
                            <input class="form-control" minlength="4" name="equipamento.nome" type="text" required value="${equipamento.nome}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Tombo: </label>
                        <div class="col-sm-10">
                            <input class="form-control" maxlength="11" name="equipamento.tombo" type="text" value="${equipamento.tombo}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Número de Série: </label>
                        <div class="col-sm-9">
                            <input id="ns-equipamento" class="form-control" name="equipamento.numeroSerie"
                                   type="text" value="${equipamento.numeroSerie}" required  >
                        </div>
                        <div class="col-sm-1">
                            <button id="btn-gerarns" type="button" class="btn btn-default" style="float: right;">Gerar Código</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Status: </label>
                        <div class="col-sm-10">
                            <select type="text" class="form-control" id="status-equip" placeholder="Status"
                                    required="true"
                                    name="equipamento.status">
                                <option value=""></option>
                                <c:forEach items="${status}" var="s">
                                    <c:if test="${s.valor == equipamento.status.valor}">
                                        <option value="${s.valor}" selected="true">${s.chave}</option>
                                    </c:if>
                                    <c:if test="${!(s.valor == equipamento.status.valor)}">
                                        <option value="${s.valor}">${s.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Setor: </label>
                        <div class="col-sm-10">
                            <select class="form-control" id="equipamento-setor" name="equipamento.setor.id" type="text">
                                <option value=""></option>
                                <c:forEach items="${setores}" var="setor">
                                    <c:if test="${setor.valor == equipamento.setor.id}">
                                        <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                    </c:if>
                                    <c:if test="${!(setor.valor == equipamento.setor.id)}">
                                        <option value="${setor.valor}">${setor.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Descrição: </label>
                        <div class="col-sm-10">
                             <textarea class="form-control" minlength="6" name="equipamento.descricao" rows="2"
                                       required="true" >${equipamento.descricao}</textarea>
                        </div>
                    </div>
                    <div align="right">
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:layout>
