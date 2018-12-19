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
                <h1 class="page-header text-center">Meu Perfil</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="panel col-md-6 col-md-offset-3" style="margin-top: 50px;">
            <div class="panel-body" style="padding-top: 0px;">
                <form id="form-cadastro-usuario" class="form-horizontal" action="${linkTo[UsuariosController].atualizar}" method="post">
                    <input type="hidden" name="usuario.id" value="${usuario.id}">
                    <input type="hidden" name="usuario.tipoUsuario" value="${usuario.tipoUsuario}">
                    <input type="hidden" name="usuario.senha" value="${usuario.senha}">
                    <input type="hidden" name="usuario.matricula" value="${usuario.matricula}">
                    <%--<input type="hidden" name="usuario.dataCadastro" value="${usuario.tipoUsuario}">--%>
                    <div class="form-group">
                        <label for="nome-usuario">Nome</label>
                        <input class="form-control" readonly minlength="3" id="nome-usuario" name="usuario.nome" type="text" value="${usuario.nome}">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label for="login-usuario">Login</label>
                        <input class="form-control" readonly minlength="4" id="login-usuario" name="usuario.login" type="text" value="${usuario.login}">
                        <div class="help-block with-errors"></div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">Tipo</label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<select class="form-control" id="tipo-usuario" name="usuario.tipoUsuario" type="text">--%>
                                <%--<option value=""></option>--%>
                                <%--<c:forEach items="${tipoUsuario}" var="tipo">--%>
                                    <%--<option value="${tipo.valor}">${tipo.chave}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label for="telefone-usuario">Telefone</label>
                        <input data-error="Before you wreck yourself" data-pattern-error="Formato esperado: (DD) XXXX-XXXX" class="form-control" id="telefone-usuario" name="usuario.telefone" pattern="\([0-9]{2}\) [0-9]{4,6}-[0-9]{3,4}$" type="tel" value="${usuario.telefone}">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label for="email-usuario">Email</label>
                        <input class="form-control" id="email-usuario" name="usuario.email" type="email" value="${usuario.email}">
                        <div class="help-block with-errors"></div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">Senha</label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<input class="form-control" id="senha-usuario" name="usuario.senha" type="password">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="row" align="center">
                        <button class="btn btn-success" type="button" data-toggle="modal" data-target="#modalSenha">Trocar Senha</button>
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>


                </form>
                <div class="modal fade" id="modalSenha" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <form method="post" action="${linkTo[UsuariosController].trocarSenha}">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Perfil do usuário</h4>
                            </div>
                            <div class="modal-body">

                                    <input type="hidden" name="usuario.id" value="${usuario.id}">
                                <input type="hidden" name="usuario.matricula" value="${usuario.matricula}">
                                <input type="hidden" name="usuario.tipoUsuario" value="${usuario.tipoUsuario}">

                                <div class="form-group">
                                        <label for="senha-antiga">Senha antiga: </label>
                                            <input class="form-control" id="senha-antiga" name="usuario.senha" type="password">
                                            <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="-nova-senha">Senha nova: </label>
                                            <input class="form-control" id="-nova-senha" name="usuario.novaSenha" type="password">
                                            <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmar-senha">Confirmar senha nova: </label>
                                            <input class="form-control" id="confirmar-senha" name="usuario.confirmaSenha" type="password">
                                            <div class="help-block with-errors"></div>
                                    </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-info">Salvar</button>
                            </div>
                        </div>
                        </form>

                    </div>
                </div>
            </div>

        </div>
    </jsp:body>
</tags:layout>
