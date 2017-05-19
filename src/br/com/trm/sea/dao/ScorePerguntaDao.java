package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.ScorePergunta;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class ScorePerguntaDao extends DaoPadrao{
	// Grava dados da scorePergunta no banco.
	public boolean incluirScorePergunta(ScorePergunta scorePergunta) throws SQLException{
		//1 preparar instruções
		preparar("INSERT INTO cad_score_pergunta (id_pergunta, id_resposta, id_usuario, flag_correta, data_cria) "
					+ "VALUES ( ?, ?, ?, ?, now())");
		//2 executar intrucao preparada
		preencher(1, scorePergunta.getId_pergunta());
		preencher(2, scorePergunta.getId_resposta());
		preencher(3, scorePergunta.getId_usuario());
		preencher(4, scorePergunta.isCorreta());
		// executar intrucao preparada
		return modificar();
	}
	
	// Retorna Lista de scoreScorePerguntas.
	public ArrayList<ScorePergunta> listarScorePerguntas() throws SQLException{
		ArrayList<ScorePergunta> listaScoreScorePerguntas = new ArrayList<ScorePergunta>();
		// 1 - consultar dados
		preparar("SELECT id_pergunta, count(id_resposta) AS QTDE, flag_correta AS CORRETA FROM CAD_SCORE_PERGUNTA GROUP BY id_pergunta, flag_correta");
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				ScorePergunta scorePergunta = new ScorePergunta();
				scorePergunta.setId_pergunta(Integer.parseInt(dado.get("id_pergunta").toString()));
				scorePergunta.setId_resposta(Integer.parseInt(("id_resposta").toString()));
				scorePergunta.setCorreta(Boolean.parseBoolean(dado.get("flag_correta").toString()));
				//adiciona objeto scorePergunta para lista de scoreScorePerguntas.
				listaScoreScorePerguntas.add(scorePergunta);
			}
		return listaScoreScorePerguntas;
	}
}