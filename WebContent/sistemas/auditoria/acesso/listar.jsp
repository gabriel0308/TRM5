<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header class="painelheader">
		<br>
		<h5>Marque os acessos ${fronback} para o Perfil: "${acessoAtual.perfil}"</h5>
		<br>
	</header>
	<section class="painelsection">
		<h4><button class="botao_padrao" onclick="carrega_modulo('perfil.listar')">Listar perfis</button></h4>
		<br>
		<form id="form_acessos" method="post" action="acesso.listar" enctype="application/x-www-form-urlencoded">
			<table class="display tabela">
				<caption></caption>
				<thead>
					<tr>
			            <th>Código</th> 
			            <th>Módulo</th>
			            <th>Categoria</th>  
			            <th>Descrição</th> 
			            <th>Permitir</th> 
					</tr>
				</thead>
				<tbody>
					<c:forEach var="modulo" items="${modulos}">
						<tr>
							<td class="centraliza">${modulo.id_modulo}</td>
							<td>${modulo.modulo}</td>
							<td>${modulo.categoria}</td>
							<td>${modulo.descricao}</td>
							<td>
								<c:if test="${modulo.simnao != null && modulo.simnao == true}">
									<input type="checkbox" name="ckb" checked value="${modulo.id_modulo}"/>
								</c:if>
								<c:if test="${modulo.simnao != null && modulo.simnao == false}">
									<input type="checkbox"  name="ckb" value="${modulo.id_modulo}"/>
								</c:if>
							</td>						
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
			            <th>Código</th> 
			            <th>Módulo</th>
			            <th>Categoria</th>  
			            <th>Descrição</th> 
			            <th>Permitir</th> 
					</tr>
				</tfoot>				
			</table>
			<input type="hidden" name="id_categoria" value="${acessoAtual.id_categoria}">
			<input type="hidden" name="id_perfil" value="${acessoAtual.id_perfil}">
			<input type="hidden" name="perfil" value="${acessoAtual.perfil}">
			<input type="hidden" name="allVals">
			<br>
			<input class="botao_padrao" name="acao" title="Salvar" value="Salvar" onclick="envia_acessos('#form_acessos', '.tabela')"/>
		</form>
	</section>
</article>