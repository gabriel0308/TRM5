package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.Usuario;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;

public class UsuarioDao
{
	private BancoDeDados banco = Transacao.getBanco();

	public List<Usuario> listar() throws SQLException  {
		banco.prepararRequisicao("SELECT id, usuario, nome, senha, perfil FROM usuario");
		List<Map<String, Object>> dados = banco.selecionar();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Map<String, Object> dado : dados) {
			Usuario usuario = new Usuario();
			usuario.setId((Integer) dado.get("id"));
			usuario.setUsuario((String) dado.get("usuario"));
			usuario.setNome((String) dado.get("nome"));
			usuario.setSenha((String) dado.get("senha"));
			usuario.setPerfil((String) dado.get("perfil"));
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public List<Usuario> listarUsuarioSetor(String setor) throws SQLException  {
		banco.prepararRequisicao("SELECT usuario FROM usuario WHERE setor=?");
		banco.preencherRequisicao(1, setor);
		List<Map<String, Object>> dados = banco.selecionar();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Map<String, Object> dado : dados) {
			Usuario usuario = new Usuario();
			usuario.setUsuario((String) dado.get("usuario"));
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public Usuario buscar(Integer id) throws SQLException {
		banco.prepararRequisicao("SELECT id, usuario, nome, senha, perfil FROM usuario WHERE id=?");
		banco.preencherRequisicao(1, id);
		List<Map<String, Object>> dados = banco.selecionar();

		Usuario usuario = new Usuario();
		for (Map<String, Object> dado : dados) {
			usuario.setId((Integer) dado.get("id"));
			usuario.setUsuario((String) dado.get("usuario"));
			usuario.setNome((String) dado.get("nome"));
			usuario.setSenha((String) dado.get("senha"));
			usuario.setPerfil((String) dado.get("perfil"));
		}
		return usuario;
	}
	public boolean incluir(Usuario usuario) throws SQLException  {
		banco.prepararRequisicao("INSERT INTO usuario (usuario, nome, senha, perfil, criacao_data, criacao_ope, edicao_data, edicao_ope) VALUES (?, ?, ?, ?, NOW(), ?, NOW(), ?)");
		banco.preencherRequisicao(1, usuario.getUsuario().toUpperCase());
		banco.preencherRequisicao(2, usuario.getNome());
		banco.preencherRequisicao(3, usuario.getSenha());
		banco.preencherRequisicao(4, usuario.getPerfil());
		banco.preencherRequisicao(5, usuario.getOpe_edicao());
		banco.preencherRequisicao(6, usuario.getOpe_edicao());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}

	public boolean alterar(Usuario usuario) throws SQLException  {
		banco.prepararRequisicao("UPDATE usuario SET nome=?, senha=?, perfil=?, edicao_data=NOW(), edicao_ope=? WHERE id=?");
		banco.preencherRequisicao(1, usuario.getNome());
		banco.preencherRequisicao(2, usuario.getSenha());
		banco.preencherRequisicao(3, usuario.getPerfil());
		banco.preencherRequisicao(4, usuario.getOpe_edicao());
		banco.preencherRequisicao(5, usuario.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}

	public boolean excluir(Usuario usuario) throws SQLException  {
		banco.prepararRequisicao("DELETE FROM usuario WHERE id=?");
		banco.preencherRequisicao(1, usuario.getId());
		int linhasAfetadas = banco.modificar();

		if (linhasAfetadas == 1) return true;
		return false;
	}
}