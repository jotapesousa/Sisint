<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 14/12/2018
  Time: 16:42
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
            .datepicker {
                z-index: 1151 !important;
            }

            .panel-title {
                border-bottom: 1px solid;
            }
            .fonte {
                font-size: 16px;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/js/init.js"></script>
        <script>
            console.log($('#tarefa-dataFechamento').val());
            var obj = moment($('#tarefa-dataFechamento').val(), 'YYYY-MM-DD').format('DD/MM/YYYY');
            $('#tarefa-dataFechamento').val(obj);
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel">
            <div class="panel-heading">
                <h3 align="center">Tarefa: ${tarefa.codigoTarefa}</h3>
            </div>
            <div class="panel-body">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group col-md-6">
                                <label for="tarefa-titulo">Titulo: </label>
                                <input id="tarefa-titulo" class="form-control fonte" name="tarefa.titulo" value="${tarefa.titulo}" disabled/>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="tarefa-tecnico">Técnico Responsável: </label>
                                <input id="tarefa-tecnico" class="form-control fonte" name="tarefa.tecnico.nome"
                                       value="${tarefa.tecnico.nome}" disabled/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="tarefa-dataFechamento">Prazo Final: </label>
                                <input id="tarefa-dataFechamento" class="form-control date-column fonte" name="tarefa.dataFechamento"
                                       value="${tarefa.dataFechamento}" disabled/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="tarefa-status">Status: </label> <br>
                                <span id="tarefa-status" class="label label-status fonte">${tarefa.statusTarefa.chave}</span>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="tarefa-descricao">Descrição: </label> <br>
                                <span id="tarefa-descricao" class="fonte">${tarefa.descricao}</span>
                            </div>
                            <%--<label for="tarefa-dataFechamento">Técnico Responsável: </label>--%>
                            <%--<span id="tarefa-tecnico">${tarefa.tecnico.nome}</span>--%>
                            <%--<br>--%>
                            <%--<label for="tarefa-status">Status da tarefa: </label>--%>
                            <%--<span id="tarefa-status" class="label label-status">${tarefa.statusTarefa.chave}</span>--%>
                            <%--<br>--%>

                            <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>

