package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.Pergunta;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class QuestionarioPerguntaDao extends DaoPadrao {
	// Grava dados no banco.
	public boolean incluirPergunta(int id_questionario, int id_pergunta) throws SQLException{
		//1 preparar instruções
		preparar("INSERT INTO cad_questionario_pergunta (id_questionario, id_pergunta, data_cria) "
				  + "VALUES (?, ?, now())");
		//2 preenche instruções
		preencher(1, id_questionario);
		preencher(2, id_pergunta);
		// executar intrucao
		return modificar();
	}
	
	// Exclui perguntas do questionario
	public boolean excluirPerunta(int id) throws SQLException{
		//preparar instrucao
		preparar("DELETE FROM cad_questionario_pergunta WHERE id_questionario_pergunta = ?");
		//preenche instruções
		preencher(1, id);
		// executa
		return modificar();
	}

	// Retorna Lista de perguntas.
	public ArrayList<Pergunta> listarPerguntas(int id_questionario) throws SQLException{
		ArrayList<Pergunta> listaPerguntas = new ArrayList<Pergunta>();
		// 1 - consultar dados
		preparar("SELECT QP.id_questionario_pergunta, P.pergunta, A.assunto, P.nivel FROM cad_questionario_pergunta AS QP "
				+"INNER JOIN cad_pergunta P on(QP.id_pergunta = P.id_pergunta) "
				+"INNER JOIN cad_assunto A on(P.id_assunto = A.id_assunto) "
				+"WHERE QP.id_questionario = ? ");
		//preenche instruções
		preencher(1, id_questionario);
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
				Pergunta pergunta = new Pergunta();
				pergunta.setId(Integer.parseInt(dado.get("id_questionario_pergunta").toString()));
				pergunta.setPergunta(dado.get("pergunta").toString());
				pergunta.setAssunto(dado.get("assunto").toString());
				pergunta.setNivel(Integer.parseInt(dado.get("nivel").toString()));
				//adiciona objeto questionario para lista de questionarios.
				listaPerguntas.add(pergunta);
		}
		return listaPerguntas;
	}

	// Retorna verdadeiro se tiver o maximo de assuntos.
	public int qtdePerguntaAssunto(int id_questionario, String assunto) throws SQLException{
		// 1 prepara instrução
		preparar("SELECT A.assunto FROM cad_questionario_pergunta AS QP "
				+"INNER JOIN cad_pergunta P on(QP.id_pergunta = P.id_pergunta) "
				+"INNER JOIN cad_assunto A on(P.id_assunto = A.id_assunto) "
				+"WHERE QP.id_questionario = ? AND A.assunto = ?");
		// 2 preenche instrução
		preencher(1, id_questionario);
		preencher(2, assunto);
		// 3 - executa e retorna quantidade
		return selecionar().size();
	}	
}