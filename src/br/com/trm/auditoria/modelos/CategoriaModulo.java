package br.com.trm.auditoria.modelos;

import br.com.trm.sistema.modelos.PojoPadrao;

public class CategoriaModulo extends PojoPadrao{
		
	private String nivel;
	private String categoria;
	
	public String getNivel(){
		return nivel;
	}
	
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
