<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<article class="painel">
			<br>
				<header class="painelheader">
				<h4 class="titulo">Cadastro de Usu�rios</h4>
				<form method="post" action="usuario.incluir">
					<button class="botao2" title="Inserir novo Usu�rio">Inserir novo Usu�rio</button>
				</form>
				<br>
				</header>
				<section class="painelsection">
				<table class="display tabela">    
				<caption>Lista de Usu�rios</caption>	        
			    <thead> 
			        <tr> 
			            <th>C�digo</th> 
			            <th>Usuario</th>
			            <th>Nome</th>  
			            <th>Perfil</th> 
			            <th>Alterar</th>
			            <th>Excluir</th> 
			        </tr> 
			    </thead>
			    <tfoot>
					<tr>
			            <th>C�digo</th> 
			            <th>Usuario</th>
			            <th>Nome</th>  
			            <th>Perfil</th> 
			            <th>Alterar</th>
			            <th>Excluir</th> 
					</tr>
				</tfoot>
			    <tbody> 
					<c:forEach var="dado" items="${dados}">
					<tr>
						<td class="centraliza">${dado.id}</td>
						<td>${dado.usuario}</td>
						<td>${dado.nome}</td>
						<td>${dado.perfil}</td>
						<td>
						<form method="post" action="usuario.alterar">
							<button class="botaosimples" type="submit" name="id" value="${dado.id}">Alterar</button>
						</form>
						</td>
						<td>
						<form method="post" action="usuario.excluir">
							<button class="botaosimples" type="submit" name="id" value="${dado.id}">Excluir</button>
						</form>
						</td>
					</tr>
					</c:forEach>
					</tbody> 
				</table>
			  </section>
			</article>
		
