package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.PerfilDao;
import br.com.trm.auditoria.dao.UsuarioDao;
import br.com.trm.auditoria.modelos.Usuario;
import br.com.trm.sistema.central.Acao;
import br.com.trm.utilitarios.Criptografia;

public class UsuarioAcao extends Acao {
	private UsuarioDao usuarioDao = new UsuarioDao();
	private PerfilDao perfilDao = new PerfilDao();
	
	public UsuarioAcao(HttpServletRequest requisicao,HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("sistema");
	}

	//Captura os dados da requisi��o e grava em um objeto
	@Override
	protected Object obterParametros() {
		// Cria objeto usuario
		Usuario usuario = new Usuario();
		// Adiciona valores.
		usuario.setId(Integer.parseInt(parametros.get("id")));
		usuario.setUsuario(parametros.get("usuario").toUpperCase().trim().replace(" ", ""));
		usuario.setNome(parametros.get("nome"));
		usuario.setSenha(Criptografia.criptografar(parametros.get("senha")));
		usuario.setPerfil(parametros.get("perfil").trim().replace(" ", ""));
		usuario.setOpe_edicao(getId_usuario());
		return usuario;
	}
	
	// Lista os usu�rios cadastrados.
	public String listar(){
		// Obtem lista de objetos do banco e guarda em um atributo.
		try {
			setAttribute("dados", usuarioDao.listar());
		} catch (SQLException e) {
			setMensagemErro("Erro de conex�o.", e);
		}
		//retorna o caminho da pagina.		
		return "auditoria/usuario/listar.jsp";
	}
	
	// Metodo para incluir usuarios.
	public String incluir() {
		// Valida acao.
		if (verificaAcao("incluir")) {
			try {
				if (usuarioDao.incluir( (Usuario) obterParametros())) {
					setMensagemSucesso("Registro inclu�do com sucesso!");
				}
			} catch (SQLException e) {
				setMensagemErro("Erro de conex�o!", e);
				e.printStackTrace();
			}
		}
		// Mostrar lista de perfil para o formul�rio.
		try {
			setAttribute("perfis", perfilDao.listar());
		} catch (SQLException e) {
			setMensagemErro("Erro de conex�o!", e);
		}
		//retorna o caminho da pagina.		
		return "auditoria/usuario/incluir.jsp";
	}
	
	// Metodo que altera usu�rios.
	public String alterar() {
		Usuario usuario = (Usuario) obterParametros();
		
		// Valida acao.
		if (verificaAcao("alterar")) {
			try {
				// Altera o usu�rio
				if (usuarioDao.alterar(usuario)) {
					// Seta mensagem de sucesso.
					setMensagemSucesso("Registro alterado com sucesso!");
				}
			} catch (SQLException e) {
				setMensagemErro("Erro de conex�o!", e);
			}
		}
		// busca perfis para o formul�rio.
		try {
			setAttribute("perfis", perfilDao.listar());
			setAttribute("dado", usuarioDao.buscar(usuario.getId()));
		} catch (SQLException e) {
			setMensagemErro("Erro de conex�o!", e);
		}
		//retorna o caminho da pagina.		
		return "auditoria/usuario/alterar.jsp";
	}

	// Metodo que exluir um item do banco.
	public String excluir()  {
		// Obtem dados prenchido da tela
		Usuario usuario = (Usuario) obterParametros();
		// Verifica se a acao � igual a "Excluir"
		if (verificaAcao("excluir")) {
			try {
				if (usuarioDao.excluir(usuario)) {
					// Atualiza tela depois da excluzao
					setAttribute("dado", usuarioDao.buscar(usuario.getId()));
					// Seta mensagem de sucesso
					setMensagemSucesso("Registro exclu�do com sucesso!");
				}
			} catch (SQLException e) {
				setMensagemErro("Erro ao excluir!", e);
			}
		}
		//retorna o caminho da pagina.		
		return "auditoria/usuario/excluir.jsp";
	}
}