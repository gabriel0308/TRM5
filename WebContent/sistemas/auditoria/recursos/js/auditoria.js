/* Funçoes exclusicas de auditoria.
 * ----------------------------------------- */
 /* oculta e define tabela */
$( document ).ready(function() {
	// Oculta a tabela
	$( "#table_wrapper").hide();
	$( "#table" ).hide();
	if ( $("table").length ){
		$( ".tabela").dataTable( {
			"order": [[ 0, "desc" ]],
			"lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
			stateSave: true
		});
	}
});

/* carrega e exibe tabela*/
$(window).load(function() {
	$( "#table" ).show(); 
	$( "#table_wrapper").show();
});

/* ------------ Categoria modulo ----------------------------- */
// Funcao de validacao.
function valida_cat_modulo(form){
	return validaTamanho(form,"input[name=categoria]");
}
// Cria o formulario.
cria_formulario(160,330, "#nova_categoria", "#form_nova_cat_modulo", valida_cat_modulo, null, null);

// Define funcao para chamada de formulario.
function nova_categoria(){
	$( "#nova_categoria" ).dialog( "open" );
	$( "#nova_categoria input[name=categoria]").focus();
}
/* ---------------------------------------------------------- */

/* ------------ Alterar Chave ----------------------------- */
//Funcao de validacao.
function valida_chaveEmpresa(form){
	return validaTamanho(form, "input[name=chave]");
}
//Cria o formulario.
cria_formulario(160,330, "#nova_chaveSistema", "#form_nova_chaveSistema", valida_chaveEmpresa, null, null);

//Define funcao para chamada de formulario.
function alteraChave(id){
	// Remove focus dos botoes.
	$( "button" ).blur();
	$( "#nova_chaveSistema input[name=id]" ).val(id);
	$( "#nova_chaveSistema" ).dialog( "open" );
	$( "#nova_chaveSistema input[name=chave]").focus();
}
/* ---------------------------------------------------------- */

/* ------------ Alterar setor ----------------------------- */
//Funcao de validacao.
function valida_setor_edita(form){
	return validaTamanho(form, "input[name=descricao]");
}
//Cria o formulario.
cria_formulario(200,330, "#edita_setor", "#form_edita_setor", valida_setor_edita, null, null);

//Define funcao para chamada de formulario.
function alteraSetor(id, id_empresa, descricao){
	// Remove focus dos botoes.
	$( "button" ).blur();
	$( "select[name=id_empresa]").val(id_empresa).change();
	$( "#edita_setor input[name=descricao]" ).val(descricao);
	$( "#edita_setor input[name=id]" ).val(id);
	$( "#edita_setor" ).dialog( "open" );
	$( "#edita_setor input[name=descricao]").focus();
}
/* ---------------------------------------------------------- */

/* ------------ Novo setor ----------------------------- */
function valida_novo_setor(form){
	return validaTamanho(form, "input[name=descricao]") +
	       validaTamanho(form, "input[name=setor]");
}
function enviar_novo_setor(form){
	enviar_simples(form, valida_novo_setor);
}
/* ---------------------------------------------------------- */

/* ------------ modulo ----------------------------- */
//Funcao de validacao.
function valida_modulo(form){
	return validaTamanho(form, "input[name=modulo]") +
		   validaTamanho(form, "input[name=descricao]");
}
// Cria o formulario.
cria_formulario(200,330, "#novo_modulo", "#form_novo_modulo", valida_modulo, null, null);

// Define funcao para chamada de formulario.
function novo_modulo(){
	$( "#novo_modulo" ).dialog( "open" );
	$( "#novo_modulo input[name=modulo]").focus();
}
/* ---------------------------------------------------------- */

/* ------------  perfil ----------------------------- */
//Funcao de validacao.
function valida_novo_perfil(form){
	return validaTamanho(form, "input[name=perfil]") +
	       validaTamanho(form, "input[name=descricao]");
}
function valida_editar_perfil(form){
	return validaTamanho(form, "input[name=descricao]");
}
//Cria o formulario.
cria_formulario(200,330, "#novo_perfil", "#form_novo_perfil", valida_novo_perfil, null, null);
//Cria o formulario.
cria_formulario(160,330, "#editar_perfil", "#form_editar_perfil", valida_editar_perfil, null, null);
//Define funcao para chamada de formulario.
function novo_perfil(){
	$( "#novo_perfil" ).dialog( "open" );
	$( "#novo_perfil input[name=perfil]").focus();
}
//Define funcao para chamada de formulario.
function alteraPerfil(id, perfil, descricao){
	// Remove focus dos botoes.
	$( "button" ).blur();
	$( "#editar_perfil").dialog('option', 'title', "Editar perfil: " + perfil);
	$( "#editar_perfil input[name=id]" ).val(id);
	$( "#editar_perfil input[name=descricao]" ).val(descricao);
	$( "#editar_perfil" ).dialog( "open" );
	$( "#editar_perfil input[name=descricao]").focus();
}
/* ---------------------------------------------------------- */

/* -------------------Edita acessos -------------------------*/
// Seleciona ckb com os acessos
//Seleciona todos  os checkboxs e envia em um vetor de string.
function envia_acessos(form, datatable){
	var allVals = [];
	// Obtem componente table
	var table = $(datatable).DataTable();
	// Para cada input com name = ckb
	table.$("input[name='ckb'], select").each(function() {
    	// verifica se esta checked
    	if(this.checked == true){
    		// Armazena no array o value de cada campo.
    		allVals.push($(this).val());
    	}
    });
    // Adiciona todos os valores dos ckb no array allVals
    $( form +" input[name=allVals]" ).val(allVals);
	// Captura função padrao de envio do formulario
	$( form ).on( "hidden", function( event ) {
		event.defaultPrevented();
	});	
    // Envia o form.
    $( form ).submit();
    // Carregando
    carregando();
}