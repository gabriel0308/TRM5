<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
	<br>
	<header class="painelheader">
		<h4 class="titulo">Editar Usuários</h4>
		<form method="post" action="usuario.listar">
			<button class="botao2" title="Listar Usuários">Listar Usuários</button>
		</form>
		<br>
	</header>
	<section class="painelsection">
			<form id="formulario" name="formulario" enctype="application/x-www-form-urlencoded" method="post">
					<fieldset class="field">
						<legend class="legend" >Editar ${dado.usuario}</legend>
						<fieldset class="paragrafo">
						<p>
							<label class="label" for="nome">Nome:</label>
							<input class="input" id="nome" type="text" name="nome" title="Nome" size="30" placeholder="Digite Seu Nome Completo" value="${dado.nome}" required autofocus maxlength="120"/>
						</p>
						</fieldset>
						<fieldset class="paragrafo">
						<p>
							<label class="label" for="senha">Senha:</label>
							<input class="input" id="senha" type="password" name="senha" size="12" placeholder="Digite Sua Senha." required pattern="[A-Za-z-0-9]{6}" title="Conter A-Z-a-z-0-9 com 6 Digitos"/>
						</p>
						</fieldset>
						<fieldset class="paragrafo">
						<p>
							<label class="label" for="perfil">Perfil do usuario:</label>
							<select class="select" id="perfil" name="perfil">
								<option value="${dado.perfil}" selected>${dado.perfil}</option>
								<c:forEach var="perfis" items="${perfis}">	
								<option value="${perfis.perfil}">${perfis.perfil}</option>
								</c:forEach>
							</select>
						</p>
						</fieldset>
						<fieldset class="paragrafo">
						<p>
							<input type="hidden" name="id" value="${dado.id}">
							<input class="botao" type="submit" name="acao" title="Alterar" value="Alterar" />
						</p>
						</fieldset>
					</fieldset>
				</form>
	</section>
</article>