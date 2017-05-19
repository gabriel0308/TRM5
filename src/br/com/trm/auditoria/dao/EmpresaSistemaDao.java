package br.com.trm.auditoria.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.trm.auditoria.modelos.EmpresaSistema;
import br.com.trm.sistema.central.Transacao;
import br.com.trm.utilitarios.BancoDeDados;
 
public class EmpresaSistemaDao
{
	private BancoDeDados banco = Transacao.getBanco();
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	
	// Metodo que busca no banco empresas do sistema
	public List<EmpresaSistema> listar(int valor) throws SQLException  {
		banco.prepararRequisicao("SELECT id_empresa_sistema, nome_empresa, chave, data_cria FROM cad_empresa_sistema");
		List<Map<String, Object>> dados = banco.selecionar();

		List<EmpresaSistema> empresaSistemas = new ArrayList<EmpresaSistema>();
		for (Map<String, Object> dado : dados) {
			EmpresaSistema empresaSistema = new EmpresaSistema();
			empresaSistema.setId(Integer.parseInt(dado.get("id_empresa_sistema").toString()));
			empresaSistema.setNome(dado.get("nome_empresa").toString());
			// Lista a chame apenas se o valor for igual a 1
			if(valor == 1){
				empresaSistema.setChave(dado.get("chave").toString());
			}
			Timestamp timestamp = Timestamp.valueOf(dado.get("data_cria").toString());
			Date data = new Date(timestamp.getTime());
			empresaSistema.setData_cria(format.format(data));
			//empresaSistema.setData_cria(format.f);
			empresaSistemas.add(empresaSistema);
		}
		return empresaSistemas;
	}

	// Efetua a alteracao de chave da empresa do sistema
	public boolean alterar(EmpresaSistema empresaSistema) throws SQLException  {
		int linhasAfetadas = 0;
		banco.prepararRequisicao("UPDATE cad_empresa_sistema SET chave = ?, data_cria = NOW() WHERE id_empresa_sistema = ?");
		banco.preencherRequisicao(1, empresaSistema.getChave());
		banco.preencherRequisicao(2, empresaSistema.getId());
	
		if(empresaSistema.getChave().trim() != null && !empresaSistema.getChave().trim().isEmpty()){
			linhasAfetadas = banco.modificar();
		}

		if (linhasAfetadas == 1) return true;
		return false;
	}
}