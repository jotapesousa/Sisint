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
        <script src="${ctx}/resources/js/init.js"></script>
        <script src="${ctx}/resources/js/tarefas/tarefa.js"></script>
        <script src="${ctx}/resources/js/tarefas/notas.js"></script>
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

            $('.concluir-tarefa').click( function() {
                var url = $('#concluir-tarefa').attr('href');
                console.log("URL" + url);
                // $('.concluir-tarefa').off('click');
                // $('.btnConcluirTarefa').each(function () {
                //     console.log('oiConcluetarefa');
                //     $(this).click(function () {
                //         idTarefa = $(this).attr('id-servico');
                //         novaUrl = url + idServico;
                //         console.log(url);
                //         var novaUrlServPadrao = urlServicoPadrao +"?id="+idServico;
                //         $('#btnSalvarTarefa').attr('href', novaUrl);
                //         $('#salvarServPadrao').attr('href', novaUrlServPadrao);
                //     });
                // });
            });

        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <div class="panel-title">Tarefas atribuídas a ${usuarioLogado.usuario.nome}</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="tabela-tarefas">
                    <table id="tabela-tarefa" class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Status</th>
                            <th>Setor</th>
                            <th>Data de Fechamento</th>
                            <th>Técnico</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tarefas}" var="tarefa">
                            <tr>
                                <td>${tarefa.titulo}</td>
                                <td><span class="label label-status">${tarefa.statusTarefa.chave}</span></td>
                                <td>${tarefa.servico.setor.nome}</td>
                                <td class="date-column">${tarefa.dataFechamento}</td>
                                <td>${tarefa.tecnico.nome}</td>
                                <td><a title="Detalhes" href="${linkTo[TarefasController].detalhes}?id=${tarefa.id}"><i class="fa fa-eye fa-lg" aria-hidden="false"></i></a>
                                    <a title="Editar" href="${linkTo[TarefasController].editar}?id=${tarefa.id}"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                    <%--<a title="Remover" href="#"><i class="fa fa-trash fa-lg"></i></a>--%>
                                    <a title="Adicionar Nota" class="add_nota" id-tarefa="${tarefa.id}" data-toggle="modal" href="#modalNota">
                                        <i class="fa fa-plus fa-lg" aria-hidden="true"></i></a>
                                    <a title="Concluir" class="concluir-tarefa" id-tarefa="${tarefa.id}" data-toggle="modal" href="#modalTarefa">
                                        <i class="fa fa-check fa-lg" aria-hidden="true"></i></a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- MODAL ADICIONAR NOTA -->
        <div class="modal fade" id="modalNota" role="dialog">
            <div class="modal-dialog">
                <form action="${linkTo[TarefasController].salvarNota}">
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
        <div class="modal fade" id="modalTarefa" role="dialog">
            <div class="modal-dialog">
                <input type="hidden" name="tarefa.id" value="${tarefa.id}"/>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title primary">Concluir Tarefa</h4>
                    </div>
                    <div class="modal-body">
                        <h5> Adicionar Nota:</h5>
                        <textarea id="textoNota" class="form-control" rows="5"></textarea>
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