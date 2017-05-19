package br.com.trm.auditoria.acoes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.sistema.central.Acao;

public class AuditoriaInicioAcao extends Acao {
	private String inicioJsp = "sistemas/auditoria/inicio.jsp";
	
	public AuditoriaInicioAcao(HttpServletRequest requisicao, HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
	}
	
	@Override
	protected Object obterParametros() {
		return null;
	}
	
// Listar todas as categorias.
	public String inicio(){
		//retorna o caminho da pagina.	
		return inicioJsp;
	}
}