<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${sessionScope.sistema == 'principal'}">
	</c:if>
	<c:if test="${sessionScope.sistema == 'auditoria'}">
		<c:import url="sistemas/auditoria/import/forms.jsp"/>
	</c:if>			
	<c:if test="${sessionScope.sistema == 'quiz'}">
		<c:import url="sistemas/tarefas/import/forms.jsp"/>
	</c:if>		
	<div id="envia_checkbox"></div>