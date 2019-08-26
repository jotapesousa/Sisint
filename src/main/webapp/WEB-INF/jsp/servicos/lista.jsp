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
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">

        <script src="${ctx}/resources/plugins/dataTables/datatables.js"><c:out value=""/></script>
        <script src="${ctx}/resources/plugins/dataTables/Buttons-1.4.2/js/buttons.html5.js"><c:out value=""/></script>
        <script src="${ctx}/resources/js/servicos/servico.js"></script>
        <script src="${ctx}/resources/plugins/moment/date-time-moment.js"></script>
        <script src="${ctx}/resources/js/table.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Gerenciamento de Serviços</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[ServicosController].form}">Cadastrar</a>
                <div class="tabela-servicos">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Setor</th>
                            <th>Titulo</th>
                            <th>Prioridade</th>
                            <th>Data de Abertura</th>
                            <th>Prazo Final</th>
                            <th>Técnico</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${servicos}" var="servico">
                        <tr>
                            <td>${servico.setor.nome}</td>
                            <td>${servico.titulo}</td>
                            <td><span class="label label-prioridade">${servico.prioridade.chave}</span></td>
                            <td class="date-column">${servico.dataAbertura}</td>
                            <td class="date-column">${servico.dataFechamento}</td>
                            <td>${servico.tecnico.nome}</td>
                            <td><span class="label label-status">${servico.statusServico.chave}</span></td>
                            <td>
                                <a title="Detalhes" href="${linkTo[ServicosController].detalhes}?id=${servico.id}">
                                    <i class="fa fa-eye fa-lg" aria-hidden="false"></i></a>
                                <a title="Editar" href="${linkTo[ServicosController].editar}?id=${servico.id}">
                                    <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                <c:if test="${usuarioLogado.isAdmin()}">
                                    <a title="Log do serviço" href="${linkTo[ServicosController].logServico}?id=${servico.id}">
                                        <i class="fa fa-list-ul fa-lg" aria-hidden="true"></i></a>
                                </c:if>
                                <a title="Remover" class="remover-servico" id-servico="${servico.id}" data-toggle="modal" href="#modalRemover">
                                    <i class="fa fa-trash fa-lg"></i></a></td>
                        </tr>
                        </c:forEach>

                        <!-- MODAL CONCLUIR SERVICO -->
                        <div class="modal fade" id="modalRemover" role="dialog">
                            <div class="modal-dialog">
                                <input type="hidden" name="servico.id" value="${servico.id}"/>
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title primary">Remover Serviço</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h5> Deseja remover o serviço?</h5>
                                    </div>
                                    <div id="btns-modal" class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                        <a id="btnRemoverServico" href="${linkTo[ServicosController].remover}?id=" class="btn btn-danger">Remover</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>
