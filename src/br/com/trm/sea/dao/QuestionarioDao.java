package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.Questionario;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class QuestionarioDao extends DaoPadrao {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	// Grava dados da questionario no banco.
	public boolean incluirQuestionario(Questionario questionario) throws SQLException{
		//1 preparar instruções
		preparar("INSERT INTO cad_questionario (nome, tempo_min, tempo_max, max_pergunta, "
					+ "max_pergunta_assunto, data_cria) VALUES (?, ?, ?, ?, ?, now())");
		//2 preenche instrucao
		preencher(1, questionario.getNome().toUpperCase());
		preencher(2, questionario.getTempoMin());
		preencher(3, questionario.getTempoMax());
		preencher(4, Integer.parseInt(questionario.getPerguntaMax()));
		preencher(5, Integer.parseInt(questionario.getPerguntaMaxAssunto()));
		// executa instrucao
		return modificar();
	}
	
	// Exclui questionário
	public boolean excluirQuestionario(int id) throws SQLException{
		//preparar instrucao
		preparar("DELETE FROM cad_questionario WHERE id_questionario = ?");
		// Preenche instruções
		preencher(1, id);
		//executar instrucoes
		return modificar();
	}

	// Retorna Lista de questionarios.
	public ArrayList<Questionario> listarQuestionarios() throws SQLException{
		ArrayList<Questionario> listaQuestionarios = new ArrayList<Questionario>();
		// 1 - consultar dados
		preparar("SELECT id_questionario, nome, tempo_min, tempo_max, max_pergunta, "
				+ "max_pergunta_assunto, data_cria FROM cad_questionario");
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				Questionario questionario = new Questionario();
				questionario.setId(Integer.parseInt(dado.get("id_questionario").toString()));
				questionario.setNome(dado.get("nome").toString());
				questionario.setTempoMin(dado.get("tempo_min").toString());
				questionario.setTempoMax(dado.get("tempo_max").toString());
				questionario.setPerguntaMax(dado.get("max_pergunta").toString());
				questionario.setPerguntaMaxAssunto(dado.get("max_pergunta_assunto").toString());
				long time = Long.parseLong(dado.get("data_cria").toString());
				questionario.setData_cria(format.format(time));
				//adiciona objeto questionario para lista de questionarios.
				listaQuestionarios.add(questionario);
		}
		return listaQuestionarios;
	}
}