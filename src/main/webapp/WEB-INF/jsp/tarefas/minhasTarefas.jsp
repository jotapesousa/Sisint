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
                                <td><span class="label">${tarefa.statusTarefa.chave}</span></td>
                                <td>${tarefa.servico.setor.nome}</td>
                                <td class="date-column">${tarefa.dataFechamento}</td>
                                <td>${tarefa.tecnico.nome}</td>
                                <td><a href="#"><i class="fa fa-eye" aria-hidden="false"></i></a>
                                    <a href="${linkTo[TarefasController].editar}?id=${tarefa.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-trash"></i></a>
                                    <!--<a href="${linkTo[TarefasController].concluir}?id=${tarefa.id}" title="Concluir Tarefa">
                                        <i class="glyphicon glyphicon-ok"></i> </a> -->
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