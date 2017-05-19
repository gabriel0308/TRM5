<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<ul class="menuH">
			<li><button class="main" onclick="carrega_modulo('inicio')">Sistemas</button></li>
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == 'm.tarefas'}"> <li><button class="main arrow" onclick="return false">Tarefas</button>
				<ul>
					<li><button class="sub" onclick="nova_tarefa()">Nova Tarefa</button></li>
					<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == 'm.t.minhastarefas'}"> <li><button class="sub" onclick="carrega_modulo('tarefa.listarMinhas')">Minhas Tarefas</button></li></c:if></c:forEach>
					<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == 'm.t.tarefasavulsas'}"> <li><button class="sub" onclick="carrega_modulo('tarefa.listarAvulsas')">Tarefas Avulsas</button></li></c:if></c:forEach>
					<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == 'm.t.gerente'}"> <li><button class="sub right" onclick="return false">Gerente</button>
						<ul>
							<li><button class="sub" onclick="carrega_modulo('tarefa.listarPorUsuario')">Tarefas Por Usuário</button></li>
						</ul>
					</li></c:if></c:forEach>
				</ul>					
			</li></c:if></c:forEach>
			<li><button class="main arrow" onclick="return false">Arquivo</button>
				<ul>
					<li><button class="sub" onclick="carrega_modulo('tarefa.listarExcluidas')">Excluídas</button></li>
					<li><button class="sub" onclick="carrega_modulo('tarefa.listarFinalizadas')">Finalizadas</button></li>
				</ul>					
			</li>
			<c:forEach var="dado" items="${sessionScope.acessos}"><c:if test="${dado == 'm.t.relatorios'}"> <li><button class="main arrow" onclick="return false">Relatórios</button>
				<ul>
					<li><button class="sub" onclick="selecionaCbk('simples', 'tarefa.relatorio', '_blank')">Simples</button></li>
					<li><button class="sub" onclick="selecionaCbk('completo', 'tarefa.relatorio', '_blank')">Completo</button></li>
				</ul>	
			</li></c:if></c:forEach>
		</ul>
