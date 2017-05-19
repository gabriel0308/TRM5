<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Módulos do Sistema: "${camada} / ${id_categoria} - ${categoria}"</h5>
		<br>
	</header>
	<section class="painelsection">
		<button class="botao_padrao" onclick="carrega_modulo('modulo.listar')">Listar</button>
		<button class="botao_padrao" onclick="novo_modulo()">Novo módulo</button>
		<br>
		<br>
		<h4></h4>
		<table class="display tabela">
			<caption></caption>
			<thead>
				<tr>
					<th>Módulo</th>
					<th>Descrição</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Módulo</th>
					<th>Descrição</th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="modulo" items="${modulos}">
					<tr>
						<td>${modulo.modulo}</td>
						<td>${modulo.descricao}</td>
						<td>
							<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'modulo.excluir', 'Excluir o módulo ', '${modulo.modulo}','modulo', '${modulo.modulo}', 'title_red')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_cancel.png" alt="">
							</button>
						</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</section>
</article>