<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 30/01/2018
  Time: 15:33
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
        <script src="${ctx}/resources/js/servicos/lista.js"></script>
        <script src="${ctx}/resources/js/equipamentos/equipamento.js"></script>
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
                            "sSearch": "Pesquisar ",
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


        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Gerenciamento de Equipamentos</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[EquipamentoController].form}">Cadastrar</a>
                <div class="tabela-equipamentos">
                    <table id="tabela-servico" class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Tombo</th>
                            <th>Número de Série</th>
                            <th>Setor</th>
                            <th>Status</th>
                            <th>Ações</th>
                            <%--<th>Detalhes</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${equipamentos}" var="equipamento">
                            <tr class="lista-equipamento">
                                <td>${equipamento.nome}</td>
                                <td>${equipamento.tombo}</td>
                                <td>${equipamento.numeroSerie}</td>
                                <td>${equipamento.setor.nome}</td>
                                <td><span class="label status-equip">${equipamento.status.chave}</span></td>
                                <td><a title="Editar" class="editar-equip" href="${linkTo[EquipamentoController].editar}?id=${equipamento.id}" alt="Editar">
                                    <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                    <c:if test="${usuarioLogado.isAdmin()}">
                                        <a title="Remover" class="link-remover" href="#delete-modal" url-remover="${linkTo[EquipamentoController].remover}?id=${equipamento.id}"
                                           data-toggle="modal" alt="Remover">
                                            <i class="fa fa-trash fa-lg"></i></a>
                                    </c:if>
                                    <%--<a href="#"><i class="glyphicon glyphicon-file"></i></a>--%>
                                </td>
                                <%--<td><a href="#">Detalhar</a></td>--%>
                            </tr>
                        </c:forEach>

                        <!-- Modal REMOVER -->
                        <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="modalLabel">Excluir Item</h4>
                                    </div>
                                    <div class="modal-body">
                                        Deseja realmente excluir este item?
                                    </div>
                                    <div class="modal-footer">
                                        <a id="btn-remover" href="" class="btn btn-primary">Sim</a>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
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
</tags:layout>>
