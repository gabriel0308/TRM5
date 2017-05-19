package br.com.trm.sea.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.trm.sea.modelos.Pergunta;
import br.com.trm.sistema.central.DaoPadrao;
/**
 * Classe de persistencia de dados
 */
public class PerguntaDao extends DaoPadrao{
	// Grava dados da pergunta no banco.
	public boolean incluirPergunta(Pergunta pergunta) throws SQLException{
		//1 preparar instruções
		preparar("INSERT INTO cad_pergunta (id_assunto, pergunta, nivel) VALUES (?, ?, ?)");
		//2 preencher instrução
		preencher(1, pergunta.getId_assunto());
		preencher(2, pergunta.getPergunta());
		preencher(3, pergunta.getNivel());
		// executar intrucao
		return modificar();
	}
	
	// Exclui pergunta
	public boolean excluirPergunta(int id) throws SQLException{
		//preparar instrucao
		preparar("DELETE FROM cad_pergunta WHERE id_pergunta = ?");
		//preenche instrucoes
		preencher(1, id);
		// executar intrucao
		return modificar();
	}

	// Retorna Lista de perguntas.
	public ArrayList<Pergunta> listarPerguntas() throws SQLException{
		ArrayList<Pergunta> listaPerguntas = new ArrayList<Pergunta>();
		// 1 - peparar instrução
		preparar("SELECT P.id_pergunta, P.pergunta, P.nivel, A.assunto FROM cad_pergunta AS P "
				+ "INNER JOIN cad_assunto A on (P.id_assunto = A.id_assunto)");
		// processar resultado
		for (Map<String, Object> dado : selecionar()) {
			Pergunta pergunta = new Pergunta();
			pergunta.setId(Integer.parseInt(dado.get("id_pergunta").toString()));
			pergunta.setPergunta(dado.get("pergunta").toString());
			pergunta.setNivel(Integer.parseInt(dado.get("nivel").toString()));
			pergunta.setAssunto(dado.get("assunto").toString());
			//adiciona objeto pergunta para lista de perguntas.
			listaPerguntas.add(pergunta);
		}
		return listaPerguntas;
	}
}