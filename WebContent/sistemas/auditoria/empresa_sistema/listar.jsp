<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Empresas do Sistema</h5>
		<br>
	</header>
	<section class="painelsection">
		<h4></h4>
		<table class="display tabela">
			<caption></caption>
			<thead>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Chave</th>
					<th>Data de Criação</th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Chave</th>
					<th>Data de Criação</th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="dado" items="${dados}">
					<tr>
						<td class="centraliza">${dado.id}</td>
						<td>${dado.nome}</td>
						<td>${dado.chave}</td>
						<td>${dado.data_cria}</td>
						<td>
							<button class="btnPadrao" title="Alterar Chave" onclick="alteraChave('${dado.id}')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_edit.png" alt="">
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</section>
</article>