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
        <script>
            $('#form-cadastro-usuario').validator();
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header text-center">Cadastro de Usuários</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="panel col-md-4 col-md-offset-4">
            <div class="panel-body" style="padding-top: 0px;">
                <form id="form-cadastro-usuario" action="${linkTo[UsuariosController].salvar}" method="post">
                    <input type="hidden" name="usuario.id" value="${usuario.id}">
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label" for="nome-usuario">Nome</label>
                            <input class="form-control" required minlength="3" id="nome-usuario" name="usuario.nome" type="text" value="${usuario.nome}">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="login-usuario">Login</label>
                            <input class="form-control" required minlength="4" id="login-usuario" name="usuario.login" type="text" value="${usuario.login}">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="matricula-usuario">Matricula</label>
                            <input class="form-control" data-pattern-error="Formato esperado: XXX.XXX-X"
                                   maxlength="9" pattern="[0-9]{3}.[0-9]{3}-[0-9]" placeholder="Ex.: 222.222-2"
                                   id="matricula-usuario" name="usuario.matricula" required type="text" value="${usuario.matricula}">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="tipo-usuario">Tipo</label>
                            <select required class="form-control" id="tipo-usuario" name="usuario.tipoUsuario" type="text">
                                <option value=""></option>
                                <c:forEach items="${tipoUsuario}" var="tipo">
                                    <option value="${tipo.valor}">${tipo.chave}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="telefone-usuario">Telefone</label>
                            <input data-pattern-error="Formato esperado: (DD) XXXX-XXXX" class="form-control" id="telefone-usuario"
                                   name="usuario.telefone" pattern="\([0-9]{2}\) [0-9]{4,6}-[0-9]{3,4}$" type="tel" value="${usuario.telefone}">
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group">
                            <label for="email-usuario">Email</label>
                            <input class="form-control" required id="email-usuario" name="usuario.email" type="email" value="${usuario.email}">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="senha-usuario">Senha</label>
                            <input class="form-control" required id="senha-usuario" name="usuario.senha" type="password">
                        </div>
                        <div class="form-group text-center">
                            <button align="center" class="btn btn-primary" type="submit">Cadastrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</tags:layout>
