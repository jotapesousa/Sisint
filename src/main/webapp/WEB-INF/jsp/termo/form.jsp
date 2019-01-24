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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/js/termo/termo.js"></script>
        <script src="${ctx}/resources/js/termo/cadastroEquipamentos.js"></script>
        <script src="${ctx}/resources/js/equipamentos/equipamento.js"></script>
        <script src="${ctx}/resources/plugins/jquery-maskmoney-master/dist/jquery.maskMoney.min.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <h4 align="center">Cadastro de Termo de Responsabilidade</h4>
            </div>
            <div class="panel-body">
                <form id="formTermo" class="form-horizontal" action="${linkTo[TermoController].salvar}" method="post">
                    <input type="hidden" name="termo.id" value="${termo.id}">
                    <div id="container-inputs-equipamento">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Número do termo: </label>
                        <div class="col-sm-1">
                            <input id="numeroTermo" class="form-control" name="termo.numero" type="text" value="${termo.numero}"
                                   maxlength="4" pattern="[0-9]">
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
                            <input id="codSeiTermo" class="form-control" name="termo.codigoSei" type="text" value="${termo.codigoSei}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Matrícula do Servidor: </label>
                        <div class="col-sm-2">
                            <input id="matServidorTermo" class="form-control" name="termo.matriculaServidor" type="text" value="${termo.matriculaServidor}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Recebido Por: </label>
                        <div class="col-sm-3">
                            <input id="nomeServidorTermo" class="form-control" name="termo.nomeServidor" type="text" value="${termo.nomeServidor}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Setor: </label>
                        <div class="col-sm-3">
                            <select id="termo-setor" class="form-control" name="termo.setor.id" type="text" required>
                                <option value=""></option>
                                <c:forEach items="${setores}" var="setor">
                                    <c:if test="${setor.valor == termo.setor.id}">
                                        <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                    </c:if>
                                    <c:if test="${!(setor.valor == termo.setor.id)}">
                                        <option value="${setor.valor}">${setor.chave}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div align="right">
                            <button id="confirmarTermo" type="button"class="btn btn-success" disabled>Confirmar</button>

                            <button id="inserirEq" type="button"class="btn btn-success"
                                    data-toggle="modal" data-target="#modalEquipamento" style="display: none;">Inserir Equipamento</button>
                            <button id="salvarTermo" class="btn btn-primary" type="submit" style="display: none;">Salvar</button>


                    </div>
                </form>

                <div id="equipamentoCadastrado" hidden class="" style="margin-top: 16px;">

                </div>

            </div>

            <!-- Modal SELECIONAR EQUIPAMENTO -->
            <div id="modalEquipamento" class="modal fade" role="dialog">
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
                                <label for="nserie-equipamento">Número de Série</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="nserie-equipamento" name="manutencao.equipamento.numeroSerie"
                                           value="${equipamento.numeroSerie}" placeholder="Digite o N/S do equipamento" required>
                                    <span class="input-group-btn">
                                    <button class="btn btn-default" id="btn-gerarns" type="button">Gerar Código</button>
                                </span>
                                </div><!-- /input-group -->
                            </div>
                            <div class="form-group">
                                <label for="tipo-equipamento">Tipo: </label>
                                <select type="text" class="form-control" id="tipo-equipamento" placeholder="Tipo"
                                        required="true"
                                        name="equipamento.tipo">
                                    <c:forEach items="${tipo}" var="t">
                                        <c:if test="${t.valor == equipamento.tipo.valor}">
                                            <option value="${t.valor}" selected="true">${t.chave}</option>
                                        </c:if>
                                        <c:if test="${!(t.valor == equipamento.tipo.valor)}">
                                            <option value="${t.valor}">${t.chave}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
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
        </div>
    </jsp:body>
</tags:layout>
