<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 26/03/2019
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>

    <jsp:attribute name="cabecalho">
        <link href="${ctx}/resources/plugins/dataPicker/dataPicker.css" rel="stylesheet"/>
        <link href="${ctx}/resources/css/componentes/checkbox.css" rel="stylesheet"/>
        <style>
            label {
                font-weight: 600;
                font-size: 12px;
            }

            .datepicker {
                z-index: 1151 !important;
            }

            .panel-title {
                border-bottom: 1px solid;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script type="text/javascript">
            $(document).ready(function() {
                var nome;
                $(".badge").each(function () {
                    nome = $(this).text();
                    nome = moment(nome);
                    nome = moment(nome).format("DD/MM/YYYY HH:mm:ss");
                    $(this).text(nome);
                    console.log(nome);
                })
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel">
            <div class="panel-heading">
                <h3>Registro de Logs do Equipamento</h3>
            </div>
            <div class="panel-body">
                <c:forEach items="${listaLogs}" var="log">
                    <ul class="list-group">
                        <li class="list-group-item"><span style="font-weight: 600;color: #3c99c8;">
                                ${log.log}</span><span class="badge">${log.dataAlteracao}</span>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>

    </jsp:body>
</tags:layout>

