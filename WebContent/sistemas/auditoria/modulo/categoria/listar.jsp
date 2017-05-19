<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Categorias de Módulos</h5>
		<br>
	</header>
	<section class="painelsection">
		<h4></h4>
		<button class="botao_padrao" onclick="nova_categoria()">Nova categoria</button>
		<br>
		<div class="tabs">
			<br>
			<ul>
				<li><a href="#tabs-1">Front-and</a></li>
				<li><a href="#tabs-2">Back-and</a></li>
			</ul>
			<div id="tabs-1">
				<table class="display tabela">
					<caption></caption>
					<thead>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Excluir</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="dadofront" items="${dadosfront}">
							<tr>
								<td class="centraliza">${dadofront.id}</td>
								<td>${dadofront.categoria}</td>
								<td>
									<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'categoria.excluir', 'Excluir a categoria: ', '${dadofront.categoria}', 'id', '${dadofront.id}', 'title_red')">
										<img src="../sistemas/auditoria/recursos/imagem/btn_cancel.png" alt="">
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>			
			</div>
			<div id="tabs-2">
				<table class="display tabela">
					<caption></caption>
					<thead>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Excluir</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="dadoback" items="${dadosback}">
							<tr>
								<td class="centraliza">${dadoback.id}</td>
								<td>${dadoback.categoria}</td>
								<td>
									<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'categoria.excluir', 'Excluir a categoria: ', '${dadoback.categoria}', 'id', '${dadoback.id}', 'title_red')">
										<img src="../sistemas/auditoria/recursos/imagem/btn_cancel.png" alt="">
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>			
		</div>
	</section>
</article>