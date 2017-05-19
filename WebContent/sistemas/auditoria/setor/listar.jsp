<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Setores do Sistema</h5>
		<br>
	</header>
	<section class="painelsection">
		<button class="botao_padrao" onclick="carrega_modulo('setor.incluir')">Novo setor</button>
		<br>
		<br>
		<h4></h4>
		<table class="display tabela">
			<caption></caption>
			<thead>
				<tr>
					<th>Código</th>
					<th>Setor</th>
					<th>Descrição</th>
					<th>Empresa</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Código</th>
					<th>Setor</th>
					<th>Descrição</th>
					<th>Empresa</th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="setor" items="${setores}">
					<tr>
						<td class="centraliza">${setor.id}</td>
						<td>${setor.setor}</td>
						<td>${setor.descricao}</td>
						<td>${setor.empresa}</td>
						<td>
							<button class="btnPadrao" title="Alterar setor" onclick="alteraSetor('${setor.id}', '${setor.idEmpresa}', '${setor.descricao}')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_edit.png" alt="">
							</button>
						</td>
						<td>
							<button class="btnPadrao" title="Excluir" onclick="confirma_dialog('excluir', 'setor.excluir', 'Excluir o setor: ', '${setor.setor}','id', '${setor.id}', 'title_red')">
								<img src="../sistemas/auditoria/recursos/imagem/btn_cancel.png" alt="">
							</button>
						</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</section>
</article>