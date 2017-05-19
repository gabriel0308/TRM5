package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.Setor;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;

public class SetorDao
{
	private BancoDeDados banco = Transacao.getBanco();

// Metodo lista setores
	public List<Setor> listar() throws SQLException  {
		banco.prepararRequisicao("SELECT S.id_setor, S.setor, S.descricao, E.nome_empresa, E.id_empresa_sistema FROM cad_setor AS S "+
				"INNER JOIN cad_empresa_sistema E on(S.id_empresa_sistema = E.id_empresa_sistema)");
		List<Map<String, Object>> dados = banco.selecionar();

		List<Setor> setores = new ArrayList<Setor>();
		for (Map<String, Object> dado : dados) {
			Setor setor = new Setor();
			setor.setIdEmpresa(Integer.parseInt(dado.get("id_empresa_sistema").toString()));
			setor.setId(Integer.parseInt(dado.get("id_setor").toString()));
			setor.setSetor(dado.get("setor").toString());
			setor.setDescricao(dado.get("descricao").toString());
			setor.setEmpresa(dado.get("nome_empresa").toString());
			setores.add(setor);
		}
		return setores;
	}

	// Cadastra setores
	public boolean incluir(Setor setor) throws SQLException {
		int linhasAfetadas = 0;
		banco.prepararRequisicao("INSERT INTO cad_setor (setor, descricao, id_empresa_sistema, data_cria) VALUES (?,?,?,NOW())");
		banco.preencherRequisicao(1, setor.getSetor().trim().replace(" ", ""));
		banco.preencherRequisicao(2, setor.getDescricao().trim());
		banco.preencherRequisicao(3, setor.getIdEmpresa());
	
		if(setor.getSetor().trim() != null && !setor.getSetor().trim().isEmpty()){
			linhasAfetadas = banco.modificar();
		}

		if (linhasAfetadas == 1) return true;
		return false;
	}

	// Altera setor
	public boolean alterar(Setor setor) throws SQLException  {
		banco.prepararRequisicao("UPDATE cad_setor SET descricao = ?, id_empresa_sistema = ? WHERE id_setor =?");
		banco.preencherRequisicao(1, setor.getDescricao().trim());
		banco.preencherRequisicao(2, setor.getIdEmpresa());
		banco.preencherRequisicao(3, setor.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}	
// Desativa os setores.
	public boolean excluir(Setor setor) throws SQLException  {
		banco.prepararRequisicao("DELETE FROM cad_setor WHERE id_setor = ?");
		banco.preencherRequisicao(1, setor.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
}