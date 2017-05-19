<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Perfis de Usuários</h5>
		<br>
	</header>
	<section class="painelsection">
		<button class="botao_padrao" onclick="novo_perfil()">Novo perfil</button>
		<br>
		<br>
		<h4></h4>
		<table class="display tabela">
			<caption></caption>
			<thead>
				<tr>
					<th>Código</th>
					<th>Perfil</th>
					<th>Descrição</th>
					<th>Acessos</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Código</th>
					<th>Perfil</th>
					<th>Descrição</th>
					<th>Acessos</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="dado" items="${dados}">
					<tr>
						<td class="centraliza">${dado.id}</td>
						<td>${dado.perfil}</td>
						<td>${dado.descricao}</td>
						<td>
							<form method="post" action="acesso.listarCategoria" enctype="application/x-www-form-urlencoded">
								<input type="hidden" name="id_perfil" value="${dado.id}">
								<button class="btnPadrao" type="submit"name="perfil" value="${perfil}" title="Gerenciar Acessos" onclick="carregando()">
									<img src="../sistemas/auditoria/recursos/imagem/btn_check.png" alt="">
								</button>
							</form>
						</td>						
						<td>
							<button class="btnPadrao" title="Editar" onclick="alteraPerfil('${dado.id}', '${dado.perfil}', '${dado.descricao}')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_edit.png" alt="">
							</button>
						</td>
						<td>
							<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'perfil.excluir', 'Excluir o perfil ', '${dado.perfil}','id', '${dado.id}', 'title_red')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_cancel.png" alt="">
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</section>
</article>