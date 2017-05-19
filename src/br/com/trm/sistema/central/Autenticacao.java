package br.com.trm.sistema.central;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.AcessoDao;
import br.com.trm.sistema.modelos.Logon;

public class Autenticacao implements Filter, ServletContextListener {
	
	private static String CHAVE_AUTENTICADO = new String("CHAVE_AUTENTICADO");
	private static Logon logon;
	public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain corrente) throws IOException, ServletException {
		filtro((HttpServletRequest) request, (HttpServletResponse) response, corrente);
	}

	public void filtro(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain corrente) throws IOException, ServletException {
		// Instancia objeto logon
		logon = new Logon();
		// Verifica se o atributo 'CHAVE_AUTENTICADO' está null então...
		if (requisicao.getSession(false) != null && requisicao.getSession(false).getAttribute(CHAVE_AUTENTICADO) == null) {
			try {
				// Valida login do usuario e senha, se retirornar true então...
				if (validarLogin(requisicao)) {
					// Metodo para criar a sessão do usuario.
					//System.out.println("Cria Sessão");
					criarSessao(requisicao);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// Se houver erro seta o atributo.
				requisicao.setAttribute("erro","Erro de conexão.");
			}
		}
		
		// Metodo que verifica se o usuario quer encerrar a sessao. se retornar true então...
		if (encerrarSessao(requisicao)) {
			// Eenvia a reposta.
			resposta.sendRedirect(requisicao.getContextPath());
			// Finaliza a Sessão.
			//System.out.println("finaliza sessao");
			requisicao.getSession(false).invalidate();
			// Seta logado false
			logon.setLogado(false);
			// Se nada ocorrer e a chave CHAVE_AUTENTICADO for true então...
		} else {
			// Continua com a corrente.
			//System.out.println("continua corrente");
			corrente.doFilter(requisicao, resposta);
		}
	}
	
	// Metodo que verificar se o atributo 'acao' é igual a 'encerrar', retornando true se sim.
	private boolean encerrarSessao(HttpServletRequest requisicao) {
		String acao = requisicao.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("encerrar")) {
			return true;
		}
		return false;
	}
	
	// Metodo que busca o login e senha no banco, e valida.
	private boolean validarLogin(HttpServletRequest requisicao) throws SQLException {
		// Armazena em uma string o paramentro 'acao' enviado do jsp.
		String acao = requisicao.getParameter("acao");
		// Verifica se 'acao'for igual a 'validar'.
		if (acao != null && acao.equalsIgnoreCase("validar")) {
			// Cria e instancia o objeto de persistencia de acesso.
			AcessoDao acessoDao = new AcessoDao();
			// Pega os atributos de autencicação.
			String usuario = requisicao.getParameter("logon_usuario").toUpperCase();
			String   senha = requisicao.getParameter("logon_senha");
			// Valida no banco e retorna dados do usuario.
			logon = acessoDao.validaLoginAcesso(usuario, senha);
			// Se logado for false, dispare erro de login.
			if(!logon.isLogado()){
				requisicao.setAttribute("erro","Acesso negado! <br> Verifique usuário e senha.");
			}
		}
		return logon.isLogado();
	}

	// Metodo que Cria a sessão do usuario.
	private void criarSessao(HttpServletRequest requisicao) {
		// Cria a sessão.
		requisicao.getSession(true);
		// Seta os atributos de sessão correspondente ao usuario.
		requisicao.getSession(false).setAttribute(CHAVE_AUTENTICADO, true);
		requisicao.getSession(false).setAttribute("userName", logon.getUsuario());
		requisicao.getSession(false).setAttribute("userId", logon.getId());
		requisicao.getSession(false).setAttribute("acessos", logon.getAcessos());
		requisicao.getSession(false).setAttribute("perfil", logon.getPerfil());
		requisicao.getSession(false).setAttribute("setor", logon.getSetor());
		requisicao.getSession(false).setAttribute("codEmpresa", logon.getCodEmpresa());
		requisicao.getSession(false).setAttribute("nomeEmpresa", logon.getNomeEmpresa());
		// requisicao.getServletContext().setAttribute("nome", "valor"); usar no contexto da aplicacao
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();
        EnumSet<SessionTrackingMode> modes = EnumSet.of(SessionTrackingMode.SSL);
        context.setSessionTrackingModes(modes);
	}
}