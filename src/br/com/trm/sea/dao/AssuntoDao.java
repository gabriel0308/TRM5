package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.Assunto;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class AssuntoDao extends DaoPadrao{
	// Grava dados da assunto no banco.
	public boolean incluirAssunto(Assunto assunto) throws SQLException{
		// Prepara requisicao
		preparar("INSERT INTO cad_assunto (assunto) VALUES (?)");
		// preenche requisição
		preencher(1, assunto.getAssunto().toUpperCase());
		// executa e retorna
		return modificar();
	}
	
	// Exclui assunto
	public boolean excluirAssunto(int id) throws SQLException{
		//preparar instrucao
		preparar("DELETE FROM cad_assunto WHERE id_assunto = ?");
		// preenche requisição
		preencher(1, id);
		// executa e retorna
		return modificar();
	}

	// Retorna Lista de assuntos.
	public ArrayList<Assunto> listarAssuntos() throws SQLException{
		ArrayList<Assunto> listaAssuntos = new ArrayList<Assunto>();
		//preparar instrucao
		preparar("SELECT id_assunto, assunto FROM cad_assunto");
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
			Assunto assunto = new Assunto();
			assunto.setId(Integer.parseInt(dado.get("id_assunto").toString()));
			assunto.setAssunto(dado.get("assunto").toString());
			//adiciona objeto assunto para lista de assuntos.
			listaAssuntos.add(assunto);
		}
		return listaAssuntos;
	}
}