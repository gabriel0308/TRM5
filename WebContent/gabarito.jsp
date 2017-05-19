<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" type="text/css" href="../recursos/css/estilos.css"/>
	<script type="text/javascript" src="../recursos/js/jquery.js" charset="UTF-8"></script>	
	<script type="text/javascript" src="../recursos/js/jquery-ui.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="../recursos/js/dataTables.js" charset="UTF-8"></script>
	<script type="text/javascript" src="../recursos/js/padrao.js" charset="UTF-8"></script>
	<link rel="shortcut icon" href="../sistemas/principal/recursos/ico/main.ico">
	<c:import url="import/ambiente.jsp"/>
	<!--HTML5 IE8-->
	<!--[if lt IE 9 ]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<!--[if lt IE 10]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body> 
	<c:if test="${sessionScope.userName != null && !empty sessionScope.userName}">
		<header class="principal">
		<div class=principal-titulo><h4><c:import url="import/principal_titulo.jsp"/></h4></div>
			<div id="logon">
				${sessionScope.userName} - ${sessionScope.setor}<br>${sessionScope.nomeEmpresa}
			</div>
		</header>
		<nav><c:import url="import/menus.jsp"/></nav>
	</c:if>
	<section class="principal">
		<h6>.</h6>
		<div id="conteudo">
			<c:import url="import/mensagens.jsp"/>
			<c:import url="${requestScope.pagina}"/>
			<c:import url="import/forms.jsp"/>
		</div>
	</section>
	<c:if test="${sessionScope.userName != null && !empty sessionScope.userName}">
		<footer class="footerprincipal">
			<h4>S.E.A Sistemas ©</h4>
		</footer>
	</c:if>	
</body>
</html>