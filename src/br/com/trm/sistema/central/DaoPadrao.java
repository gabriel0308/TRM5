package br.com.trm.sistema.central;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.trm.utilitarios.BancoDeDados;
/**
 * Classe de persistencia padrao
 */
public abstract class DaoPadrao {
	// Objeto BancoDeDados
	protected BancoDeDados banco = Transacao.getBanco();
	
	// Metodo preparar requisicao
	protected void preparar(String query) throws SQLException{
		banco.prepararRequisicao(query);
	}
	
	// Metodo preencherRequisicao
	protected void preencher(int paran, Object valor) throws SQLException{
		banco.preencherRequisicao(paran, valor);
	}
	
	// Modificar = update, delete, incluir
	protected boolean modificar() throws SQLException{
		return banco.modificar() == 1;
	}
	
	// Selecionar dados genericamente
	protected List<Map<String, Object>> selecionar() throws SQLException{
		return banco.selecionar();
	}
}