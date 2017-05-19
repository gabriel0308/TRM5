package br.com.trm.sistema.central;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.AcessoDao;

public class Controle extends HttpServlet implements ServletContextListener {
	private static final long serialVersionUID = 1L;
	private Properties propriedades;
	private AcessoDao acessoDAO;

	private Acao criarAcao(String nome, HttpServletRequest requisicao, HttpServletResponse resposta) throws Exception{
		/* Metodo que verifica se existe no arquivo controle.properties a chave (String nome)
		 * que deverá retornar um caminho e nome completo da classe.
		 * se existir irá instanciala pelo metodo Class.forName() e retornar o objeto Acao
		 * com essa instancia.
		 */
		String acao = propriedades.getProperty(nome);
		if (acao == null || acao.isEmpty()) {
			throw new Exception("Ação inválida!");
		}
		Constructor<?> classe = Class.forName(acao).getConstructor(HttpServletRequest.class, HttpServletResponse.class);
		return (Acao) classe.newInstance(requisicao, resposta);
	}
	
	//Metodo que recebe a instancia da classe mais uma String com o nome do metodo a ser evocado.
	//Retornando o caminho e o arquivo da página a ser exibida.
	private String executarAcao(String metodo, Object instancia) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?>[] tipos = null;
		Object[] valores = null;
		//Salva na variável o metodo, que é localizado dentro da instancia pela var "metodo"
		Method metodod = instancia.getClass().getMethod(metodo, tipos);
		//Salva na Variável o retorno da execução desse Metodo da instancia.
		String tela = metodod.invoke(instancia, valores).toString();
		return tela;//e retorna o caminho do JSP
	}

	///inicializa parametros de configuraçoes quanto carrega o servidor.
	public void init() throws ServletException {
		// Cria objeto dao para persistencias de acessos.
		acessoDAO = new AcessoDao();
		// Inicializa o properties
		propriedades = new Properties();
		try {
			// Carrega o arquivo de classes.
			propriedades.load(getClass().getClassLoader().getResourceAsStream("controle.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Metoddo principal front-end e validação e retorno de páginas.
	protected void service(HttpServletRequest requisicao, HttpServletResponse resposta){
		// Pagina com a lista de sistemas.
		String pagina = "/sistemas/principal/inicio.jsp";
		// Verifica se o usuario esta autenticado.
		if (requisicao.getSession(false) != null && requisicao.getSession(false).getAttribute("CHAVE_AUTENTICADO") != null) {
			// Define ambiente de sistema atual
			requisicao.getSession(false).setAttribute("sistema", "principal");
			// Obtem o PatchInfo digitado na barra endereço do navegador e armazena em String.
			String informacao = requisicao.getPathInfo();
			// Valida se esta neste formato: "/usuario.listar"
			if (informacao.matches("^\\/\\w+\\.\\w+$")) {
				// Depois separa com split onde tem ponto.
				String[] classeMetodo = informacao.split("\\.");
				String classe = classeMetodo[0].substring(1); // usuario
				String metodo = classeMetodo[1];              // listar
				try {
					// Verifica se esta autenticado e se o perfil do usuario tem acesso a pagina solicitada.
					if(acessoDAO.TemAcesso(informacao.substring(1), requisicao.getSession(false).getAttribute("perfil").toString())){
						Acao acao = criarAcao(classe, requisicao, resposta);
						pagina = executarAcao(metodo, acao);
					}else{
						// Seta mensagem de acesso negado.
						requisicao.setAttribute("erro", "Usuário sem acesso ao Módulo: <br><b>"+informacao.substring(1)+"</b>");
					}
				} catch (NoSuchMethodException e){
					requisicao.setAttribute("erro", "Ação inválida!");
					e.printStackTrace();
				} catch (SQLException e) {
					requisicao.setAttribute("erro", "Erro na validação de acessos!");
					e.printStackTrace();
				} catch (Exception e) {
					requisicao.setAttribute("erro", e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			// Pagina de login.
			pagina = "/autenticacao.jsp";
			// Verifica se a sessao for null. se nao entao..
			if(requisicao.getSession(false)!=null){
				// Encerra sessao.
				requisicao.getSession(false).invalidate();
			}
		}
		
		// Adicionando atributo pagina com a página a ser importada no gabarito.
		requisicao.setAttribute("pagina", pagina);
		// Verifica se o response foi alterado, caso nao execute o forward.
 		if (!resposta.isCommitted()){ 
			try {
				requisicao.getRequestDispatcher("/gabarito.jsp").forward(requisicao, resposta);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
		return;  
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