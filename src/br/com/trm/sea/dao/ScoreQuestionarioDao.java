 package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.ScoreQuestionario;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class ScoreQuestionarioDao extends DaoPadrao{
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	// Grava dados da scoreQuestionario no banco.
	public boolean incluirScoreQuestionario(ScoreQuestionario scoreQuestionario) throws SQLException{
		//1 preparar instruções
		preparar("INSERT INTO cad_score_questionario (id_questionario, id_usuario, pontuacao, "
					+ "qtde_acerto, qtde_erro, data) VALUES ( ?, ?, ?, ?, ?, now())");
		//2 executar intrucao preparada
		preencher(1, scoreQuestionario.getId_questionario());
		preencher(2, scoreQuestionario.getId_usuario());
		preencher(3, scoreQuestionario.getPontuacao());
		preencher(4, scoreQuestionario.getQtde_acerto());
		preencher(5, scoreQuestionario.getQtde_erro());
		// executar intrucao preparada
		return modificar();
	}
	
	// Limpa toda a tabela
	public boolean limparScoreQuestionario() throws SQLException{
		//prepara instrução
		preparar("DELETE FROM cad_score_questionario");
		//executar instrucoes preparadas
		return modificar();
	}

	// Retorna Lista de scoreScoreQuestionarios.
	public ArrayList<ScoreQuestionario> listarScoreQuestionarios(boolean todos, String id) throws SQLException{
		ArrayList<ScoreQuestionario> listaScoreScoreQuestionarios = new ArrayList<ScoreQuestionario>();
		String where = "";
		if(!todos){where = "WHERE SQ.id_usuario = ? ";}
		// 1 - consultar dados
		preparar("SELECT SQ.id_score_questionario, Q.nome, U.usuario, SQ.pontuacao, SQ.qtde_acerto, SQ.qtde_erro, SQ.data "
				+ "FROM cad_score_questionario AS SQ "
				+ "INNER JOIN cad_questionario Q on(SQ.id_questionario = Q.id_questionario) "
				+ "INNER JOIN cad_usuario U on(SQ.id_usuario = U.id_usuario) "+where+"ORDER BY SQ.data DESC");
		// Seta parametros
		if(!todos){preencher(1, id);}
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				ScoreQuestionario sc = new ScoreQuestionario();
				sc.setId(Integer.parseInt(dado.get("id_score_questionario").toString()));
				sc.setQuestionario(dado.get("nome").toString());
				sc.setUsuario(dado.get("usuario").toString());
				sc.setPontuacao(Integer.parseInt(dado.get("pontuacao").toString()));
				sc.setQtde_acerto(Integer.parseInt(dado.get("qtde_acerto").toString()));
				sc.setQtde_erro(Integer.parseInt(dado.get("qtde_erro").toString()));
				long time = Long.parseLong(dado.get("data_cria").toString());
				sc.setData_cria(format.format(time));
				//adiciona objeto scoreQuestionario para lista de scoreScoreQuestionarios.
				listaScoreScoreQuestionarios.add(sc);
			}
		return listaScoreScoreQuestionarios;
	}
}