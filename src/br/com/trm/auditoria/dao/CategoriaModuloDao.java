package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.CategoriaModulo;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;

public class CategoriaModuloDao
{
	private BancoDeDados banco = Transacao.getBanco();

// Metodo que busca no banco categorias onde nivel = ?
	public List<CategoriaModulo> listar(int nivel) throws SQLException  {
		banco.prepararRequisicao("SELECT id_categoria_modulo, categoria FROM cad_categoria_modulo WHERE nivel=?");
		banco.preencherRequisicao(1, nivel);
		List<Map<String, Object>> dados = banco.selecionar();

		List<CategoriaModulo> categorias = new ArrayList<CategoriaModulo>();
		for (Map<String, Object> dado : dados) {
			CategoriaModulo categoria = new CategoriaModulo();
			categoria.setId(Integer.parseInt(dado.get("id_categoria_modulo").toString()));
			categoria.setCategoria(dado.get("categoria").toString());
			categorias.add(categoria);
		}
		return categorias;
	}

// Metodo que busca todas as categorias do banco.
	/*public List<Modulo> listar() throws SQLException  {
		banco.prepararRequisicao("SELECT id_categoria_modulo, categoria FROM cad_categoria_modulo");
		List<Map<String, Object>> dados = banco.selecionar();

		List<Modulo> modulos = new ArrayList<Modulo>();
		for (Map<String, Object> dado : dados) {
			Modulo modulo = new Modulo();
			modulo.setId((Integer) dado.get("id"));
			modulo.setCategoria((String) dado.get("categoria"));
			modulos.add(modulo);
		}
		return modulos;
	}*/
	
	/*public CategoriaModulo buscar(Integer id) throws SQLException {
		banco.prepararRequisicao("SELECT id_categoria_modulo, categoria FROM cad_categoria_modulo WHERE id_categoria_modulo=?");
		banco.preencherRequisicao(1, id);
		List<Map<String, Object>> dados = banco.selecionar();

		CategoriaModulo categoriaModulo = new CategoriaModulo();
		for (Map<String, Object> dado : dados) {
			categoriaModulo.setId((Integer) dado.get("id_categoria_modulo"));
			categoriaModulo.setCategoria((String) dado.get("categoria"));
		}
		return categoriaModulo;
	}*/
	
/*	public String buscarNivel(CategoriaModulo categoriaModulo) throws SQLException {
		banco.prepararRequisicao("SELECT nivel FROM cad_categoria_modulo WHERE categoria=? AND nivel=?");
		banco.preencherRequisicao(1, categoriaModulo.getCategoria());
		banco.preencherRequisicao(2, categoriaModulo.getNivel());
		List<Map<String, Object>> dados = banco.selecionar();
		String nivel = "0";
		for (Map<String, Object> dado : dados) {
			nivel = dado.get("nivel").toString();
		}
		return nivel;
	}*/ 

	public boolean incluir(CategoriaModulo categoriaModulo) throws SQLException {
		int linhasAfetadas = 0;
		banco.prepararRequisicao("INSERT INTO cad_categoria_modulo (categoria, nivel, data_cria) VALUES (?,?,NOW())");
		banco.preencherRequisicao(1, categoriaModulo.getCategoria());
		banco.preencherRequisicao(2, Integer.parseInt(categoriaModulo.getNivel()));
	
		if(categoriaModulo.getCategoria().trim() != null && !categoriaModulo.getCategoria().trim().isEmpty()){
			linhasAfetadas = banco.modificar();
		}

		if (linhasAfetadas == 1) return true;
		return false;
	}

// Deleta categorias de modulos
	public boolean excluir(CategoriaModulo categoriaModulo) throws SQLException  {
		banco.prepararRequisicao("DELETE FROM cad_categoria_modulo WHERE id_categoria_modulo=?");
		banco.preencherRequisicao(1, categoriaModulo.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
}