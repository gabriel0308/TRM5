<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div id="nova_tarefa" title="Nova Tarefa">
		<form class="form_tarefa" id="form_nova_tf" action="tarefa.incluir" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldsetForm">
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Assunto*:</label><br>
						<textarea name="assunto" placeholder="Digite o Assunto" maxlength="50" autofocus class="text ui-widget-content ui-corner-all form txtarea_padrao"></textarea>			
					</fieldset>				
				</fieldset>
				<fieldset class="form">
					<fieldset class="fieldInterno">
						<label>Nota:</label><br>
						<textarea name="nota" placeholder="Digite sua Nota" maxlength="2048" class="text ui-widget-content ui-corner-all form txtarea_padrao nota"></textarea>			
					</fieldset>				
				</fieldset>				
				<fieldset class="form">		
					<fieldset class="fieldInterno">
						<label>Observa��o:</label><br>	
						<textarea name="obs" placeholder="Digite sua Observa��o" maxlength="250" class="text ui-widget-content ui-corner-all form txtarea_padrao"></textarea>			
					</fieldset>
				</fieldset>
				<fieldset class="form">
					<fieldset class="fieldInterno">	
						<label>Tipo: </label><br>							
						<select name="tipo" class="text ui-widget-content ui-corner-all form">
							<option value="1" selected>Sistema</option>
							<option value="1">Sistema</option>
							<option value="2">Manuten��o</option>
							<option value="3">Proc.Interno</option>
						</select>
					</fieldset>
					<fieldset class="fieldInterno">						
						<label>N�vel: </label><br>	
						<select name="nivel" class="text ui-widget-content ui-corner-all form">
							<option value="1" class="vermelho" selected>N�vel 1</option>
							<option value="1" class="vermelho">N�vel 1 </option>
							<option value="2" class="laranja">N�vel 2 </option>
							<option value="3" class="amarelo">N�vel 3 </option>
							<option value="4" class="azul">N�vel 4 </option>
							<option value="5" class="verde">N�vel 5 </option>
						</select>					
					</fieldset>
					<fieldset class="fieldInterno">
						<label>Previs�o: </label><br>
						<input type="text" value="${dataatual}" name="data" onkeypress="return false;" placeholder="dd/mm/yyyy" title="Data da Previs�o" class="text ui-widget-content ui-corner-all form data">
					</fieldset>			
					<fieldset class="fieldInterno">
						<label>Operador:</label><br>
						<select name="usuario" class="text ui-widget-content ui-corner-all form">
							<option selected="selected" value="${sessionScope.userName}">${sessionScope.userName}</option>
							<option value="nenhum">--- Nenhum ----</option>
						</select>						
					</fieldset>
				</fieldset>
			</fieldset>
			<input type="hidden" name="acao" value="incluir"/>
		</form>
	</div>
	<div id="editar_tarefa" title=""></div>	