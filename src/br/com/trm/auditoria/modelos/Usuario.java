package br.com.trm.auditoria.modelos;

import java.util.ArrayList;

import br.com.trm.sistema.modelos.PojoPadrao;

public class Usuario extends PojoPadrao
{
	private String usuario;
	private String nome;
	private String senha;
	private String perfil;
	private String empresa;
	private String setor;

	private ArrayList<String> acessos = new ArrayList<String>();
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public ArrayList<String> getAcessos() {
		return acessos;
	}
	public void setAcessos(ArrayList<String> acessos) {
		this.acessos = acessos;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
}