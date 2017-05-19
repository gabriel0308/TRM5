<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${sessionScope.sistema == 'principal'}">
		Sistema de Educação e Aprendizagem
	</c:if>
	<c:if test="${sessionScope.sistema == 'auditoria'}">
		S.E.A - Auditoria
	</c:if>	
	<c:if test="${sessionScope.sistema == 'quiz'}">
		S.E.A - Quiz
	</c:if>		
	