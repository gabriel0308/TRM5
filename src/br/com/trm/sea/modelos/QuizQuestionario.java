package br.com.trm.sea.modelos;

import java.util.List;

public class QuizQuestionario extends Questionario{
	private List<QuizPergunta> listaPerguntas;

	public List<QuizPergunta> getListaPerguntas() {
		return listaPerguntas;
	}
	public void setListaPerguntas(List<QuizPergunta> listaPerguntas) {
		this.listaPerguntas = listaPerguntas;
	}
}
