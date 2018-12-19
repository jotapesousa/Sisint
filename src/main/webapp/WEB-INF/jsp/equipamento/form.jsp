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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
        <link href="${ctx}/resources/css/componentes/checkbox.css" rel="stylesheet"/>
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/typeahead/typeahead.bundle.js"></script>

        <!-- Gerador de Código -->
        <script>
            $('#btn-gerarns').click(function() {
                var ns = 'PC' + moment().year() + moment().unix() + 'RN';

                $('#ns-equipamento').val(ns);
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Cadastro de Equipamentos</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="panel painel-sisint">
            <div class="panel-body">
                <form id="formTarefa" action="${linkTo[EquipamentoController].salvar}" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="equipamento.id" value="${equipamento.id}">

                    <div class="row">
                        <div class="form-group col-md-4">
                            <label for="nome-equipamento">Nome: </label>
                            <input id="nome-equipamento" class="form-control" minlength="4" name="equipamento.nome"
                                   type="text" required value="${equipamento.nome}">
                        </div>
                        <div class="col-md-4">
                            <label for="ns-equipamento">Número de Série</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="ns-equipamento" name="equipamento.numeroSerie"
                                       value="${equipamento.numeroSerie}" required>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" id="btn-gerarns" type="button">Gerar Código</button>
                                </span>
                            </div><!-- /input-group -->
                        </div><!-- /.col-lg-6 -->
                        <div class="form-group col-md-4">
                            <label for="tombo-equipamento">Tombo: </label>
                            <input id="tombo-equipamento" class="form-control" maxlength="11" name="equipamento.tombo"
                                   type="text" value="${equipamento.tombo}">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="status-equip">Status: </label>
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
                        <div class="form-group col-md-4">
                            <label for="equipamento-setor">Setor: </label>
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
                        <div class="form-group col-md-8">
                            <label for="descricao-equipamento">Descrição: </label>
                            <textarea id="descricao-equipamento" class="form-control" minlength="6"
                                      name="equipamento.descricao" rows="7"
                                      required="true" >${equipamento.descricao}
                            </textarea>
                        </div>
                    </div>
                    <div class="panel">
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:layout>
