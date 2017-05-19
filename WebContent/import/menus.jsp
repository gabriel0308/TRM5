<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${sessionScope.sistema == 'principal'}">
		<c:import url="/sistemas/principal/import/menu.jsp"/>
	</c:if>		
	<c:if test="${sessionScope.sistema == 'auditoria'}">
		<c:import url="/sistemas/auditoria/import/menu.jsp"/>
	</c:if>		
	<c:if test="${sessionScope.sistema == 'quiz'}">
		<c:import url="/sistemas/tarefas/import/menu.jsp"/>
	</c:if>
	<div id="logout">
		<button id="sair" title="Sair do sistema" onclick="carrega_modulo('?acao=encerrar')">Sair</button>
	</div>