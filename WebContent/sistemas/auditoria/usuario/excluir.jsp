<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
	<br>
	<header class="painelheader">
		<h4 class="titulo">Excluir Usu�rios</h4>
		<form method="post" action="usuario.listar">
			<button class="botao2" title="Listar Usu�rios">Listar Usu�rios</button>
		</form>
		<br>
	</header>
	<section class="painelsection">
	<form name="formulario" enctype="application/x-www-form-urlencoded" method="post">
	<table class="sortable">    
	<caption>Confirma exclus�o ?</caption>	
		<thead> 
			<tr> 
		        <th>C�digo</th> 
		        <th>Usuario</th>
		        <th>Nome</th>  
		        <th>Perfil</th> 
		    	<th>Excluir</th> 
		    </tr> 
	    </thead> 
			<tbody> 
				<tr>
					<td class="centraliza">${dado.id}</td>
					<td>${dado.usuario}</td>
					<td>${dado.nome}</td>
					<td>${dado.perfil}</td>
					<td>
						<input type="hidden" name="id" value="${dado.id}">
						<input class="botaosimples" type="submit" name="acao" value="Excluir" />
					</td>
				</tr>
			</tbody> 
	</table>
	</form>
	</section>
</article>