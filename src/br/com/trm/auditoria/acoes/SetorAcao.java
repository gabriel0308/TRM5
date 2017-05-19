package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.EmpresaSistemaDao;
import br.com.trm.auditoria.dao.SetorDao;
import br.com.trm.auditoria.modelos.Setor;
import br.com.trm.sistema.central.Acao;

public class SetorAcao extends Acao {
	private SetorDao setorDao = new SetorDao();
	private EmpresaSistemaDao empresaSistemaDao = new EmpresaSistemaDao();
	
	public SetorAcao(HttpServletRequest requisicao, HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
		// Define conjunto de formularios
		setFormulario("setor");
	}
	
	@Override
	protected Object obterParametros() {
		Setor setor = new Setor();
		// Adiciona valores.
		if (parametros.get("id_empresa") != null){
			setor.setIdEmpresa(Integer.parseInt(parametros.get("id_empresa").toString()));
		}
		if (parametros.get("id") != null){
			setor.setId(Integer.parseInt(parametros.get("id").toString()));
		}
		setor.setSetor(parametros.get("setor"));
		setor.setDescricao(parametros.get("descricao"));
		return setor;
	}
	
// Listar todas os setores.
	public String listar(){
		try {
			setAttribute("setores", setorDao.listar());
			setAttribute("empresas", empresaSistemaDao.listar(0));
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.	
		return "sistemas/auditoria/setor/listar.jsp";
	}
	
// Metodo para incluir setor
	public String incluir() {
		String pagina = "sistemas/auditoria/setor/incluir.jsp";
		// Valida Acao
		if (verificaAcao("incluir")) {
			try {
				if (setorDao.incluir((Setor)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro incluído com sucesso!");
					//listar setores
					pagina = listar();
				}
			// Se retornar exception de  ConstraintViolationException adiciona uma menssagem.
			} catch (SQLException e) {
				setMensagemErro("Não foi possível incluir",e);
			}
		}
		// Carrega lista de emrpresas p usar no formulario
		try {
			setAttribute("empresas", empresaSistemaDao.listar(0));
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		// Retorna pagina com formulario
		return pagina;
	}

// Metodo que altera o setor
	public String alterar()  {
		// Verifica a acao
		if (verificaAcao("alterar")) {
			try {
				if (setorDao.alterar((Setor)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro alterado com sucesso!");
				}
			} catch (SQLException e) {
				setMensagemErro("Erro ao alterar!",e);
			}
		}
		// Por fim exibe a lista
		return listar();
	}
	
// Metodo que exluir um setor
	public String excluir()  {
		// Verifica a acao
		if (verificaAcao("excluir")) {
			try {
				if (setorDao.excluir((Setor)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro excluído com sucesso!");
				}
			} catch (SQLException e) {
				setMensagemErro("Erro ao excluir!",e);
			}
		}
		// Por fim exibe a lista
		return listar();
	}
}