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
        <script src="${ctx}/resources/js/servicos/lista.js"></script>
        <script src="${ctx}/resources/plugins/moment/date-time-moment.js"></script>
        <script>
            $(document).ready(function () {
                $.fn.dataTable.moment('DD/MM/YYYY');

                $('.table').DataTable( {
                    pageLength:25,
                    "language":
                        {
                            "sEmptyTable": "Nenhum registro encontrado",
                            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                            "sInfoPostFix": "",
                            "sInfoThousands": ".",
                            "sLengthMenu": "_MENU_ resultados por página",
                            "sLoadingRecords": "Carregando...",
                            "sProcessing": "Processando...",
                            "sZeroRecords": "Nenhum registro encontrado",
                            "sSearch": "Pesquisar",
                            "oPaginate": {
                                "sNext": "Próximo",
                                "sPrevious": "Anterior",
                                "sFirst": "Primeiro",
                                "sLast": "Último"
                            },
                            "oAria": {
                                "sSortAscending": ": Ordenar colunas de forma ascendente",
                                "sSortDescending": ": Ordenar colunas de forma descendente"
                            }
                        }
                } );
            });

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
                <div class="panel-title">Gerenciamento de tarefas</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="tabela-servicos">
                    <table id="tabela-servico" class="table table-bordered tabela">
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
                                    <c:if test="${usuarioLogado.usuario.id == tarefa.tecnico.id || usuarioLogado.isAdmin()}">
                                        <a title="Editar" href="${linkTo[TarefasController].editar}?id=${tarefa.id}"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                        <a title="Remover" href="${linkTo[TarefasController].remover}?id=${tarefa.id}"><i class="fa fa-trash fa-lg"></i></a>
                                    </c:if>
                                        <%--<a data-toggle="modal" url="${linkTo[ServicosController].assumirServico}?id=${tarefa.id}" --%>
                                           <%--href="#concluirId" id="concluir-tarefa" id-tarefa="${tarefa.id}" title="Concluir Tarefa">--%>
                                            <%--<i class="glyphicon glyphicon-ok"></i> </a>--%>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td id="accordion${tarefa.id}" class="collapse" colspan="6">--%>
                                    <%--qualquer--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        </c:forEach>

                        <%--<div id="concluirId" class="modal fade" role="dialog">--%>
                            <%--<div class="modal-dialog">--%>
                                <%--<!-- Modal content-->--%>
                                <%--<div class="modal-content">--%>
                                    <%--<div class="modal-header">--%>
                                        <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                                        <%--<h4 class="modal-title">Concluir Tarefa</h4>--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-body">--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="notaFinal">Nota Final:</label>--%>
                                            <%--<textarea class="form-control" rows="5" id="notaFinal"></textarea>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-footer">--%>
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>--%>
                                        <%--<a href="${linkTo[TarefasController].concluir}?id=${tarefa.id}" class="btn btn-primary">Concluir</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            <%--</div>--%>
                        <%--</div>--%>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>