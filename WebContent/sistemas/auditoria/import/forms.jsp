<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${form == 'categoria'}">
	<div id="nova_categoria" title="Nova Categoria de Módulos">
		<form id="form_nova_cat_modulo" action="categoria.incluir" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Categoria*:</label><br>
						<input type="text" name="categoria" placeholder="Digite a categoria" maxlength="30" class="text ui-widget-content ui-corner-all form">		
					</fieldset>
					<fieldset class="fieldInterno">						
						<label>Nível: </label><br>	
						<select name="nivel" class="text ui-widget-content ui-corner-all form">
							<option value="1" selected>Front-and</option>
							<option value="0">Back-and </option>
						</select>					
					</fieldset>				
				</fieldset>
			</fieldset>
			<input type="hidden" name="acao" value="incluir"/>
		</form>
	</div>
</c:if>

<c:if test="${form == 'empresaSistema'}">
	<div id="nova_chaveSistema" title="Nova chave">
		<form id="form_nova_chaveSistema" action="empresaSistema.alterar" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Chave*:</label><br>
						<input type="text" name="chave" placeholder="Digite nova chave" maxlength="200" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>
				</fieldset>
			</fieldset>
			<input type="hidden" name="id"/>
			<input type="hidden" name="acao" value="alterar"/>
		</form>
	</div>
</c:if>

<c:if test="${form == 'setor'}">
	<div id="edita_setor" title="Edita setor">
		<form id="form_edita_setor" action="setor.alterar" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Descrição*:</label><br>
						<input type="text" name="descricao" placeholder="Digite a descrição" maxlength="30" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>
					<fieldset class="fieldInterno">						
						<label>Empresa: </label><br>	
						<select name="id_empresa" class="text ui-widget-content ui-corner-all form largura280">
						<c:forEach var="empresa" items="${empresas}">
							<option value="${empresa.id}">${empresa.nome}</option>
						</c:forEach>						
						</select>					
					</fieldset>				
				</fieldset>
			</fieldset>
			<input type="hidden" name="id"/>
			<input type="hidden" name="acao" value="alterar"/>
		</form>
	</div>
</c:if>

<c:if test="${form == 'modulo'}">
	<div id="novo_modulo" title="Novo módulo em: '${categoria}'">
		<form id="form_novo_modulo" action="modulo.incluir" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Módulo*:</label><br>
						<input type="text" name="modulo" placeholder="Digite o modulo" maxlength="40" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>
					<fieldset class="fieldInterno">
						<label>Descrição*:</label><br>
						<input type="text" name="descricao" placeholder="Digite a descrição" maxlength="40" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>			
				</fieldset>
			</fieldset>
			<input type="hidden" name="id_categoria" value="${id_categoria}"/>
			<input type="hidden" name="acao" value="incluir"/>
		</form>
	</div>
</c:if>

<c:if test="${form == 'perfil'}">
	<div id="novo_perfil" title="Novo perfil">
		<form id="form_novo_perfil" action="perfil.incluir" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Perfil*:</label><br>
						<input type="text" name="perfil" placeholder="Digite o perfil" maxlength="40" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>
					<fieldset class="fieldInterno">
						<label>Descrição*:</label><br>
						<input type="text" name="descricao" placeholder="Digite a descrição" maxlength="40" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>			
				</fieldset>
			</fieldset>
			<input type="hidden" name="acao" value="incluir"/>
		</form>
	</div>
	<div id="editar_perfil" title="Editar">
		<form id="form_editar_perfil" action="perfil.editar" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Descrição*:</label><br>
						<input type="text" name="descricao" placeholder="Digite a descrição" maxlength="40" class="text ui-widget-content ui-corner-all form largura270">		
					</fieldset>			
				</fieldset>
			</fieldset>
			<input type="hidden" name="id"/>
			<input type="hidden" name="acao" value="editar"/>
		</form>
	</div>	
</c:if>