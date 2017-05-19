package br.com.trm.sistema.modelos;

public class PojoPadrao {
	
	private Integer id;
	private String data_cria;
	private int ope_cria;
	private String data_edicao;
	private int ope_edicao;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData_cria() {
		return data_cria;
	}
	public void setData_cria(String data_cria) {
		this.data_cria = data_cria;
	}
	public String getData_edicao() {
		return data_edicao;
	}
	public void setData_edicao(String data_edicao) {
		this.data_edicao = data_edicao;
	}
	public int getOpe_cria() {
		return ope_cria;
	}
	public void setOpe_cria(int ope_cria) {
		this.ope_cria = ope_cria;
	}
	public int getOpe_edicao() {
		return ope_edicao;
	}
	public void setOpe_edicao(int ope_edicao) {
		this.ope_edicao = ope_edicao;
	}
}