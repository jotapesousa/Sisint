<%--
  Created by IntelliJ IDEA.
  User: Sinf02
  Date: 15/09/2017
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Sistema de Integração - PCRN</title>
</head>
<body style="background-color: #3498db;">
<jsp:include page="/WEB-INF/jsp/erros/msgError.jsp"/>
<div class="container">
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>


    <div class="panel painel-sisint col-md-4 col-md-offset-4" style="margin-top:100px;">
        <div class="panel-heading" align="center">
            <img src="${ctx}/resources/imagens/logo-transp.png" style="margin-top: 16px; margin-left: -20px;"/>
        </div>
        <div class="panel-body" style="padding-bottom: 75px;">
            <form method="post" action="${linkTo[LoginController].login}">
                <div class="input-group" style="margin-bottom: 8px;">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="login-usuario" type="text" class="form-control" name="usuario.login" placeholder="Login">
                </div>
                <div class="input-group" style="margin-bottom: 8px;">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="senha-usuario" type="password" class="form-control" name="usuario.senha" placeholder="Senha">
                </div>

                <button class="btn btn-primary col-md-3 col-md-offset-9">Entrar</button>
            </form>
        </div>
    </div>
</div>
<script>
    $('.alert').fadeOut(3000);
</script>
</body>
</html>
