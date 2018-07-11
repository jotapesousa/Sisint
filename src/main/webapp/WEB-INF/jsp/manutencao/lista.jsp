<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 07/02/2018
  Time: 14:56
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
        <script src="${ctx}/resources/plugins/moment/date-time-moment.js"></script>
        <script src="${ctx}/resources/js/manutencao/lista.js"></script>
        <script src="${ctx}/resources/js/manutencao/form.js"></script>
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


        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <div class="panel-title">Gerenciamento de Manutencao</div>
            </div>
            <input id="urlAssumir" type="hidden" value="${linkTo[ManutencaoController].assumirManutencao}">
            <input id="urlConcluir" type="hidden" value="${linkTo[ManutencaoController].concluir}">
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[ManutencaoController].form}">Cadastrar</a>
                <div class="tabela-servicos">
                    <table id="tabela-servico" class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <%--<th>Título</th>--%>
                            <th>Solicitante</th>
                            <th>Equipamento</th>
                            <th>Tombo</th>
                            <th>N/S do Equipamento</th>
                            <th>Setor Solicitante</th>
                            <th>Técnico</th>
                            <th>Data de Abertura</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${manutencoes}" var="manutencao">
                            <tr>
                                <%--<td>${manutencao.titulo}</td>--%>
                                <td>${manutencao.nomeSolicitante}</td>
                                <td>${manutencao.equipamento.nome}</td>
                                <td>${manutencao.equipamento.tombo}</td>
                                <td>${manutencao.equipamento.numeroSerie}</td>
                                <td>${manutencao.setor.nome}</td>
                                <td>${manutencao.tecnico.nome}</td>
                                <td>${manutencao.dataAbertura}</td>
                                <td><span class="status-manutencao">${manutencao.status.chave}</span></td>
                                <td><a href="${linkTo[ManutencaoController].detalhar}?id=${manutencao.id}" title="Detalhar"><i class="fa fa-eye"></i></a>
                                    <c:if test="${(usuarioLogado.usuario.nome == manutencao.tecnico.nome)}">
                                        <a href="${linkTo[ManutencaoController].editar}?id=${manutencao.id}" title="Editar"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                        <a href="${linkTo[ManutencaoController].remover}?id=${manutencao.id}" title="Remover"><i class="fa fa-trash"></i></a>
                                        <c:if test="${manutencao.status == 'EM_MANUTENCAO'}">
                                            <a class="concluir-manutencao" id-manutencao="${manutencao.id}" title="Concluir" data-toggle="modal"
                                               href="#modalConcluir"><i class="glyphicon glyphicon-ok" aria-hidden="true"></i></a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${manutencao.status == 'AGUARDANDO_MANUTENCAO'}">
                                        <a class="assumir-manutencao" id-manutencao="${manutencao.id}" title="Assumir" data-toggle="modal"
                                           href="#modalAssumir"><i class="glyphicon glyphicon-ok-circle" aria-hidden="true"></i></a>
                                    </c:if></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- MODAL PARA CONFIRMAR ASSUMIR SERVIÇO -->
        <div class="modal fade" id="modalAssumir" role="dialog">
            <div class="modal-dialog">
                <input type="hidden" name="manutencao.id" value="${manutencao.id}"/>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title primary">Assumir Manutenção...</h4>
                    </div>
                    <div class="modal-body">
                        <h5> Deseja assumir a manutenção?</h5>
                    </div>
                    <div id="btns-modalAssumir" class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <a id="btnAssumirManutencao" href="${linkTo[ManutencaoController].assumirManutencao}?id=" class="btn btn-primary">
                            Assumir
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL PARA CONCLUIR SERVIÇO -->
        <div class="modal fade" id="modalConcluir" role="dialog">
            <div class="modal-dialog">
                <input type="hidden" name="manutencao.id" value="${manutencao.id}"/>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title primary">Conluir Manutenção</h4>
                    </div>
                    <div class="modal-body">
                        <h5> Deseja concluir a manutenção?</h5>
                    </div>
                    <div id="btns-modal" class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <a id="btnConcluirManutencao" href="${linkTo[ManutencaoController].concluir}?id=" class="btn btn-primary">
                            Concluir
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>

