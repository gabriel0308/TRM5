<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Selecione uma categoria para o Perfil: "${acessoAtual.perfil}"</h5>
	</header>
	<section class="painelsection">
		<h4></h4>
		<br>
		<div class="tabs">
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
							<th>Selecionar</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Selecionar</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="dadofront" items="${dadosfront}">
							<tr>
								<td class="centraliza">${dadofront.id}</td>
								<td>${dadofront.categoria}</td>
								<td>
								<form method="post" action="acesso.listar" enctype="application/x-www-form-urlencoded">
									<input type="hidden" name="id_categoria" value="${dadofront.id}">
									<input type="hidden" name="id_perfil" value="${acessoAtual.id_perfil}">
									<input type="hidden" name="perfil" value="${perfil}">
									<input type="hidden" name="fronback" value="Front-and">
									<button class="btnPadrao" type="submit" name="acao" value="selecionar" title="Selecionar" onclick="carregando()">
										<img src="../sistemas/auditoria/recursos/imagem/btn_check.png" alt="">
									</button>
								</form>
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
							<th>Selecionar</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Código</th>
							<th>Categoria</th>
							<th>Selecionar</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="dadoback" items="${dadosback}">
							<tr>
								<td class="centraliza">${dadoback.id}</td>
								<td>${dadoback.categoria}</td>
								<td>
								<form method="post" action="acesso.listar" enctype="application/x-www-form-urlencoded">
									<input type="hidden" name="id_categoria" value="${dadoback.id}">
									<input type="hidden" name="id_perfil" value="${acessoAtual.id_perfil}">
									<input type="hidden" name="perfil" value="${perfil}">
									<input type="hidden" name="fronback" value="Back-and">
									<button class="btnPadrao" type="submit" name="acao" value="selecionar" title="Selecionar" onclick="carregando()">
										<img src="../sistemas/auditoria/recursos/imagem/btn_check.png" alt="">
									</button>
								</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>			
		</div>
	</section>
</article>