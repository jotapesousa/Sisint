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
    <link href="${ctx}/resources/css/form-signin.css" rel="stylesheet" />

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Sistema de Integração - PCRN</title>
</head>
<body style="background-color: #3498db;">
<div class="container">
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <div class="panel-body">
        <div class="form-signin" style="padding-bottom: 50px; border-radius: 10px;">
            <div class="panel-heading" align="center">
                <img src="${ctx}/resources/imagens/logo-transp.png" style="width: 100%;"/>
            </div>
            <form method="post" action="${linkTo[LoginController].login}">
                <input id="login-usuario" type="text" class="form-control" name="usuario.login" placeholder="Login" autofocus>
                <p></p>
                <input id="senha-usuario" type="password" class="form-control" name="usuario.senha" placeholder="Senha">
                <jsp:include page="/WEB-INF/jsp/erros/msgError.jsp"/>
                <button class="btn btn-primary btn-lg btn-block">Entrar</button>
            </form>
        </div>
    </div>
</div>
<script>
    $('.alert').fadeOut(3000);
</script>
</body>
</html>
