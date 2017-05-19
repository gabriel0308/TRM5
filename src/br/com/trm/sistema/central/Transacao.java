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

import br.com.trm.utilitarios.BancoDeDados;

public class Transacao implements Filter, ServletContextListener {
	private static BancoDeDados banco;

	public static BancoDeDados getBanco() {
		return banco;
	}

	public void init(FilterConfig config) throws ServletException {
		banco = new BancoDeDados();
	}
	public void destroy() {}

	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain corrente) throws IOException, ServletException {
		try {
			banco.conectar();
			corrente.doFilter(requisicao, resposta);
			banco.desconectar();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
