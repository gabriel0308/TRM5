package br.com.trm.sea.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class ScoreQuestionario extends PojoPadrao{
	
	private int id_questionario;
	private String questionario;
	private int id_usuario;
	private String usuario;
	private int pontuacao;
	private int qtde_erro;
	private int qtde_acerto;
	
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public int getQtde_erro() {
		return qtde_erro;
	}
	public void setQtde_erro(int qtde_erro) {
		this.qtde_erro = qtde_erro;
	}
	public int getQtde_acerto() {
		return qtde_acerto;
	}
	public void setQtde_acerto(int qtde_acerto) {
		this.qtde_acerto = qtde_acerto;
	}
	public int getId_questionario() {
		return id_questionario;
	}
	public void setId_questionario(int id_questionario) {
		this.id_questionario = id_questionario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getQuestionario() {
		return questionario;
	}
	public void setQuestionario(String questionario) {
		this.questionario = questionario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
