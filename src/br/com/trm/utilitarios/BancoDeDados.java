package br.com.trm.utilitarios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.postgresql.Driver;

public class BancoDeDados
{
	private Connection conexao;
	private PreparedStatement requisicaoPreparada;
	private Driver tdrv;

	public void conectar() throws ClassNotFoundException, SQLException, IOException {
		tdrv = new org.postgresql.Driver();
        DriverManager.registerDriver(tdrv);  
		conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sea","sea","123");
	}

	public void desconectar() throws SQLException {
		DriverManager.deregisterDriver(tdrv);
		conexao.close();
	}

	public void prepararRequisicao(String requisicao) throws SQLException {
		requisicaoPreparada = conexao.prepareStatement(requisicao);
	}

	public void preencherRequisicao(int indice, Object valor) throws SQLException {
		requisicaoPreparada.setObject(indice, valor);
	}

	public void preencherRequisicao(Object ... valores) throws SQLException {
		for (int i = 1; i <= valores.length; i++) {
			requisicaoPreparada.setObject(i, valores[i]);
		}
	}

	public int modificar() throws SQLException {
		return requisicaoPreparada.executeUpdate();
	}

	public List<Map<String, Object>> selecionar() throws SQLException {
		List<Map<String, Object>> dados = new ArrayList<Map<String,Object>>();

		ResultSet resultado = requisicaoPreparada.executeQuery();
		while (resultado.next()) {
			Map<String, Object> dado = new HashMap<String, Object>();

			ResultSetMetaData metadado = resultado.getMetaData();
			for (int i = 1; i <= metadado.getColumnCount(); i++) {
				String colunaNome = metadado.getColumnName(i);
				Object colunaValor = resultado.getObject(colunaNome);
				dado.put(colunaNome, colunaValor);
			}
			dados.add(dado);
		}

		return dados;
	}
}