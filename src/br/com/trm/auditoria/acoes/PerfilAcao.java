package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.PerfilDao;
import br.com.trm.auditoria.modelos.Perfil;
import br.com.trm.sistema.central.Acao;

public class PerfilAcao extends Acao{
	private PerfilDao perfilDao = new PerfilDao();
	
	public PerfilAcao(HttpServletRequest requisicao,HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
		// Define conjunto de formularios
		setFormulario("perfil");
	}

	@Override
	protected Object obterParametros() {
		Perfil perfil = new Perfil();
		// Adiciona valores.
		if (parametros.get("id") != null){
			perfil.setId(Integer.parseInt(parametros.get("id")));	
		}
		perfil.setPerfil(parametros.get("perfil"));
		perfil.setDescricao(parametros.get("descricao"));
		return perfil;
	}
	
	//	Faz a busca dos dados do banco e armazena em um atributo dados na session da pagina.
	public String listar(){
		//Busca lista de perfis do banco e guarda em um atributo
		try {
			setAttribute("dados", perfilDao.listar());
		} catch (SQLException e) {
			e.printStackTrace();
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.		
		return "sistemas/auditoria/perfil/listar.jsp";
	}
	
	// Metodo para incluir categoria
	public String incluir() {
		// Valida Acao
		if (verificaAcao("incluir")) {
			try {
				if (perfilDao.incluir((Perfil)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro incluído com sucesso!");
				}
			// Se retornar exception de  ConstraintViolationException adiciona uma menssagem.
			} catch (SQLException e) {
				setMensagemErro("Não foi possível incluir",e);
			}
		}
		// Por fim exibe a lista
		return listar();
	}

	// Metodo para alterar chave de uma empresa
	public String editar() {
		// Valida Acao
		if (verificaAcao("editar")) {
			try {
				if (perfilDao.alterar((Perfil)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro editado com sucesso!");
				}
			// Se retornar exception de ConstraintViolationException adiciona uma menssagem.
			} catch (SQLException e) {
				setMensagemErro("Não foi possível editar",e);
			}
		}
		// Por fim exibe a lista
		return listar();
	}
		
	// Metodo que exluir uma categoria
	public String excluir()  {
		// Verifica a acao é igual a "Excluir"
		if (verificaAcao("excluir")) {
			try {
				if (perfilDao.excluir((Perfil)obterParametros())) {
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