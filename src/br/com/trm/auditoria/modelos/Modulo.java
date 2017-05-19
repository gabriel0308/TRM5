package br.com.trm.auditoria.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Modulo extends PojoPadrao
{
	private String modulo;
	private int id_categoria;
	private String descricao;
	
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}