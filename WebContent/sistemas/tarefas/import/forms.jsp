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
						<label>Observação:</label><br>	
						<textarea name="obs" placeholder="Digite sua Observação" maxlength="250" class="text ui-widget-content ui-corner-all form txtarea_padrao"></textarea>			
					</fieldset>
				</fieldset>
				<fieldset class="form">
					<fieldset class="fieldInterno">	
						<label>Tipo: </label><br>							
						<select name="tipo" class="text ui-widget-content ui-corner-all form">
							<option value="1" selected>Sistema</option>
							<option value="1">Sistema</option>
							<option value="2">Manutenção</option>
							<option value="3">Proc.Interno</option>
						</select>
					</fieldset>
					<fieldset class="fieldInterno">						
						<label>Nível: </label><br>	
						<select name="nivel" class="text ui-widget-content ui-corner-all form">
							<option value="1" class="vermelho" selected>Nível 1</option>
							<option value="1" class="vermelho">Nível 1 </option>
							<option value="2" class="laranja">Nível 2 </option>
							<option value="3" class="amarelo">Nível 3 </option>
							<option value="4" class="azul">Nível 4 </option>
							<option value="5" class="verde">Nível 5 </option>
						</select>					
					</fieldset>
					<fieldset class="fieldInterno">
						<label>Previsão: </label><br>
						<input type="text" value="${dataatual}" name="data" onkeypress="return false;" placeholder="dd/mm/yyyy" title="Data da Previsão" class="text ui-widget-content ui-corner-all form data">
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