package br.com.trm.sistema.central;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Acao
{
	private HttpServletRequest requisicao;
	private HttpServletResponse resposta;
	private int id_usuario;
	private String usuario;
	private String setor;
	private String codEmpresa;
	private String nomeEmpresa;
	protected HashMap<String, String> parametros;
	
	public Acao(HttpServletRequest requisicao, HttpServletResponse resposta){
		this.requisicao = requisicao;
		this.resposta = resposta;
		this.id_usuario = (int) requisicao.getSession(false).getAttribute("userId");
		this.usuario = requisicao.getSession(false).getAttribute("userName").toString();
		this.setor = requisicao.getSession(false).getAttribute("setor").toString();
		this.codEmpresa = requisicao.getSession(false).getAttribute("codEmpresa").toString();
		this.nomeEmpresa = requisicao.getSession(false).getAttribute("nomeEmpresa").toString();
		// Carrega e valida todos os parametros.
		carregaParametros();
	}
	
	// Seta form.
	public void setFormulario( String args){
		requisicao.setAttribute("form", args);
	}
	
	// Seta sistema.
	public void setSistema(String args){
		requisicao.getSession(false).setAttribute("sistema",args);
	}
	
	// Seta mensagem de sucesso
	public void setMensagemSucesso(String sucesso){
		requisicao.setAttribute("mgs_sucesso", sucesso);
	}
	
	// Seta mensagem de erro
	public void setMensagemErro(String erro, Exception e){
		if (e != null){
			e.printStackTrace();
			if(e.getMessage().contains("duplicate")){
				requisicao.setAttribute("erro", "Item já cadastrado!");
				return;
			}
			if(e.getMessage().contains("violates")){
				requisicao.setAttribute("erro", "Este item está sendo utilizado pelo sistema!");
				return;
			}
		}else{
			requisicao.setAttribute("erro", erro);
		}
	}
	
	// Metodo abstrato para capturar parametros da requisição.
	private void carregaParametros(){
		Enumeration<String> args = requisicao.getParameterNames();
		parametros = new HashMap<String, String>();
		while(args.hasMoreElements()) {
			String atributo = args.nextElement();
			String valor = getParameter(atributo);
			if(valor != null){
				parametros.put(atributo, valor);
			}else{
				parametros.put(atributo, "N/A");
			}
		}
	}
	
	// Metodo obrigatório para preencher objetos.
	protected abstract Object obterParametros();
	
	// Metodo que captura acao.
	public boolean verificaAcao(String args){
		// Pega parâmetro acao.
		String acao = requisicao.getParameter("acao");
		// Faz a validacao.
		if(acao != null && acao.equalsIgnoreCase(args)){
			return true;
		}
		return false;
	}
	public HttpServletRequest getRequisicao() {
		return requisicao;
	}
	public HttpServletResponse getResposta() {
		return resposta;
	}
	public String getParameter(String arg0){
		return requisicao.getParameter(arg0);
	}
	public void setAttribute(String arg0, Object arg1){
		this.requisicao.setAttribute(arg0, arg1);
	}
	public void setResposta(HttpServletResponse resposta) {
		this.resposta = resposta;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public String getUsuario() {
		if(usuario != null){
			return usuario;
		}else{
			return "N/A";
		}
	}
	public String getSetor() {
		if(setor != null){
			return setor;
		}else{
			return "N/A";
		}
	}
	public String getCodEmpresa() {
		if(codEmpresa != null){
			return codEmpresa;
		}else{
			return "N/A";
		}
	}
	public String getNomeEmpresa() {
		if(nomeEmpresa != null){
			return nomeEmpresa;
		}else{
			return "N/A";
		}
	}
}