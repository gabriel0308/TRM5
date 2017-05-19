<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${sessionScope.sistema == 'principal'}">
		<title>Sistema de Educação e Aprendizagem</title>
		<c:import url="sistemas/principal/import/scripts.jsp"/>
	</c:if>		
	<c:if test="${sessionScope.sistema == 'auditoria'}">
		<title>S.E.A - Auditoria</title>
		<c:import url="sistemas/auditoria/import/scripts.jsp"/>
	</c:if>		
	<c:if test="${sessionScope.sistema == 'quiz'}">
		<title>S.G.T.I - Tarefas</title>
		<c:import url="sistemas/tarefas/import/estilos.jsp"/>
		<c:import url="sistemas/tarefas/import/scripts.jsp"/>
	</c:if>