/* Funçoes principais do sistemas
 * ----------------------------------------- */
 /* oculta e define parametros */
 $( "html" ).hide();
/* carrega e exibe */
$(window).load(function() {
	// Carrega html
	$( "html" ).show('fade', {percent: 70}, 450, null);
	// Desabilita botao direito do mouse.
	//$(document).bind("contextmenu",function(e){return false;});
	// Tooltip
    $( document ).tooltip({
    	hide: null,
    	show: null,
    });
    // Tabs
    $( ".tabs" ).tabs();
	// Reseta scroll
	$( document ).scrollTop(0);
	// Define botao padrao
	$(".botao_padrao").button();
	// Carrega o datapicker.
	carredarDatepicker();
	// Quando o campo allckb for clikado. //mudar para classe
	$("#allckb").click(function(){
		// Seta a variável checked_status como checked.
		var checked_status = this.checked;
		// Para cada campo input.
		$("input[name='ckb']").each(function(){
			// define checked o campo atual.
			this.checked = checked_status;
		});
	});
	
	// Mensagem de carregamento.
	$(function() {
		$( "#carregando" ).dialog({
			autoOpen: false,
		    modal: true,
		    hide: true,
			height:40,
			width: 200,
			resizable: false,
			draggable: false,
			dialogClass: "login",
		});
	});
	  
	// Mensagem de sucesso popup
	$(function() {
		// Oculta caixa de mensagem
		$( "#mgs_sucesso" ).hide();
		// Se houver mensagem de sucesso, exibe.
		var text_mgs = $("#text_mgs").text();
		if (text_mgs.length > 0){
			mostra_msg_sucesso();
		}
	});	
	
	// Mensagem de erro
	$(function() {
		$( "#msg_erro" ).dialog({
			autoOpen: false,
		    modal: true,
			resizable: false,
			draggable: false,
			show: {effect: "fade",duration: 100},
			hide: {effect: "fade",duration: 100},
		    dialogClass: "title_red",
		    buttons: {
	          Ok: function() {
	            $( this ).dialog( "close" );
	          }
	        }
		});
		var erroFlag = $("#erro").text();
		if (erroFlag.length > 0){
			$( "#msg_erro" ).dialog( "open" );
		}
	});

	// Mensagem de erro formulario
	$(function() {
		$( "#form_erro" ).dialog({
			autoOpen: false,
		    modal: true,
			resizable: false,
			draggable: false,
			show: {effect: "fade",duration: 100},
			hide: {effect: "fade",duration: 100},
		    dialogClass: "title_red",
		    buttons: {
	          OK: function() {
	            $( this ).dialog( "close" );
	          }
		    }
		});
	});
	
	// tela de confirmacao de acoes
	$(function() {
		var dialog, form;
		dialog = $( "#confirma_dialog" ).dialog({
			autoOpen: false,
			modal: true,
			draggable: false,
			resizable: false,
			show: {effect: "fade",duration: 100},
			hide: {effect: "fade",duration: 100},
			buttons: {
				"Confirmar": function() {
					// Captura o formulario dentro da div
					form = dialog.find( "form" ).on( "hidden", function( event ) {
						event.defaultPrevented();
					});
					// Envia
					form.submit();
					// comando do botao Confirmar
					$( this ).dialog( "close" );
					carregando();
				},
				"Cancelar": function() {
					// comando do botao Cancelar
					$( this ).dialog( "close" );
				}
			}
		});
	});
});

// Carrega a tela de carregamento.
function carregando(){
	$( "#carregando" ).dialog("open");
}

// Carrega datapicker no campo data.
function carredarDatepicker(){
	$(".data").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0",
		dateFormat: 'dd/mm/yy',
		dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		nextText: 'Proximo',
		prevText: 'Anterior'
	});	
}
//Funcao que executa efeito drop depois de 2 segundos
function descarta_msg() {
	setTimeout(function() {
	$( "#mgs_sucesso:visible" ).hide("slide", { direction: 'right', mode: 'hide', distance :500});
	}, 3000 );
};

// Exibe a tela de menssagem com efeito.
function mostra_msg_sucesso() {
	setTimeout(function() {
	$( "#mgs_sucesso" ).show( "drop", { direction: 'up', mode: 'show', distance :500}, 500, descarta_msg );
	}, 250 );
};

// Carrega modulos.
function carrega_modulo(modulo){
	carregando();
	window.location=modulo;
}

// Remove classe de erro dos componentes de formularios.
function removeStatusErro(form){
	$(form+ ' input, '+form+ ' select, '+form+ ' textarea').each(
		function(index){  
			var input = $(this);
			input.removeClass( "ui-state-error" );
		}
	);
}

// Valida campos vazios.
function validaTamanho(form, campo ) {
	campo = $(form + " " + campo);
	var s = 0;
	if ( campo.val().length < 1 || campo.val() == "" ) {
		campo.addClass( "ui-state-error" );
		campo.focus();
		s = 1;
	}else{
		campo.removeClass( "ui-state-error" );
	}
	return s;
}

// Valida radio button.
function validaRadio(form, campo){
	campo = $(form + " " + campo);
	var s = 0;
	if(campo.filter(':checked').val() == null){
		campo.focus();
		campo.css("color","red");
		s = 1;
	}else{
		campo.css("color","black");
		s = 0;
	} 
	return s;
}

// Valida campos selects
function validaSelect(form, campo){
	campo = $(form + " " + campo);
	var s = 0;
	if(campo.filter('option:selected').val() == 0){
		campo.addClass( "ui-state-error" );
		campo.focus();
		s = 1;
	}else{
		campo.removeClass( "ui-state-error" );
		s = 0;
	} 
	return s;
}

//Funcao de enviar.
function enviar(form, funcao, dialog){
	// acao do botao Confirmar
	// Valida campos
	if(funcao(form) == 0){
		// Envia
		$( form ).submit();
		$( form )[0].reset();
		$( dialog ).dialog( "close" );
		carregando();
	}else{
		// Exibe mensagem de erro.
		$( "#form_erro" ).html("<br>Preencher campos obrigatórios!");
		$( "#form_erro" ).dialog().dialog( "open" );
	}
}

//Funcao de enviar.
function enviar_simples(form, funcao){
	// acao do botao Confirmar
	// Valida campos
	if(funcao(form) == 0){
		// Envia
		$( form ).submit();
		$( form )[0].reset();
		carregando();
	}else{
		// Exibe mensagem de erro.
		$( "#form_erro" ).html("<br>Preencher campos obrigatórios!");
		$( "#form_erro" ).dialog().dialog( "open" );
	}
}

//tela de formulario padrao generico
function cria_formulario(altura, largura, div, form, funcao, acao1, acao2){
	// Remove focus dos botoes.
	$( "button" ).blur();
	$(function() {
		var dialog;
		dialog = $( div ).dialog({
			autoOpen: false,
			height: altura,
			width: largura,
			modal: true,
			draggable: true,
			resizable: false,
			show: {effect: "fade",duration: 100},
			hide: {effect: "fade",duration: 100},
			buttons: {
				"Confirmar": function() {
					enviar(form, funcao, dialog);
				},
				"Cancelar": function() {
					// acao do botao Cancelar
					$( form )[0].reset();
					removeStatusErro(form);
					$( this ).dialog( "close" );
				}
			},
			// Atalho de teclas
			open: function(){
				dialog.keydown(function(e) {
					// Ctrl+enter
					if ( e.ctrlKey == true && (e.keyCode == '10' || e.keyCode == '13')) {
						enviar(form, funcao, dialog);
				    }
					// Enter
					if (e.keyCode == '10' || e.keyCode == '13'){
						return false;
				    }
					// Valida de as acoes de atalhos sao diferentes de null
					if(acao1 != null){
						// Alt+W
						if ( e.altKey == true && (e.keyCode == '87' || e.keyCode == '119')) {
							$( form+" input[name=acao]").val(acao1);
							enviar(form, funcao, dialog);
					    }
						// Alt+C
						if ( e.altKey == true && (e.keyCode == '67' || e.keyCode == '99')) {
							$( form+" input[name=acao]").val(acao2);
							enviar(form, funcao, dialog);
					    }				
					}
				});		
			}
		});
	});		
}

//Funcao de confirmacao de tela generico.
function confirma_dialog(acao, action, mensagem, item, campoName, campoValor, classe){
	var opcao;
	// Remove focus dos botoes.
	$( "button" ).blur();
	// Limpa a div confirma_dialog
	$("#confirma_dialog").html("");
	// Define o titulo do dialog com uma classe.
	$( "#confirma_dialog" ).dialog({ dialogClass: classe});
	// Mosta a mensagem de exclusao
	opcao = "<br><p> "+mensagem+" "+item+" ?</p>";
	// Escreve o html na div confirma_dialog com os parametros da funcao
	$("#confirma_dialog").html(opcao +
		"<form method='post' action='"+action+"' enctype='application/x-www-form-urlencoded'>" +
		"	<input type='hidden' name='"+campoName+"' value='"+campoValor+"'>" +
		"	<input type='hidden' name='acao' value='"+acao+"'>" +
		"</form>"
	);
	// Por fim abre o dialog com a mensagem pronta.
	$( "#confirma_dialog" ).dialog().dialog( "open" );
}
/*
//Seleciona todos os checkboxs e envia em um vetor de string.
function selecionaCbk(acao, action, target){
	var allVals = [];
	// for para cada input com name = ckb
    $("input[name='ckb']").each(function() {
    	// verifica se esta checked
    	if(this.checked == true){
    		// Armazena no array o value de cada campo.
    		allVals.push($(this).val());
    	}
    });
    
	// se existe envia.
    if(allVals != null & allVals.length > 0){
    	$("#envia_checkbox").html(" ");
    	// Seta o html do formulario
    	$("#envia_checkbox").html(
    		"<form method='post' id='form1' action='"+action+"' target='"+target+"' enctype='application/x-www-form-urlencoded'>" +
    		"	<input type='hidden' name='allIds' value='"+allVals+"'>" +
    		"	<input type='hidden' name='acao' value='"+acao+"'>" +
    		"</form>"
    	);
    	// Captura função padrao de envio do formulario
    	$( "#form1" ).on( "hidden", function( event ) {
    		event.defaultPrevented();
    	});	
		// Envia
		$( "#form1" ).submit();
    }else{
    	// Se nao da erro.
		$( "#form_erro" ).html("<br><br>Nenhum item selecionado!!");
    	$( "#form_erro" ).dialog( "open" );
    }
}
*/




