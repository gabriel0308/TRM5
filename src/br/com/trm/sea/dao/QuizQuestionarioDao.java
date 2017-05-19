package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.QuizPergunta;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 * 
 */
public class QuizQuestionarioDao extends DaoPadrao{
	private RespostaDao respostaController;
	// Retorna Lista de perguntas.
	public ArrayList<QuizPergunta> listarPerguntas(int id_questionario) throws SQLException {
		// Controler da tabela resposta.
		respostaController = new RespostaDao();
		ArrayList<QuizPergunta> listaPerguntas = new ArrayList<QuizPergunta>();
		// 1 - consultar dados
		preparar("SELECT QP.id_pergunta, P.pergunta FROM cad_questionario_pergunta AS QP "
						+ "INNER JOIN cad_pergunta P on(QP.id_pergunta = P.id_pergunta) "
						+ "WHERE QP.id_questionario = ? ");
		// executar instrucoes preparadas
		preencher(1, id_questionario);
		// 2 - executa consulta
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
			QuizPergunta pergunta = new QuizPergunta();
			pergunta.setId(Integer.parseInt(dado.get("id_pergunta").toString()));
			pergunta.setPergunta(dado.get("pergunta").toString());
			// adiciona objeto questionario para lista de questionarios.
			listaPerguntas.add(pergunta);
		}
		// Para cada pergunta seta suas respostas.
		for (QuizPergunta quizPergunta : listaPerguntas) {
			// Lista todas as resposta para uma pergunta.
			quizPergunta.setListaRespostas(respostaController.listarRespostas(quizPergunta.getId()));
		}
		return listaPerguntas;
	}
}