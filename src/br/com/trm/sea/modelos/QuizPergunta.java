package br.com.trm.sea.modelos;

import java.util.List;

public class QuizPergunta extends Pergunta{
	
	private List<Resposta> listaRespostas;

	public List<Resposta> getListaRespostas() {
		return listaRespostas;
	}
	public void setListaRespostas(List<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}
}
