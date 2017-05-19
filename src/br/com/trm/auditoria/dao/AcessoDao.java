package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.Acesso;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.sistema.modelos.Logon;
import br.com.trm.utilitarios.BancoDeDados;
import br.com.trm.utilitarios.Criptografia;

public class AcessoDao
{
	private BancoDeDados banco = Transacao.getBanco();
	
	// Inclui acesso novo acesso na tabela de acessos.
	public boolean incluir(Acesso acessoModel) throws SQLException  {
		banco.prepararRequisicao("INSERT INTO cad_acesso (id_perfil, id_modulo, data_cria, ope_cria, data_edita, ope_edita) VALUES (?, ?, NOW(), ?, NOW(), ?)");
		banco.preencherRequisicao(1, acessoModel.getId_perfil());
		banco.preencherRequisicao(2, acessoModel.getId_modulo());
		banco.preencherRequisicao(3, acessoModel.getOpe_cria());
		banco.preencherRequisicao(4, acessoModel.getOpe_edicao());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
	
	// Listar modulos por categorias. e ja marca quais acessos tem para essse perfil.
	public List<Acesso> listarAcessosCategoriaPerfil(Acesso a) throws SQLException  {
		//Instancia lista de modulos
		List<Acesso> acessos = new ArrayList<Acesso>();
		// Preenche a query
		banco.prepararRequisicao("SELECT M.id_modulo, M.modulo, C.categoria, M.descricao FROM cad_modulo AS M "+
				"INNER JOIN cad_categoria_modulo C on(M.id_categoria_modulo = C.id_categoria_modulo) "+
				"WHERE C.id_categoria_modulo = ? ");
		
		// Adiciona parametro
		banco.preencherRequisicao(1,  a.getId_categoria());
		// Esexuta a selecao
		List<Map<String, Object>> dados = banco.selecionar();
		
		// Percorre o resultado
		for (Map<String, Object> dado : dados) {
			Acesso acesso = new Acesso();
			acesso.setId_modulo(Integer.parseInt(dado.get("id_modulo").toString()));
			acesso.setModulo(dado.get("modulo").toString());
			acesso.setCategoria(dado.get("categoria").toString());
			acesso.setDescricao(dado.get("descricao").toString());
			acesso.setPerfil(a.getPerfil());
			// Verifica se o perfil tem acesso. e marca true se sim.
			if(verificaAcesso(acesso)){
				acesso.setSimnao(true);
			}
			acessos.add(acesso);
		}
		return acessos;
	}
	
	
	// Metodo que verifica se existe acesso a um modulo para um determinado perfil.
	public boolean verificaAcesso(Acesso acesso) throws SQLException {
		boolean sucesso = false;
		banco.prepararRequisicao("SELECT A.id_acesso FROM cad_acesso AS A "+
				"INNER JOIN cad_perfil P on(A.id_perfil = P.id_perfil) "+
				"INNER JOIN cad_modulo M on(A.id_modulo = M.id_modulo) "+
				"WHERE P.perfil =? "+
				"AND M.modulo = ?");
		banco.preencherRequisicao(1, acesso.getPerfil());
		banco.preencherRequisicao(2, acesso.getModulo());
		List<Map<String, Object>> dados = banco.selecionar();
		if (dados.size() >= 1){
			return true;
		}
		return sucesso;
	}

	// Metodo que busca um acesso por id.
	public Acesso buscarAcesso(Integer id) throws SQLException {
		banco.prepararRequisicao("SELECT A.id_acesso, P.perfil, M.modulo FROM cad_acesso AS A "+
				"INNER JOIN cad_perfil P on(A.id_perfil = P.id_perfil) "+
				"INNER JOIN cad_modulo M on(A.id_modulo = M.id_modulo) "+
				"WHERE A.id_acesso = ? ");
				
		banco.preencherRequisicao(1, id);
		List<Map<String, Object>> dados = banco.selecionar();
		Acesso acessoModel = new Acesso();
		for (Map<String, Object> dado : dados) {
			acessoModel.setId(Integer.parseInt(dado.get("id_acesso").toString()));
			acessoModel.setPerfil(dado.get("perfil").toString());
			acessoModel.setModulo(dado.get("modulo").toString());
		}
		return acessoModel;
	}
		
	// Metodo que busca todos os acessos de um perfil com determinado nivel.
	public ArrayList<String> buscarAcessoNivel1(String perfil) throws SQLException  {
		ArrayList<String> modulos = new ArrayList<String>();
		banco.prepararRequisicao("SELECT B.modulo FROM cad_acesso AS A"+
				" INNER JOIN cad_modulo AS B on(A.id_modulo = B.id_modulo)"+
				" INNER JOIN cad_perfil AS C on(A.id_perfil = C.id_perfil)"+
				" INNER JOIN cad_categoria_modulo AS D on(B.id_categoria_modulo = D.id_categoria_modulo)"+
				" WHERE C.perfil=? AND D.nivel=?");
		banco.preencherRequisicao(1, perfil);
		banco.preencherRequisicao(2, 1);
		List<Map<String, Object>> dados = banco.selecionar();
		
		for (Map<String, Object> dado : dados) {
			modulos.add(dado.get("modulo").toString());
		}
		return modulos;
	}
	
// Metodo que valida se o usuário tem acesso ao modulo.
	public boolean TemAcesso(String informacao, String perfil) throws SQLException{
		boolean sucesso = false;
		Acesso acesso = new Acesso();
		// Preenche o objeto Acesso com os dados do PatchInfo e logon.
		acesso.setPerfil(perfil);// Perfil
		acesso.setModulo(informacao);// Modulo
		// Verifica se o perfil tem o modulo cadastrado.
		if (verificaAcesso(acesso)){
			sucesso = true;
		}
		return sucesso;
	}

// Metodo que valida o login de acesso atraves de senha.
	public Logon validaLoginAcesso(String usuario, String senha) throws SQLException{
		// Cria novo objeto logon.
		Logon logon = new Logon();
		banco.prepararRequisicao("SELECT A.id_usuario, A.usuario, A.nome, S.setor, B.perfil, C.id_empresa_sistema,"
				+ " C.nome_empresa FROM cad_usuario AS A"
				+ " INNER JOIN cad_setor AS S on(A.id_setor = S.id_setor)"
				+ " INNER JOIN cad_perfil AS B on(A.id_perfil = B.id_perfil)"
				+ " INNER JOIN cad_empresa_sistema AS C on(S.id_empresa_sistema = C.id_empresa_sistema)"
				+ " WHERE A.usuario=? AND A.senha=?");
		banco.preencherRequisicao(1, usuario);
		banco.preencherRequisicao(2, Criptografia.criptografar(senha));//captura senha e criptografa.
		// Recebe uma lista de objetos.
		List<Map<String, Object>> dados = banco.selecionar();
		if (dados.size() == 1) {
			// Se o objeto existir então armazena na variavel global.
			for (Map<String, Object> dado : dados) {
				logon.setId(Integer.parseInt(dado.get("id_usuario").toString()));
				logon.setUsuario( dado.get("usuario").toString().toUpperCase());
				logon.setNome(dado.get("nome").toString());
				logon.setPerfil(dado.get("perfil").toString());
				logon.setSetor(dado.get("setor").toString());
				logon.setCodEmpresa(dado.get("id_empresa_sistema").toString());
				logon.setNomeEmpresa(dado.get("nome_empresa").toString());
				logon.setAcessos(buscarAcessoNivel1(logon.getPerfil()));
			}
			logon.setLogado(true);
		}
		return logon;
	}
	
	// Metodo que exclui o acesso 
	public boolean excluir(Acesso acesso) throws SQLException  {
		banco.prepararRequisicao("DELETE FROM cad_acesso WHERE id_perfil=? AND id_modulo=?");
		banco.preencherRequisicao(1, acesso.getId_perfil());
		banco.preencherRequisicao(2, acesso.getId_modulo());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
}