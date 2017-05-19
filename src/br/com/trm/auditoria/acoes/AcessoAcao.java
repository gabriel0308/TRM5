package br.com.trm.auditoria.acoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trm.auditoria.dao.AcessoDao;
import br.com.trm.auditoria.dao.CategoriaModuloDao;
import br.com.trm.auditoria.modelos.Acesso;
import br.com.trm.sistema.central.Acao;
public class AcessoAcao extends Acao
{
	public AcessoAcao(HttpServletRequest requisicao,HttpServletResponse resposta) {
		super(requisicao, resposta);
		// Define o ambiente de sistema
		setSistema("auditoria");
	}

	private AcessoDao acessoDAO = new AcessoDao();
	private CategoriaModuloDao categoriaModuloDao = new CategoriaModuloDao();
	
	@Override
	protected Object obterParametros() {
		Acesso acesso = new Acesso();
		// Adiciona valores.
		if (parametros.get("id_categoria") != null){
			acesso.setId_categoria(Integer.parseInt(parametros.get("id_categoria")));	
		}
		if (parametros.get("id_perfil") != null){
			acesso.setId_perfil(Integer.parseInt(parametros.get("id_perfil")));	
		}
		acesso.setModulo(parametros.get("modulo"));
		acesso.setPerfil(parametros.get("perfil"));
		acesso.setCategoria(parametros.get("categoria"));
		acesso.setDescricao(parametros.get("descricao"));
		acesso.setOpe_cria(getId_usuario());
		acesso.setOpe_edicao(getId_usuario());
		return acesso;
	}
	
	//Esse metodo faz tanta coisa que fica mais facil analizar o codigo do que explicar....
	private List<Acesso> obterListaAcessoPreenchido(List<Acesso> listacessos) throws SQLException {
		// Obtem acessoAtual com as informações de perfil.
		Acesso acessoAtual = (Acesso)obterParametros();
		// Instancia lista de retorno
		List<Acesso> lista = new ArrayList<Acesso>();
		// Para cada módulo da coleção "listaModulos"
		for (Acesso acesso: listacessos){
			Acesso a = new Acesso();
			// Adiciona o modulo
			a.setModulo(acesso.getModulo());
			a.setId_modulo(acesso.getId_modulo());
			a.setPerfil(acessoAtual.getPerfil());
			a.setId_perfil(acessoAtual.getId_perfil());
			a.setOpe_cria(getId_usuario());
			a.setOpe_edicao(getId_usuario());
			// Captura campos da tela.
			String allIds = parametros.get("allVals");
			String[] listaIds = allIds.split(",");
			// Verifica se existe conteudo.
			if(listaIds != null && listaIds.length >= 1){
				for(int i = 0; i < listaIds.length; i++){
					// Se for igual seta true
					if(a.getId_modulo() == Integer.parseInt(listaIds[i])){
						a.setSimnao(true);
					}
				}
			}
			// Adiciona na lista
			lista.add(a);
		}
	return lista;
	}

	//===========================================================================================================================
	//Metodo que lista os acessos do perfil inicialmente selecionado.
	public String listar() {
		// Obtem dados da página para um objeto
		Acesso acessoAtual = (Acesso)obterParametros();
		// Instancia objeto colecao.
		List<Acesso> listacessos = new ArrayList<Acesso>();
		// Obtem a lista de acessos do banco.
		try {
			listacessos = acessoDAO.listarAcessosCategoriaPerfil(acessoAtual);
			// Seta acesso atual.
			setAttribute("acessoAtual", acessoAtual);
			// Seta tipo
			setAttribute("fronback", getParameter("fronback"));
			// Verifica se "acao" é igual a "salvar"
			if (verificaAcao("salvar")) {
				// Para cada iten da lista.
				for(Acesso a : obterListaAcessoPreenchido(listacessos)){
					// Verifica se o acessso for true, e se nao existe acesso adiciona.
					if(a.isSimnao() && !acessoDAO.verificaAcesso(a)){
						System.out.println("Incluir: "+a.getId_modulo());
						acessoDAO.incluir(a);
					}else
					//Verifica se o acessso for false, e se o acesso existe. se existe exclui!
					if(!a.isSimnao() && acessoDAO.verificaAcesso(a)){
						System.out.println("Excluir: "+a.getId_modulo());
						acessoDAO.excluir(a);
					}
				}
				// Seta mensagem de sucesso
				setMensagemSucesso("Perfil editado com sucesso!");
				// Atualiza novamente os acessos.
				listacessos = acessoDAO.listarAcessosCategoriaPerfil(acessoAtual);
			}
			// Seta lista de acessos na pagina.
			setAttribute("modulos", listacessos);
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.		
		return "sistemas/auditoria/acesso/listar.jsp";
	}
	
	// Metodo que lista categoria de modulos para seleção.
	public String listarCategoria() {
		// Dados do acesso e perfil selecionado.
		setAttribute("acessoAtual", (Acesso)obterParametros());
		// Tenta buscar uma lista de dados  e salvar em um atributo.
		try {
			setAttribute("dadosback", categoriaModuloDao.listar(0));
			setAttribute("dadosfront", categoriaModuloDao.listar(1));
		} catch (SQLException e) {
			setMensagemErro("Erro na solicitação!", e);
		}
		//retorna o caminho da pagina.		
		return "sistemas/auditoria/acesso/listarCategoria.jsp";
	}
}