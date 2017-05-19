<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
<br>
	<header class="painelheader">
	<h4></h4>
	</header>
	<section class="painelsection">
<c:if test="${dados != null && !empty dados}">
	<table class="display tabela">
		<caption>Tarefas Avulsas: ${tamanho}</caption>	        
	    <thead>  
	        <tr> 
	            <th>Nº</th> 
	            <th></th>
	            <th>Assunto</th>
	            <th></th>
	            <th>P</th>
	            <th>Tipo</th> 
	            <th>início</th>
	            <th>Previão</th>
	            <th></th>
	            <th></th>
	            <th></th>
	        </tr> 
	   	</thead> 
	    <tfoot>  
	        <tr> 
	            <th>Nº</th>
	            <th></th> 
	            <th>Assunto</th>
	            <th></th>
	            <th>P</th>
	            <th>Tipo</th> 
	            <th>início</th>
	            <th>Previão</th>
	            <th></th>
	            <th></th>
	            <th></th>
	        </tr> 
	    </tfoot> 
	    <tbody> 
			<c:forEach var="dado" items="${dados}">
			<tr>
				<td class="centraliza">${dado.id}</td>
				<th></th>
				<td class="td_clicavel" onclick="editar_tarefa('${dado.id}', '${dado.usuario}', '${dado.data}', '${dado.tipo}', '${dado.nivel}','tarefa.editarAvulsas', 'Adicionar')">
					<input type="hidden" id="nota_${dado.id}" value="${dado.nota}">
					<input type="hidden" id="obs_${dado.id}" value="${dado.obs}">
					<label class="td_clicavel" id="assunto_${dado.id}" title="${dado.nota}">${dado.assunto}</label>
				</td>
				<td>
					<button class="tf_botao" title="Finalizar" onclick="confirmaTarefa(${dado.id},'finalizar', 'tarefa.editarAvulsas')">
						<img src="../visualizacoes/recursos/imagem/btn_check.png" alt="">
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
				<button class="tf_botao" title="Excluir" onclick="confirmaTarefa(${dado.id},'excluir', 'tarefa.editarAvulsas')">
					<img src="../visualizacoes/recursos/imagem/btn_cancel.png" alt="">
				</button>
				</td>
				<td>
				<form method="post" action="tarefa.adicionar" enctype="application/x-www-form-urlencoded">
					<input type="hidden" name="id" value="${dado.id}">
					<button class="tf_botao" type="submit" name="acao" value="adicionar" title="Adicionar" onclick="carregando()">
						<img src="../visualizacoes/recursos/imagem/btn_add.png" alt="">
					</button>
				</form>
				</td>
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