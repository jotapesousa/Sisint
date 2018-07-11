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
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <h4 align="center">Cadastro de Termo de Responsabilidade</h4>
            </div>
            <div class="panel-body">
                <form id="formTarefa" class="form-horizontal" action="${linkTo[TermoController].imprimirTermo}" method="get">
                    <input type="hidden" name="termo.id" value="${termo.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Número do termo: </label>
                        <div class="col-sm-1">
                            <input class="form-control" minlength="2" name="termo.numero" type="text" required value="${termo.numero}">
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Ano: </label>
                        <div class="col-sm-1">
                            <input class="form-control" minlength="4" maxlength="4" name="termo.ano" type="text" value="${termo.ano}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Setor: </label>
                        <div class="col-sm-4">
                            <select class="form-control" id="termo-setor" name="termo.setor.id" type="text">
                                <option value=""></option>
                                <c:forEach items="${setores}" var="setor">
                                    <c:if test="${setor.valor == termo.setor.id}">
                                        <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                    </c:if>
                                    <c:if test="${!(setor.valor == termo.setor.id)}">
                                        <option value="${setor.valor}">${setor.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Equipamentos: </label>
                        <div class="col-sm-1">
                            <button id="inserirEq" type="button"class="btn btn-default"
                                    data-toggle="modal" data-target="#modalEquipamento">Inserir Equipamento</button>
                        </div>
                    </div>
                    <div align="right">
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>

                    <div class="modal fade" id="modalEquipamento" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    <p>One fine body&hellip;</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </form>
            </div>
        </div>
    </jsp:body>
</tags:layout>
