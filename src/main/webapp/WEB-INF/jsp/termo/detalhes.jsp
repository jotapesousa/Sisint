<%--
  Created by IntelliJ IDEA.
  User: SINF
  Date: 10/01/2019
  Time: 17:13
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

            $(".badge").each(function () {
                var tecnico = $('#tarefa-tecnico').val();
                console.log(tecnico);
                var nome = $(this).text();
                nome = moment(nome).format("DD/MM/YYYY HH:mm:ss");
                $(this).text(nome + ", " + tecnico);
                console.log(nome);
            })
        </script>
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
            <div class="panel-heading">
                <h3 align="center">Termo de Responsabilidade: ${termo.numero} / ${termo.ano}</h3>
            </div>
            <div class="panel-body form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Número do termo: </label>
                    <div class="col-sm-1">
                        <input class="form-control" name="termo.numero" type="text" value="${termo.numero}" disabled>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Ano: </label>
                    <div class="col-sm-1">
                        <input id="anoTermo" class="form-control" name="termo.ano" type="text" value="${termo.ano}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Código SEI: </label>
                    <div class="col-sm-2">
                        <input class="form-control" name="termo.codigoSei" type="text" value="${termo.codigoSei}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Matrícula do Servidor: </label>
                    <div class="col-sm-2">
                        <input class="form-control" name="termo.ano" type="text" value="${termo.matriculaServidor}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Recebido Por: </label>
                    <div class="col-sm-3">
                        <input class="form-control" name="termo.nomeServidor" type="text" value="${termo.nomeServidor}" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Setor: </label>
                    <div class="col-sm-3">
                        <input class="form-control" name="termo.setor.nome" type="text" value="${termo.setor.nome}" disabled>
                    </div>
                </div>
                <hr>
                <div class="panel-heading">
                    <h3 align="center">Equipamentos</h3>
                </div>
                <div class="tabela-equipamentos">
                    <table id="tabela-servico" class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Tipo</th>
                            <th>Tombo</th>
                            <th>Número de Série</th>
                            <th>Setor</th>
                            <th>Status</th>
                            <th>Ações</th>
                                <%--<th>Detalhes</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${termo.equipamentos}" var="equipamento">
                            <tr class="lista-equipamento">
                                <td>${equipamento.nome}</td>
                                <td>${equipamento.tipo.chave}</td>
                                <td>${equipamento.tombo}</td>
                                <td>${equipamento.numeroSerie}</td>
                                <td>${equipamento.setor.nome}</td>
                                <td><span class="label status-equip">${equipamento.status.chave}</span></td>
                                <td><a title="Editar" class="editar-equip" href="${linkTo[EquipamentoController].editar}?id=${equipamento.id}" alt="Editar">
                                    <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                                    <%--<c:if test="${usuarioLogado.isAdmin()}">--%>
                                        <%--<a title="Remover" class="link-remover" href="#delete-modal" url-remover="${linkTo[EquipamentoController].remover}?id=${equipamento.id}"--%>
                                           <%--data-toggle="modal" alt="Remover">--%>
                                            <%--<i class="fa fa-trash fa-lg"></i></a>--%>
                                    <%--</c:if>--%>
                                        <%--<a href="#"><i class="glyphicon glyphicon-file"></i></a>--%>
                                </td>
                                    <%--<td><a href="#">Detalhar</a></td>--%>
                            </tr>
                        </c:forEach>

                        <%--<!-- Modal REMOVER -->--%>
                        <%--<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">--%>
                            <%--<div class="modal-dialog" role="document">--%>
                                <%--<div class="modal-content">--%>
                                    <%--<div class="modal-header">--%>
                                        <%--<button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>--%>
                                        <%--<h4 class="modal-title" id="modalLabel">Excluir Item</h4>--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-body">--%>
                                        <%--Deseja realmente excluir este item?--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-footer">--%>
                                        <%--<a id="btn-remover" href="" class="btn btn-primary">Sim</a>--%>
                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>--%>
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

