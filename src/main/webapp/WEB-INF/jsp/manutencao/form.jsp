<%--
  Created by IntelliJ IDEA.
  User: samue
  Date: 08/09/2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%-- aqui vai qualquer coisa--%>
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
            label {
                font-weight: 600;
                font-size: 12px;
            }

            .datepicker {
                z-index: 1151 !important;
            }

            .panel-title {
                border-bottom: 1px solid;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/typeahead/typeahead.bundle.js"></script>
        <script src="${ctx}/resources/js/manutencao/form.js"></script>
        <script src="${ctx}/resources/js/equipamentos/equipamento.js"></script>
        <script>
            $('#form-manutencao-tarefa').validator();
        </script>

        <!-- Gerador de Código -->
        <script>
            $('#btn-gerarns').click(function() {
                var ns = 'PC' + moment().year() + moment().unix() + 'RN';

                $('#nserie-equipamento').val(ns);
            })
        </script>
    </jsp:attribute>

    <jsp:body>

        <ul clas="errors">
            <c:forEach items="${errors}" var="error">
                <li>${error.category} - ${error.message}</li>
            </c:forEach>
        </ul>

        <div class="alert alert-warning alert-dismissible" hidden role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Warning!</strong> Better check yourself, you're not looking too good.
        </div>
        <div class="panel painel-cadastro-sisint">
            <form id="form-manutencao-tarefa" action="${linkTo[ManutencaoController].salvar}" method="post"
                  enctype="multipart/form-data">
                    <%--<input type="hidden" name="manutencao.codigoServico" value="${manutencao.codigoServico}"/>--%>
                <div id="container-inputs-equipamento"></div>
                <input id="manutencao-dataAbertura" type="hidden" name="manutencao.dataAbertura"
                       value="${manutencao.dataAbertura}"/>
                <div id="container-inputs-tarefa"></div>
                <div class="panel-body">
                    <h4 class="tituloCadastro">Cadastro de manutenções</h4>
                    <div id="cadastro-manutencao">
                        <div class="row">
                            <input id="manutencao-id" type="hidden" name="manutencao.id" value="${manutencao.id}"/>
                            <div class="col-md-12">
                                <div class="form-group col-sm-4">
                                    <label for="nomeSolicitante-manutencao">Nome do solicitante</label>
                                    <input type="text" minlength="3" class="form-control"
                                           id="nomeSolicitante-manutencao"
                                           required="true"
                                           value="${manutencao.nomeSolicitante}"
                                           placeholder="Nome do solicitante" name="manutencao.nomeSolicitante"/>

                                </div>
                                <div class="form-group col-sm-4">
                                    <label for="setor-manutencao">Setor solicitante</label>
                                    <select class="form-control" required id="setor-manutencao"
                                            name="manutencao.setor.id">
                                        <c:forEach items="${setores}" var="setor">
                                            <c:if test="${setor.valor == manutencao.setor.id}">
                                                <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                            </c:if>
                                            <c:if test="${!(setor.valor == manutencao.setor.id)}">
                                                <option value="${setor.valor}">${setor.chave}</option>
                                            </c:if>
                                        </c:forEach>

                                    </select>
                                </div>

                                <div class="form-group col-sm-4">
                                    <label for="data-fechamento-manutencao">Data de Finalização</label>
                                    <input type="text" class="form-control datePicker" id="data-fechamento-manutencao"
                                           required="true"
                                           value="${manutencao.dataFechamento}"
                                           placeholder="Data de finalização" readonly="readonly"
                                           name="manutencao.dataFechamento"/>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <%--<div class="form-group col-sm-4">--%>
                                    <%--<label for="tecnico-manutencao">Ténico Responsável</label>--%>
                                    <%--<select class="form-control" id="tecnico-manutencao"--%>
                                            <%--name="manutencao.tecnico.id">--%>
                                        <%--<option></option>--%>
                                        <%--<c:forEach items="${usuarios}" var="usuario">--%>
                                            <%--<c:if test="${usuario.valor == manutencao.tecnico.id}">--%>
                                                <%--<option value="${usuario.valor}"--%>
                                                        <%--selected="true">${usuario.chave}</option>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${!(usuario.valor == manutencao.tecnico.id)}">--%>
                                                <%--<option value="${usuario.valor}">${usuario.chave}</option>--%>
                                            <%--</c:if>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <div class="form-group col-sm-4">
                                    <label for="status-manutencao">Status: </label>
                                    <select type="text" id="status-manutencao" class="form-control" id="status-equip"
                                            placeholder="Status" required="true" name="manutencao.status">
                                        <option value=""></option>
                                        <c:forEach items="${status}" var="s">
                                            <c:if test="${s.valor == manutencao.status.valor}">
                                                <option value="${s.valor}" selected="true">${s.chave}</option>
                                            </c:if>
                                            <c:if test="${!(s.valor == manutencao.status.valor)}">
                                                <option value="${s.valor}">${s.chave}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label for="listaEquipamento">Equipamento</label>
                                    <div class="input-group">
                                        <button id="listaEquipamento" type="button" class="btn btn-success"
                                        data-toggle="modal" data-target="#myModal">Inserir Equipamento</button>
                                    </div>
                                </div>
                                <div class=""></div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group col-sm-8">
                                    <label for="descricao">Descrição:</label>
                                    <textarea class="form-control" name="manutencao.descricao" rows="2" required="true"
                                              id="descricao">${manutencao.descricao}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Container EQUIPAMENTO SELECIONADO -->
                    <div id="equipamentoCadastrado" hidden class="" style="margin-top: 16px;">
                        <input type='hidden' id="#mant-equip-id" name='manutencao.equipamento.id' value='${manutencao.equipamento.id}' />
                        <div id='listEquipamento' class='panel panel-primary'>
                            <div class='panel-heading'>
                                <h3 class='panel-title'>Equipamento</h3>
                            </div>
                            <div class='panel-body'>
                                <a id='editar-tarefa' class='editar-tarefa' href='#myModal' data-toggle='modal'
                                    style='float: right;'><i class='fa fa-pencil-square-o'></i></a>
                                <a id='remover-tarefa' class='remover-tarefa' href='#'
                                    style='margin-right: 4px; float: right;'><i class='fa fa-trash-o'></i></a>
                                <span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>
                                    Nome: ${manutencao.equipamento.nome}</span><br>
                                <span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>
                                    Tombo: ${manutencao.equipamento.tombo}</span><br>
                                <span class='list-group-item-text' style='size: 14px; margin-right: 16px;'>
                                    N/S: ${manutencao.equipamento.numeroSerie}</span><br>
                                <span class='list-group-item-text.' style='size: 14px; margin-right: 16px;'>
                                    Status: ${manutencao.equipamento.status.chave}</span><br>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel-footer" align="right">
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>

        <!-- Modal SELECIONAR EQUIPAMENTO -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" id="fecharModal" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Selecionar equipamento</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                        <label class="radio-inline"><input id="check-ns" type="radio" name="optradio">Buscar por NS</label>
                        <label class="radio-inline"><input id="check-tombo" type="radio" name="optradio">Buscar por Tombo</label>
                        </div>
                        <div class="form-group">
                            <input id="busca-equipamentoPorNS" class="form-control" type="text" maxlength="18" value=""
                                   placeholder="Buscar por NS" style="display: none; float: right;"/>
                        </div>
                        <div class="form-group">
                            <input id="busca-equipamentoTombo" class="form-control" type="text" maxlength="10"value=""
                                   placeholder="Buscar por tombo" style="display: none; float: right;"/>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Tombo</th>
                                <th>Número de série</th>
                                <th>Status</th>
                                <th>Selecionar</th>
                            </tr>
                            </thead>
                            <tbody id="equip-body">

                            </tbody>
                        </table>
                        <div id="nenhumEquipamento" class="text-center h4">Nenhum Equipamento Encontrado</div>
                    </div>
                    <div class="modal-footer">
                        <button id="cadastrarEquipamento" type="button" class="btn btn-primary"
                                data-toggle="modal" data-target="#modalCriarEquipamento" data-dismiss="modal">Novo Equipamento</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>

            </div>
        </div>

        <!-- Modal NOVO EQUIPAMENTO -->
        <div id="modalCriarEquipamento" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <input id="urlSalvarEquipamento" type="hidden" value="${linkTo[EquipamentoController].salvar}"/>
                    <input type='hidden' id="#equip-novo-id" name='manutencao.equipamento.id' value='${manutencao.equipamento.id}' />
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cadastrar Equipamento</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nome-equipamento">Nome do Equipamento</label>
                            <input id="nome-equipamento" class="form-control" type="text" name="manutencao.equipamento.nome"
                                   value="${equipamento.nome}" placeholder="Nome do equipamento">
                        </div>
                        <div class="form-group">
                            <label for="tombo-equipamento">Tombo</label>
                            <input id="tombo-equipamento" class="form-control" type="text" name="manutencao.equipamento.tombo"
                                   maxlength="10"
                                   value="${equipamento.nome}" placeholder="Digite o tombo do equipamento">
                        </div>
                        <div class="form-group">
                            <div>
                                <label for="nserie-equipamento">Número de Série</label>
                                <input id="nserie-equipamento" class="form-control" type="text" name="manutencao.equipamento.numeroSerie"
                                       value="${equipamento.numeroSerie}" placeholder="Digite o N/S do equipamento">
                            </div>
                            <div>
                                <button id="btn-gerarns" type="button" class="btn btn-default" style="float: right;">Gerar Código</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Status: </label>
                            <select type="text" class="form-control" id="status-equipamento" placeholder="Status"
                                    required="true"
                                    name="manutencao.equipamento.status">
                                <option value=""></option>
                                <c:forEach items="${statusEquipamento}" var="s">
                                    <c:if test="${s.valor == equipamento.status.valor}">
                                        <option value="${s.valor}" selected="true">${s.chave}</option>
                                    </c:if>
                                    <c:if test="${!(s.valor == equipamento.status.valor)}">
                                        <option value="${s.valor}">${s.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Setor: </label>
                            <select class="form-control" id="setor-equipamento" name="manutencao.equipamento.setor.id" type="text">
                                <option value=""></option>
                                <c:forEach items="${setores}" var="setor">
                                    <c:if test="${setor.valor == equipamento.setor.id}">
                                        <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                    </c:if>
                                    <c:if test="${!(setor.valor == equipamento.setor.id)}">
                                        <option value="${setor.valor}">${setor.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Descrição: </label>
                            <textarea id="descricao-equipamento" class="form-control" minlength="6" name="manutencao.equipamento.descricao" rows="2"
                                      required="true" >${equipamento.descricao}</textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="cadastro-equip" class="btn btn-primary" data-dismiss="modal">Cadastrar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>

            </div>
        </div>
    </jsp:body>
</tags:layout>
