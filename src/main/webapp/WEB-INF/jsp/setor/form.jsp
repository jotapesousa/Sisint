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
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Cadastro de Setores</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="panel painel-sisint">
            <div class="panel-body">
                <form id="formTarefa" action="${linkTo[SetorController].salvar}" method="post">
                    <input type="hidden" name="setor.id" value="${setor.id}">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="nome-setor">Nome: </label>
                            <input id="nome-setor" class="form-control" minlength="3" name="setor.nome"
                                   type="text" required value="${setor.nome}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="senha-setor">Senha: </label>
                            <input id="senha-setor" class="form-control" name="setor.senha" type="text" value="${setor.senha}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="telefone-setor">Telefone: </label>
                            <input id="telefone-setor" class="form-control" name="setor.telefone" type="text" value="${setor.telefone}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="endereco-setor">Endereço: </label>
                            <input id="endereco-setor" class="form-control" name="setor.endereco" type="text" value="${setor.endereco}">
                        </div>
                        <div class="form-group col-md-12">
                            <label for="informacao-setor">Informações: </label>
                            <textarea id="informacao-setor" class="form-control" minlength="6" name="setor.informacao" rows="3"
                                      required="true" >${setor.informacao}</textarea>
                        </div>
                    </div>

                    <div>
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:layout>
