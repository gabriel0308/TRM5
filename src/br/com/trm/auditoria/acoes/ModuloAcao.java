package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.CategoriaModuloDao;
import br.com.trm.auditoria.dao.ModuloDao;
import br.com.trm.auditoria.modelos.Modulo;
import br.com.trm.sistema.central.Acao;

public class ModuloAcao extends Acao {
	private ModuloDao moduloDao = new ModuloDao();
	private CategoriaModuloDao categoriaModuloDao = new CategoriaModuloDao();
	
	public ModuloAcao(HttpServletRequest requisicao, HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
		// Define conjunto de formularios
		setFormulario("modulo");
	}
	
	@Override
	protected Object obterParametros() {
		Modulo modulo = new Modulo();
		// Adiciona valores.
		if (parametros.get("id_categoria") != null){
			modulo.setId_categoria(Integer.parseInt(parametros.get("id_categoria")));	
		}
		modulo.setModulo(parametros.get("modulo"));
		modulo.setDescricao(parametros.get("descricao"));
		return modulo;
	}

	// Listar todas as categorias
	public String listar(){
		// Pagina padrao
		String pagina = "sistemas/auditoria/modulo/listarCategoria.jsp";
		//Valida acao
		try {
			if (verificaAcao("selecionar")) {
				// Lista os módulos de uma categoria
					if (parametros.get("camada").equalsIgnoreCase("1")){
						setAttribute("camada","Front-and");
					}else{
						setAttribute("camada","Back-and");
					}
					setAttribute("categoria", parametros.get("categoria"));
					setAttribute("id_categoria", parametros.get("id_categoria"));
					setAttribute("modulos", moduloDao.listar((Modulo) obterParametros()));
					// Retorna pagina com lista de modulos
					pagina = "sistemas/auditoria/modulo/listar.jsp";
			}
			// Lista as categorias a selecionar
			setAttribute("dadosback", categoriaModuloDao.listar(0));
			setAttribute("dadosfront", categoriaModuloDao.listar(1));
			// Retorna pagina com lista de modulos
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.	
		return pagina;
	}
	
// Metodo para incluir um módulo
	public String incluir() {
		// Valida Acao
		if (verificaAcao("incluir")) {
			try {
				if (moduloDao.incluir((Modulo)obterParametros())) {
					// Exibe mensagem de sucesso
					setMensagemSucesso("Registro incluído com sucesso!");
				}
			// Se retornar exception adiciona uma menssagem.
			} catch (SQLException e) {
				setMensagemErro("Não foi possível incluir",e);
			}
		}
		// Por fim exibe a modulo
		return listar();
	}

// Metodo que exluir um modulo
	public String excluir()  {
		// Verifica a acao é igual a "Excluir"
		if (verificaAcao("excluir")) {
			try {
				if (moduloDao.excluir((Modulo)obterParametros())) {
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