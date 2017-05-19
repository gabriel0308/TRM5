<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
<br>
	<header class="painelheader">
	<h4></h4>
	</header>
	<section class="painelsection">
<c:if test="${dados != null && !empty dados}">
	<table class="display tabela">   
		<caption>Tarefas Excluidas: ${tamanho}</caption>
	    <thead> 
	        <tr> 
	       		<th><input id="allckb" type="checkbox" name="tudo"/></th>
	            <th>Nº</th> 
	            <th>Assunto</th>
	           	<th></th>
	            <th>P</th>
	            <th>Tipo</th> 
	            <th>início</th>
	            <th>Previsão</th>
	            <th></th>
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
	            <th>Previsão</th>
	            <th></th>
			</tr>
		</tfoot>
	    <tbody>
			<c:forEach var="dado" items="${dados}">
				<tr>
					<th><input type="checkbox" name="ckb" value="${dado.id}"/></th>
					<td class="centraliza">${dado.id}</td>
					<td><label title="${dado.nota}">${dado.assunto}</label></td>
					<td>
					<form method="post" action="tarefa.listarExcluidas" enctype="application/x-www-form-urlencoded">
						<input type="hidden" name="id" value="${dado.id}">
						<button class="tf_botao" type="submit" name="acao" value="adicionar" title="Adicionar" onclick="carregando()">
							<img src="../visualizacoes/recursos/imagem/btn_add.png" alt="">
						</button>
					</form>
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
					<td>
						<c:if test="${dado.vencido == true}"><img title="Vencida!" src="../visualizacoes/recursos/imagem/vencido.png" alt=""></c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody> 
	</table>
</c:if>
	</section>
</article>
