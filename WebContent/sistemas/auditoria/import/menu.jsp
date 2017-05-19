<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="menuH">
	<li><button class="main" onclick="carrega_modulo('auditoria.inicio')">In�cio</button></li>
	<li><button class="main arrow" onclick="return false">Cadastros</button>
		<ul>
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.usuarios'}">
				<li><button class="sub" onclick="carrega_modulo('auditoria.inicio')">Usu�rios</button></li>
			</c:if></c:forEach>		
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.perfis'}">
				<li><button class="sub" onclick="carrega_modulo('perfil.listar')">Perfis</button></li>
			</c:if></c:forEach>
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.setores'}">
				<li><button class="sub" onclick="carrega_modulo('setor.listar')">Setores</button></li>
			</c:if></c:forEach>
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.modulos'}">
				<li><button class="sub" onclick="carrega_modulo('empresaSistema.listar')">Empresa sistema</button></li>
			</c:if></c:forEach>			
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.modulos'}">
				<li><button class="sub right" onclick="return false">M�dulos de Sistema</button>
					<ul>
						<li><button class="sub" onclick="carrega_modulo('categoria.listar')">Categorias</button></li>
						<li><button class="sub" onclick="carrega_modulo('modulo.listar')">M�dulos</button></li>
					</ul>
				</li>
			</c:if></c:forEach>			
		</ul>					
	</li>
	<li><button class="main" onclick="carrega_modulo('inicio')">Sistemas</button></li>
</ul>