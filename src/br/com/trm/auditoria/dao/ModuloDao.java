package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.Modulo;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;

public class ModuloDao
{
	private BancoDeDados banco = Transacao.getBanco();

// Metodo listar módulos
	public List<Modulo> listar(Modulo modulo) throws SQLException  {
		banco.prepararRequisicao("SELECT modulo, descricao FROM cad_modulo WHERE id_categoria_modulo = ?");
		banco.preencherRequisicao(1, modulo.getId_categoria());
		List<Map<String, Object>> dados = banco.selecionar();

		List<Modulo> modulos = new ArrayList<Modulo>();
		for (Map<String, Object> dado : dados) {
			Modulo mdulo = new Modulo();
			mdulo.setModulo(dado.get("modulo").toString());
			mdulo.setDescricao(dado.get("descricao").toString());
			modulos.add(mdulo);
		}
		return modulos;
	}
// Cadastro de modulos
	public boolean incluir(Modulo modulo) throws SQLException {
		int linhasAfetadas = 0;
		banco.prepararRequisicao("INSERT INTO cad_modulo (modulo, descricao, id_categoria_modulo, data_cria) VALUES (?,?,?,NOW())");
		banco.preencherRequisicao(1, modulo.getModulo());
		banco.preencherRequisicao(2, modulo.getDescricao());
		banco.preencherRequisicao(3, modulo.getId_categoria());
	
		if(modulo.getModulo().trim() != null && !modulo.getModulo().trim().isEmpty()){
			linhasAfetadas = banco.modificar();
		}

		if (linhasAfetadas == 1) return true;
		return false;
	}

// Deleta modulos
	public boolean excluir(Modulo modulo) throws SQLException  {
		banco.prepararRequisicao("DELETE FROM cad_modulo WHERE modulo = ?");
		banco.preencherRequisicao(1, modulo.getModulo());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
}