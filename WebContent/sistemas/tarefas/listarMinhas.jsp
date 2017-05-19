<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
	<header class="painelheader">
		<br>
		<h4></h4>
	</header>
	<section class="painelsection">
	<table class="display tabela">	
		<caption>Minhas Tarefas: ${tamanho}</caption>
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
	            <th></th> 
			</tr>
		</tfoot>
	    <tbody>
			<c:forEach var="dado" items="${dados}">
				<tr>
					<th><input type="checkbox" name="ckb" value="${dado.id}"/></th> 
					<td class="centraliza">${dado.id}</td>
					<td class="td_clicavel" onclick="editar_tarefa('${dado.id}', '${dado.usuario}', '${dado.data}', '${dado.tipo}', '${dado.nivel}','tarefa.alterar', 'alterar')">
						<input type="hidden" id="nota_${dado.id}" value="${dado.nota}">
						<input type="hidden" id="obs_${dado.id}" value="${dado.obs}">
						<label class="td_clicavel" id="assunto_${dado.id}" title="${dado.nota}">${dado.assunto}</label>
					</td>
					<td>
					<button class="btnPadrao" title="Finalizar" onclick="confirma_dialog('finalizar', 'tarefa.alterar', 'Finalizar a tarefa: ', '${dado.id}', 'title_green')">
						<img src="../visualizacoes/tarefas/recursos/imagem/btn_check.png" alt="">
					</button>
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
					<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'tarefa.alterar', 'Excluir a tarefa: ', '${dado.id}', 'title_red')">
						<img src="../visualizacoes/tarefas/recursos/imagem/btn_cancel.png" alt="">
					</button>
					</td>
					<td>
						<c:if test="${dado.vencido == true}"><img title="Vencida!" src="../visualizacoes/tarefas/recursos/imagem/vencido.png" alt=""></c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody> 
	</table>
	</section>
</article>