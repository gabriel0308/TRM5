package br.com.trm.auditoria.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Perfil extends PojoPadrao
{
	private String perfil;
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}