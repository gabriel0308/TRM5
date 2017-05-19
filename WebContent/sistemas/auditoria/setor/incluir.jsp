<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Setores do Sistema</h5>
		<br>
	</header>
	<section class="painelsection">
		<button class="botao_padrao" onclick="carrega_modulo('setor.listar')">Listar Setores</button>
		<br>
		<br>
		<h4></h4>
		<div class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-dialog-buttons formulario">
			<div class="ui-dialog-titlebar ui-corner-all ui-widget-header ui-helper-clearfix">
				<span class="ui-dialog-title">Novo Setor</span>
			</div>
			<div class="ui-dialog-content ui-widget-content">
				<form id="form_novo_setor" action="setor.incluir" enctype="application/x-www-form-urlencoded" method="post">
					<fieldset class="fieldsetForm">
						<fieldset class="form">
							<fieldset class="fieldInterno">
								<label>Setor*:</label><br>
								<input type="text" name="setor" placeholder="Digite o setor" maxlength="30" autofocus class="text ui-widget-content ui-corner-all form largura110">		
							</fieldset>
							<fieldset class="fieldInterno">
								<label>Descrição*:</label><br>
								<input type="text" name="descricao" placeholder="Digite a descrição" maxlength="30" autofocus class="text ui-widget-content ui-corner-all form largura200">		
							</fieldset>								
						</fieldset>					
						<fieldset class="form">
							<fieldset class="fieldInterno">						
								<label>Empresa: </label><br>	
								<select name="id_empresa" class="text ui-widget-content ui-corner-all form largura300">
								<c:forEach var="empresa" items="${empresas}">
									<option value="${empresa.id}">${empresa.nome}</option>
								</c:forEach>						
								</select>					
							</fieldset>				
						</fieldset>
					</fieldset>
					<input type="hidden" name="acao" value="incluir"/>
				</form>	
			</div>
			<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
				<div class="ui-dialog-buttonset">
					<button type="button" class="ui-button ui-corner-all ui-widget" onclick="enviar_novo_setor('#form_novo_setor')">Confirmar</button>
				</div>
			</div>
		</div>
	</section>
</article>

