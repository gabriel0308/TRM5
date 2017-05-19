package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.Resposta;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class RespostaDao extends DaoPadrao{
	// Grava dados da resposta no banco.
	public boolean incluirResposta(Resposta resposta) throws SQLException{
		//1 - preparar instruções.
		preparar("INSERT INTO cad_resposta (id_pergunta, resposta, flag_correta) " +
							"VALUES (?, ?, ?)");
		//2 - executar intrucao preparada
		preencher(1, resposta.getId_pergunta());
		preencher(2, resposta.getResposta());
		preencher(3, resposta.isCorreta());
		//3 - xecuta
		return modificar();
	}
	
	// Exclui resposta
	public boolean excluirResposta(int id) throws SQLException{
		// preparar instrucao
		preparar("DELETE FROM cad_resposta WHERE id_resposta = ?");
		// executar instrucoes preparadas
		preencher(1, id);
		// executar intrucao preparada
		return modificar();
	}

	// Retorna Lista de respostas por pergunta.
	public ArrayList<Resposta> listarRespostas(int id_pergunta) throws SQLException{
		ArrayList<Resposta> listaRespostas = new ArrayList<Resposta>();
		// 1 - prepara a query
		preparar("SELECT id_resposta, resposta, flag_correta FROM cad_resposta "
				+ "WHERE id_pergunta = ?");
		// 2 seta os parametros
		preencher(1, id_pergunta);
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				Resposta resposta = new Resposta();
				resposta.setId(Integer.parseInt(dado.get("id_resposta").toString()));
				resposta.setResposta(dado.get("resposta").toString());
				resposta.setCorreta(Boolean.parseBoolean(dado.get("flag_correta").toString()));
				//adiciona objeto resposta para lista de respostas.
				listaRespostas.add(resposta);
			}
		return listaRespostas;
	}

	// Retorna true se encontrar uma resposra correta para a pergunta indicada id
	public boolean isFlagCorreta(int id) throws SQLException{
		// 1 - prepara a query
		preparar("SELECT flag_correta FROM cad_resposta "
				+ "WHERE id_pergunta = ?");
		// 2 seta os parametros
		preencher(1, id);
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				if(Boolean.parseBoolean(dado.get("flag_correta").toString())){
					return true;
				}
			}
		return false;
	}	
}