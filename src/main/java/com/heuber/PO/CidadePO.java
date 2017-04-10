package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.CidadeTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class CidadePO implements InterfacePO<CidadeTO> {

	private CidadePO() {
	}

	private static CidadePO instancia;

	private static synchronized CidadePO getInstancia() {
		if (instancia == null)
			return instancia = new CidadePO();
		return instancia;
	}

	private CidadeTO cidade;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CIDADE (NOME_CIDADE) VALUES(?,?)");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.execute();
			}
		}
	}

	@Override
	public void atualizar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CIDADE SET NOME_CIDADE=?  WHERE COD_CIDADE =?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(2, this.cidade.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CIDADE WHERE COD_CIDADE = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.cidade.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public List<CidadeTO> select() throws Exception {
		List<CidadeTO> listaCidade = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CIDADE WHERE 1=1");

		if (this.cidade.getCodigo() != 0) {
			sql.append(" AND COD_CIDADE = " + this.cidade.getCodigo());
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.cidade = this.tranferencia(resultSet);
						listaCidade.add(cidade);
					}
				}
			}
		}
		return listaCidade;
	}

	private CidadeTO tranferencia(ResultSet resultSet) throws SQLException {
		CidadeTO cidadeTO = new CidadeTO();

		cidadeTO.setCodigo(resultSet.getLong("COD_CIDADE"));
		cidadeTO.setNome(resultSet.getString("NOME_CIDADE"));
		return cidadeTO;
	}

	private void setStatement(PreparedStatement statement) throws SQLException {
		statement.setString(1, this.cidade.getNome());
	}

	public CidadeTO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeTO cidade) {
		this.cidade = cidade;
	}

}
