package br.com.trm.auditoria.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class EmpresaSistema extends PojoPadrao {
	private String nome;
	private String chave;
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
}
