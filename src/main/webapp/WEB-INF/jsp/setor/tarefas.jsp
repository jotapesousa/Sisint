<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 23/09/2019
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/dataTables/datatables.js"><c:out value=""/></script>
        <script src="${ctx}/resources/plugins/dataTables/Buttons-1.4.2/js/buttons.html5.js"><c:out value=""/></script>
        <%--<script src="${ctx}/resources/js/table.js"></script>--%>
        <script src="${ctx}/resources/js/setor/tarefasSetor.js"></script>
        <%--<script src="${ctx}/resources/js/init.js"></script>--%>
        <script>

            var idServico = "";
            var url = $('#btnSalvarTarefa').attr('href');
            var urlServicoPadrao = $('#urlServicoPadrao').val();
            //            var $btnAssumir = $('#assumir-servico');
            //            atribuirListennerBtnEdicao();
            //            function atribuirListennerBtnEdicao($btnEditar) {
            $('.concluir-tarefa').off('click');
            $('.assumir-servico').each(function () {
                $(this).click(function () {
                    var novaUrl;
                    idTarefa = $(this).attr('id-servico');
                    novaUrl = url + idServico;
                    var novaUrlServPadrao = urlServicoPadrao +"?id="+idServico;
                    $('#btnSalvarTarefa').attr('href', novaUrl);
                    $('#salvarServPadrao').attr('href', novaUrlServPadrao);
                });
            });


        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <div class="panel-title">Tarefas</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="form-group">
                    <form method="post" action="${linkTo[SetorController].tarefasPorSetor}">
                        <label for="setor-tarefa">Setor: </label>
                        <select class="form-control" id="setor-tarefa" name="setor.id" type="text">
                            <option value="" selected="true"></option>
                            <c:forEach items="${setores}" var="setor">
                                <%--<c:if test="${setor.valor == setor.id}">--%>
                                    <%--<option value="${setor.valor}" selected="true">${setor.chave}</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${!(setor.valor == setor.id)}">--%>
                                    <option value="${setor.valor}">${setor.chave}</option>
                                <%--</c:if>--%>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-info" style="margin-top: 10px;">Filtrar</button>
                    </form>
                </div>

                <div class="tabela-servicos">
                    <table id="tabela-servico" class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Status</th>
                            <th>Setor</th>
                            <th>Data de Abertura</th>
                            <th>Data de Fechamento</th>
                            <th>Técnico</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tarefas}" var="tarefa">
                            <tr class="linha-tabela clickable" data-toggle="collapse" data-target="#accordion${tarefa.id}">
                                <td>${tarefa.titulo}</td>
                                <td><span class="label label-status">${tarefa.statusTarefa.chave}</span></td>
                                <td>${tarefa.servico.setor.nome}</td>
                                <td class="date-column">${tarefa.dataAbertura}</td>
                                <td class="date-column">${tarefa.dataFechamento}</td>
                                <td>${tarefa.tecnico.nome}</td>
                                <td><a title="Detalhes" href="${linkTo[TarefasController].detalhes}?id=${tarefa.id}"><i class="fa fa-eye fa-lg" aria-hidden="false"></i></a>
                                    <a title="Editar" href="${linkTo[TarefasController].editar}?id=${tarefa.id}"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                    <a title="Remover" href="${linkTo[TarefasController].remover}?id=${tarefa.id}"><i class="fa fa-trash fa-lg"></i></a>
                                        <%--<a data-toggle="modal" url="${linkTo[ServicosController].assumirServico}?id=${tarefa.id}" --%>
                                        <%--href="#concluirId" id="concluir-tarefa" id-tarefa="${tarefa.id}" title="Concluir Tarefa">--%>
                                        <%--<i class="glyphicon glyphicon-ok"></i> </a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>
