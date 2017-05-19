package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.Perfil;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;

public class PerfilDao
{
	private BancoDeDados banco = Transacao.getBanco();

	// Listar os perfis
	public List<Perfil> listar() throws SQLException  {
		banco.prepararRequisicao("SELECT id_perfil, perfil, descricao FROM cad_perfil");
		List<Map<String, Object>> dados = banco.selecionar();

		List<Perfil> perfis = new ArrayList<Perfil>();
		for (Map<String, Object> dado : dados) {
			Perfil perfil = new Perfil();
			perfil.setId(Integer.parseInt(dado.get("id_perfil").toString()));
			perfil.setPerfil( dado.get("perfil").toString());
			perfil.setDescricao(dado.get("descricao").toString());
			perfis.add(perfil);
		}
		return perfis;
	}

	// Busca perfil pelo id
	public Perfil buscar(Integer id) throws SQLException {
		banco.prepararRequisicao("SELECT id_perfil, perfil, descricao FROM cad_perfil WHERE id_perfil=?");
		banco.preencherRequisicao(1, id);
		List<Map<String, Object>> dados = banco.selecionar();

		Perfil perfil = new Perfil();
		for (Map<String, Object> dado : dados) {
			perfil.setId(Integer.parseInt(dado.get("id_perfil").toString()));
			perfil.setPerfil( dado.get("perfil").toString());
			perfil.setDescricao( dado.get("descricao").toString());
		}
		return perfil;
	}

//  Cadastra novo perfil
	public boolean incluir(Perfil perfil) throws SQLException  {
		banco.prepararRequisicao("INSERT INTO cad_perfil (perfil, descricao, data_cria) VALUES (?, ?, NOW())");
		banco.preencherRequisicao(1, perfil.getPerfil().toUpperCase());
		banco.preencherRequisicao(2, perfil.getDescricao());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
	
	// Altera perfil
	public boolean alterar(Perfil perfil) throws SQLException  {
		banco.prepararRequisicao("UPDATE cad_perfil SET descricao = ? WHERE id_perfil = ?");
		banco.preencherRequisicao(1, perfil.getDescricao().trim());
		banco.preencherRequisicao(2, perfil.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
	
// Metodo que excluir os acessos do perfil e em seguida o perfil.
	public boolean excluir(Perfil perfil) throws SQLException  {
		// Codigo que exclui todos os acessos de um perfil.
		banco.prepararRequisicao("DELETE FROM cad_acesso WHERE id_perfil=?");
		banco.preencherRequisicao(1, perfil.getId());
		banco.modificar();
		// Verifica se existe registro de acessos do perfil.
		banco.prepararRequisicao("SELECT id_perfil FROM cad_acesso WHERE id_perfil=?");
		banco.preencherRequisicao(1, perfil.getId());
		List<Map<String, Object>> dados = banco.selecionar();
		// Testa se existe acessos para o perfil selecionado.
		if (dados.isEmpty() || dados.size() < 1){
			// Por ultimo exclui o perfil se nao existir acessos para esse perfil.
			banco.prepararRequisicao("DELETE FROM cad_perfil WHERE id_perfil=?");
			banco.preencherRequisicao(1, perfil.getId());
			if(banco.modificar() == 1){
				return true;
			}
		}
		return false;
	}
}