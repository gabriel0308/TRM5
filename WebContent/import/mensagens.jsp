 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<c:if test="${requestScope.mgs_sucesso != null && !empty requestScope.mgs_sucesso}">
			<div id="mgs_sucesso" class="ui-widget-content ui-corner-all">
				<p><span class="ui-icon ui-icon-info"></span><strong>Sucesso!</strong></p>
				<p id="text_mgs">${mgs_sucesso}</p>
			</div>		
			</c:if>
			<c:if test="${requestScope.erro != null && !empty requestScope.erro}">
				<div id="msg_erro" title="Atenção!"><br>
					<p id="erro">${requestScope.erro}</p>
				</div>
			</c:if>
 			<div id="confirma_dialog" title="ATENÇÃO!"></div>
			<div id="form_erro" title="ATENÇÃO!"></div>
 			<div id="carregando">Carregando...</div>
	