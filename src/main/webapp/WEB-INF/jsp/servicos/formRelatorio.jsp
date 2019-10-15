<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 25/09/2019
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
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
        <script src="${ctx}/resources/js/relatorio/relatorio.js"></script>
        <script src="${ctx}/resources/plugins/"></script>
        <%--<script src="${ctx}/resources/js/setor/setor.js"></script>--%>
        <%--<script src="${ctx}/resources/js/init.js"></script>--%>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Relatório de Serviços</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="form-group">
                    <form method="post" action="${linkTo[RelatorioController].relatorioNumServicosPorSetor}">
                        <div class="row">
                            <div class="form-group col-md-1">
                                <label for="dtDe_relatorio">A Partir De</label>
                                <button type="button" data-dismiss="modal" class="close" value="">
                                    <span onclick="limparDataInicio()">&times;</span>
                                </button>
                                <input type="text" class="form-control datePicker" id="dtDe_relatorio"
                                       placeholder="(Opcional)" readonly="readonly" name="dtDe" value="${dtDe}"/>
                            </div>
                            <div class="form-group col-md-1">
                                <label for="dtAte_relatorio">Até</label>
                                <button type="button" data-dismiss="modal" class="close">
                                    <span onclick="limparDataFinal()">&times;</span>
                                </button>
                                <input type="text" class="form-control datePicker" id="dtAte_relatorio"
                                       placeholder="(Opcional)" readonly="readonly" name="dtAte" value="${dtAte}"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-info">Filtrar</button><br><br>
                    </form>
                    <a href="#modalFiltrar" url-filtrar="${linkTo[RelatorioController].relatorioFiltrarServicosPorSetor}?idSetor="
                       data-toggle="modal" class="btn btn-info link-filtrar">Filtrar Por Setor</a>
                </div>

                <div class="modal fade" id="modalFiltrar" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Escolha o setor</h4>
                            </div>
                            <div class="modal-body">
                                <label for="filtroSetor">Setor: </label>
                                <select class="form-control" id="filtroSetor" name="setor.id" type="text">
                                    <option value="" selected="true"></option>
                                    <c:forEach items="${setores}" var="setor">
                                        <option value="${setor.valor}">${setor.chave}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <a id="btnFiltrar" href="" class="btn btn-primary" target='_blank'>Filtrar</a>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

                <%--<div class="col-md-4 col-md-offset-4">--%>
                    <%--<div class="tabela-servicos">--%>
                        <%--<table id="tabela-servico" class="table table-bordered">--%>
                            <%--<thead>--%>
                            <%--<tr>--%>
                                <%--<th>Setor</th>--%>
                                <%--<th>Núm. de Chamados</th>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <%--<tbody>--%>
                            <%--<c:forEach items="${servicos}" var="servico">--%>
                                <%--<tr class="linha-tabela">--%>
                                    <%--<td>${servico[1]}</td>--%>
                                    <%--<td>${servico[0]}</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                <%--</div>--%>

            </div>
        </div>
    </jsp:body>
</tags:layout>

