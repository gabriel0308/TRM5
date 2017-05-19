package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.EmpresaSistemaDao;
import br.com.trm.auditoria.modelos.EmpresaSistema;
import br.com.trm.sistema.central.Acao;

public class EmpresaSistemaAcao extends Acao {
	private EmpresaSistemaDao empresaSistemaDao = new EmpresaSistemaDao();
	
	public EmpresaSistemaAcao(HttpServletRequest requisicao, HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
		// Define conjunto de formularios
		setFormulario("empresaSistema");
	}
	
	@Override
	protected Object obterParametros() {
		EmpresaSistema empresaSistema = new EmpresaSistema();
		// Adiciona valores.
		if (parametros.get("id") != null){
			empresaSistema.setId(Integer.parseInt(parametros.get("id")));	
		}
		empresaSistema.setNome(parametros.get("categoria"));
		empresaSistema.setChave(parametros.get("chave"));
		return empresaSistema;
	}
	
// Listar todas as empresas do sistema
	public String listar(){
		try {
			setAttribute("dados", empresaSistemaDao.listar(1));
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.	
		return "sistemas/auditoria/empresa_sistema/listar.jsp";
	}
	
// Metodo para alterar chave de uma empresa
	public String alterar() {
		// Valida Acao
		if (verificaAcao("alterar")) {
			try {
				if (empresaSistemaDao.alterar((EmpresaSistema)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Chave alterada com sucesso!");
				}
			// Se retornar exception de  ConstraintViolationException adiciona uma menssagem.
			} catch (SQLException e) {
				setMensagemErro("Não foi possível alterar",e);
			}
		}
		// Por fim exibe a lista
		return listar();
	}
}