package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.CategoriaModuloDao;
import br.com.trm.auditoria.modelos.CategoriaModulo;
import br.com.trm.sistema.central.Acao;

public class CategoriaModuloAcao extends Acao {
	private CategoriaModuloDao categoriaModuloDao = new CategoriaModuloDao();
	
	public CategoriaModuloAcao(HttpServletRequest requisicao, HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
		// Define conjunto de formularios
		setFormulario("categoria");
	}
	
	@Override
	protected Object obterParametros() {
		CategoriaModulo categoriaModulo = new CategoriaModulo();
		// Adiciona valores.
		if (parametros.get("id") != null){
			categoriaModulo.setId(Integer.parseInt(parametros.get("id")));	
		}
		categoriaModulo.setCategoria(parametros.get("categoria"));
		categoriaModulo.setNivel(parametros.get("nivel"));
		return categoriaModulo;
	}
	
// Listar todas as categorias.
	public String listar(){
		try {
			setAttribute("dadosback", categoriaModuloDao.listar(0));
			setAttribute("dadosfront", categoriaModuloDao.listar(1));
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.	
		return "sistemas/auditoria/modulo/categoria/listar.jsp";
	}
	
// Metodo para incluir categoria
	public String incluir() {
		// Valida Acao
		if (verificaAcao("incluir")) {
			try {
				if (categoriaModuloDao.incluir((CategoriaModulo)obterParametros())) {
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

// Metodo que exluir uma categoria
	public String excluir()  {
		// Verifica a acao é igual a "Excluir"
		if (verificaAcao("excluir")) {
			try {
				if (categoriaModuloDao.excluir((CategoriaModulo)obterParametros())) {
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