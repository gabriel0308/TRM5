<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="painel">
	<header> </header>
	<section>
		<article class="painel">
			<br>
			<div id="menu_sistemas">
				<h2>Selecione um Sistema.</h2>
				<br>
				<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '2.inicio'}">
					<button title="Auditoria" class="ui-corner-all botao_padrao botao_sistemas" onclick="carrega_modulo('auditoria.inicio')">
						<img src="../sistemas/principal/recursos/imagem/sis_auditoria.png" alt=""><br><b>Auditoria</b>
					</button>
				</c:if></c:forEach>
				<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == '4.inicio'}">
					<button title="Quiz" class="ui-corner-all botao_padrao botao_sistemas" onclick="carrega_modulo('quiz.inicio')">
						<img src="../sistemas/principal/recursos/imagem/sis_tarefas.png" alt=""><br><b>Quiz</b>
					</button>
				</c:if></c:forEach>
			</div>
		</article>
	</section>
</article>