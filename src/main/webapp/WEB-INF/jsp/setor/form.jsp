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
                <h4 align="center">Cadastro de setor</h4>
            </div>
            <div class="panel-body">
                <form id="formTarefa" class="form-horizontal" action="${linkTo[SetorController].salvar}" method="post">
                    <input type="hidden" name="setor.id" value="${setor.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Nome: </label>
                        <div class="col-sm-10">
                            <input class="form-control" minlength="4" name="setor.nome" type="text" required value="${setor.nome}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Senha: </label>
                        <div class="col-sm-10">
                            <input class="form-control" name="setor.senha" type="text" value="${setor.senha}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Telefone: </label>
                        <div class="col-sm-10">
                            <input class="form-control" name="setor.telefone" type="text" value="${setor.telefone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Informações: </label>
                        <div class="col-sm-10">
                             <textarea class="form-control" minlength="6" name="setor.informacao" rows="2"
                                       required="true" >${setor.informacao}</textarea>
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
