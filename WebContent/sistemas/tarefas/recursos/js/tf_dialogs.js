/* Funçoes exclusicas do sistemas de tarefas
 * ----------------------------------------- */
 /* oculta e define tabela */
$( document ).ready(function() {
	// Oculta a tabela
	$( "#table_wrapper").hide();
	$( "#table" ).hide();
	if ( $("table").length ){
		$( ".tabela").dataTable( {
			"order": [[ 4, "desc" ]],
			"lengthMenu": [[5, 50, 100, -1], [5, 50, 100, "All"]],
			stateSave: true
		});
	}
});

/* carrega e exibe */
$(window).load(function() {
	// Carrega a tabela
	$( "#table" ).show(); 
	$( "#table_wrapper").show();
});

/* ----------------------------------------- */
// Funcao de validacao.
function valida_tf(form){
	var s = 0;
	s = s + validaTamanho($(''+form+' textarea[name=assunto]'));
	return s;
}
// Cria o formulario.
cria_formulario(610,740, "#nova_tarefa", "#form_nova_tf", valida_tf, null, null);

// Define funcao para chamada de formulario.
function nova_tarefa(){
	$( "#nova_tarefa" ).dialog( "open" );
}
/* ----------------------------------------- */
//Cria o formulario.
cria_formulario(610,740, "#editar_tarefa", "#form_editar_tf",  valida_tf, "finalizar", "excluir");

//Define funcao para chamada de formulario.
function editar_tarefa(id, usuario, data, tipo, nivel, caminho, acao){
	var nota = $("#nota_"+id).val();
	var obs = $("#obs_"+id).val();
	var assunto = $("#assunto_"+id).html();
	if (tipo == 1){ tipo = '<option value="1" selected>Sistema</option>'};
	if (tipo == 2){ tipo = '<option value="2" selected>Manutenção</option>'};
	if (tipo == 3){ tipo = '<option value="3" selected>Proc.Interno</option>'};
	if (nivel == 1){nivel = '<option value="1" class="vermelho" selected>Nível 1</option>'};
	if (nivel == 2){nivel = '<option value="2" class="laranja" selected>Nível 2</option>'};
	if (nivel == 3){nivel = '<option value="3" class="amarelo" selected>Nível 3</option>'};
	if (nivel == 4){nivel = '<option value="4" class="azul" selected>Nível 4</option>'};
	if (nivel == 5){nivel = '<option value="5" class="verde" selected>Nível 5</option>'};
	// Zera o html
	$("#editar_tarefa").html(" ");
	// Arrumar no html
	$("#editar_tarefa").html(
	'<form class="form_tarefa" id="form_editar_tf" action="'+caminho+'" enctype="application/x-www-form-urlencoded" method="post">' +
	'	<fieldset class="fieldsetForm">' +
	'		<fieldset class="form">' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Assunto*:</label><br>' +
	'				<textarea name="assunto" placeholder="Digite o Assunto" maxlength="50" autofocus class="text ui-widget-content ui-corner-all form txtarea_padrao">'+assunto+'</textarea>' +
	'			</fieldset>	' +
	'		</fieldset>' +
	'		<fieldset class="form">' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Nota:</label><br>' +
	'				<textarea name="nota" placeholder="Digite sua Nota" maxlength="2048" class="text ui-widget-content ui-corner-all form txtarea_padrao nota">'+nota+'</textarea>' +
	'			</fieldset>	' +
	'		</fieldset>	' +
	'		<fieldset class="form">	' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Observação:</label><br>' +
	'				<textarea name="obs" placeholder="Digite sua Observação" maxlength="250" class="text ui-widget-content ui-corner-all form txtarea_padrao">'+obs+'</textarea>' +
	'			</fieldset>' +
	'		</fieldset>' +
	'		<fieldset class="form">' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Tipo: </label><br>							' +
	'				<select name="tipo" class="text ui-widget-content ui-corner-all form">' +
	'					' +tipo+
	'					<option value="1">Sistema</option>' +
	'					<option value="2">Manutenção</option>' +
	'					<option value="3">Proc.Interno</option>' +
	'				</select>' +
	'			</fieldset>' +
	'			<fieldset class="fieldInterno">	' +
	'				<label>Nível: </label><br>	' +
	'				<select name="nivel" class="text ui-widget-content ui-corner-all form">' +
	'					' +nivel+
	'					<option value="1" class="vermelho">Nível 1 </option>' +
	'					<option value="2" class="laranja">Nível 2 </option>' +
	'					<option value="3" class="amarelo">Nível 3 </option>' +
	'					<option value="4" class="azul">Nível 4 </option>' +
	'					<option value="5" class="verde">Nível 5 </option>' +
	'				</select>' +
	'			</fieldset>' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Previsão: </label><br>' +
	'				<input type="text" value="'+data+'" name="data" onkeypress="return false;" placeholder="dd/mm/yyyy" title="Data da Previsão" class="text ui-widget-content ui-corner-all form data">' +
	'			</fieldset>' +
	'			<fieldset class="fieldInterno">' +
	'				<label>Operador:</label><br>' +
	'				<select name="usuario" class="text ui-widget-content ui-corner-all form">' +
	'					<option selected="selected" value="'+usuario+'">'+usuario+'</option>' +
	'					<option value="nenhum">--- Nenhum ----</option>' +
	'				</select>' +
	'			</fieldset>' +
	'		</fieldset>' +
	'	</fieldset>' +
	'   <input type="hidden" name="id" value="'+id+'">' +
	'	<input type="hidden" name="acao" value="'+acao+'"/>' +
	'</form>');
	$( "#editar_tarefa").dialog('option', 'title', 'Editar Tarefa Nº: '+id);
	carredarDatepicker();
	$( "#editar_tarefa" ).dialog( "open" );
}

// Atalhos teclado.
$(document).keydown(function(event) {
	// Atalho nova tarefa.
    if (event.altKey == true && (event.which == '81' || event.which == '113')) {
    	nova_tarefa();
    }
    // Atalho listar minhas
    if (event.altKey == true && (event.which == '49')) {
    	carrega_modulo('tarefa.listarMinhas');
    }

    // Atalho listar avulsas
    if (event.altKey == true && (event.which == '50')) {
    	carrega_modulo('tarefa.listarAvulsas');
    }

    // Atalho listar excluidas
    if (event.altKey == true && (event.which == '51')) {
    	carrega_modulo('tarefa.listarExcluidas');
    }

    // Atalho listar finalizadas
    if (event.altKey == true && (event.which == '52')) {
    	carrega_modulo('tarefa.listarFinalizadas');
    }
});
