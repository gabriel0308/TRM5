<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
	<header class="painelheader">
	<div id="tarefa_por_usuario">
		<form name="form_tarefa" action="tarefa.listarPorUsuario" enctype="application/x-www-form-urlencoded" method="post">
			<fieldset class="fieldInterno">	
				<label for="usuario">Usuário: </label>							
				<select id="usuario" name="usuario" class="text ui-widget-content ui-corner-all form">
					<option selected="selected" value="">--- Escolha um Usuário ----</option>
					<c:forEach var="usuario" items="${usuarios}">
					<option value="${usuario.usuario}">${usuario.usuario}</option>
					</c:forEach>
				</select>
				<input class="botao_padrao" type="submit" name="acao" title="Listar" value="Listar" onclick="carregando()"/>		
			</fieldset>
		</form>	
	</div>	
	</header>
	<section class="painelsection">
	<c:if test="${dados != null && !empty dados}">
		<table class="display tabela">    
			<caption>Total: ${tamanho} Usuário: ${usuarioAtual}</caption>	        
		    <thead> 
		        <tr> 
		        	<th><input id="allckb" type="checkbox" name="tudo"/></th>
		            <th>Nº</th> 
		            <th>Assunto</th>
		            <th></th> 		            
		            <th>P</th>
		            <th>Tipo</th> 
		            <th>início</th>
		            <th>Previão</th>
		        </tr> 
		    </thead>
		    <tfoot> 
		        <tr> 
		        	<th></th>
		            <th>Nº</th> 
		            <th>Assunto</th>
		            <th></th>
		            <th>P</th>
		            <th>Tipo</th> 
		            <th>início</th>
		            <th>Previão</th>
		        </tr> 
		    </tfoot>
		    <tbody>
				<c:forEach var="dado" items="${dados}">
				<tr>
					<th><input type="checkbox" name="ckb" value="${dado.id}"/></th>
					<td class="centraliza">${dado.id}</td>
					<td><label title="${dado.nota}">${dado.assunto}</label></td>
					<td class="vencida">
						<c:if test="${dado.vencido == true}"><img title="Vencida!" src="../visualizacoes/recursos/imagem/vencido.png" alt=""></c:if>
					</td>
					<c:if test="${dado.nivel == 1}"><td class="vermelho">${dado.nivel}</td></c:if>
					<c:if test="${dado.nivel == 2}"><td class="laranja">${dado.nivel}</td></c:if>
					<c:if test="${dado.nivel == 3}"><td class="amarelo">${dado.nivel}</td></c:if>
					<c:if test="${dado.nivel == 4}"><td class="azul">${dado.nivel}</td></c:if>
					<c:if test="${dado.nivel == 5}"><td class="verde">${dado.nivel}</td></c:if>
					<c:if test="${dado.tipo == 1}"><td>Sistema</td></c:if>
					<c:if test="${dado.tipo == 2}"><td>Manutenção</td></c:if>
					<c:if test="${dado.tipo == 3}"><td>Proc.Interno</td></c:if>
					<td>${dado.criacao_data}</td>
					<td>${dado.data}</td>
				</tr>
				</c:forEach>
			</tbody> 
		</table>
	</c:if>
  </section>
</article>
