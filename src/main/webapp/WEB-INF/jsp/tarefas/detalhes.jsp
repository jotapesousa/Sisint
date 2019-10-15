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
        <script src="${ctx}/resources/js/tarefas/notas.js"></script>
        <script src="${ctx}/resources/js/tarefas/tarefa.js"></script>
        <script>
            $('.dataBadge').each( function () {
                var date = new Date($(this).text());
                $(this).text(date.toLocaleString());
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel">
            <div class="panel-heading">
                <h3 align="center">Tarefa: ${tarefa.codigoTarefa}</h3>
            </div>
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
                            <label for="tarefa-dataAbertura">Data de Abertura: </label>
                            <input id="tarefa-dataAbertura" class="form-control date-columnInput fonte" name="tarefa.dataAbertura"
                                   value="${tarefa.dataAbertura}" disabled/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="tarefa-dataFechamento">Prazo Final: </label>
                            <input id="tarefa-dataFechamento" class="form-control date-columnInput fonte" name="tarefa.dataFechamento"
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
                <div class="panel-heading">
                    <h3 align="center">Notas <a title="Adicionar Nota" class="add_nota" id-tarefa="${tarefa.id}"
                                                  data-toggle="modal" href="#modalNota" style="margin-left: 10px;">
                        <i class="fa fa-plus" aria-hidden="true"></i></a></h3>
                </div>
                <div class="row">
                    <c:forEach items="${tarefa.notas}" var="nota">
                        <input class="usuarioNota" type="hidden" value="${nota.tecnico.nome}">
                        <ul class="list-group">
                            <li class="list-group-item"><span style="font-weight: 600;color: #3c99c8;">
                                    ${nota.descricao}</span><span class="badge dataBadge">${nota.dataCriacao}</span><span class="badge">${nota.tecnico.nome}</span>
                            </li>
                        </ul>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${tarefa.statusTarefa.chave != 'Concluído' && tarefa.sizeNotas() != 0}">
                <c:if test="${tarefa.tecnico.id == usuarioLogado.usuario.id}">
                    <div class="form-group text-center">
                        <a class="btn btn-lg btn-primary concluir-tarefa" id-tarefa="${tarefa.id}" data-toggle="modal" href="#modalConcluir">Concluir Tarefa</a>
                    </div>
                </c:if>
            </c:if>
        </div>

        <!-- MODAL ADICIONAR NOTA -->
        <div class="modal fade" id="modalNota" role="dialog">
            <div class="modal-dialog">
                <form action="${linkTo[TarefasController].salvarNota}" accept-charset="ISO-8859-1">
                    <input type="hidden" id="id_tarefa_nota" name="nota.tarefa.id" value=""/>
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title primary">Adicionar Nota</h4>
                        </div>
                        <div class="modal-body">
                            <textarea type="text" id="nota_tarefa" name="nota.descricao" class="form-control" rows="7" value="${nota.descricao}"></textarea>
                        </div>
                        <div id="btn_nota_modal" class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <%--<a id="btnAddNota" class="btn btn-primary" href="${linkTo[TarefasController].salvarNota}?id=">Concluir</a>--%>
                            <button type="submit" class="btn btn-primary">Adicionar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- MODAL CONCLUIR TAREFA -->
        <div class="modal fade" id="modalConcluir" role="dialog">
            <div class="modal-dialog">
                <input type="hidden" name="tarefa.id" value="${tarefa.id}"/>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title primary">Concluir Tarefa</h4>
                    </div>
                    <div class="modal-body">
                        Deseja realmente concluir esta tarefa?
                    </div>
                    <div id="btns-modal" class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <a id="btnConcluirTarefa" href="${linkTo[TarefasController].concluir}?id=" class="btn btn-primary">Concluir</a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>

