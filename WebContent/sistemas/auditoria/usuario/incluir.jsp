<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<article class="painel">
	<br>
	<header class="painelheader">
		<h4 class="titulo">Cadastro de Usuários</h4>
		<form method="post" action="usuario.listar">
			<button class="botao2" title="Listar Usuários">Listar Usuários</button>
		</form>
		<br>
	</header>
	<section class="painelsection">
			<form id="formulario" name="formulario" enctype="application/x-www-form-urlencoded" method="post">
					<fieldset class="field">
						<legend class="legend">Novo Usuário</legend>
						<fieldset class="paragrafo">
						<p >
							<label class="label" for="usuario">Usuario:</label>
							<input class="input" id="usuario" type="text" name="usuario" title="Usuário" size="15" placeholder="Digite Seu Usuário" required autofocus maxlength="20"/>
						</p>
						</fieldset>
						<fieldset class="paragrafo">
						<p>
							<label class="label" for="nome">Nome:</label>
							<input class="input" id="nome" type="text" name="nome" title="Nome" size="30" placeholder="Digite Seu Nome Completo" required maxlength="120"/>
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
							<label class="label" for="perfil">Perfil do usuário:</label>
							<select class="select" id="perfil" name="perfil" required>
								<option selected="selected" value="">--- Escolha um Perfil ----</option>
								<c:forEach var="perfil" items="${perfis}">
								<option value="${perfil.perfil}">${perfil.perfil}</option>
								</c:forEach>
							</select>
						</p>
						</fieldset>
						<fieldset class="paragrafo">
						<p>
							<input class="botao" type="submit" name="acao" title="Incluir" value="Incluir" />
						</p>
						</fieldset>
					</fieldset>
				</form>
	</section>
</article>