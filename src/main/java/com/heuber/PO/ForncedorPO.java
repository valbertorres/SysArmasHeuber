package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.CidadeTO;
import com.heuber.TO.FornecedorTO;
import com.heuber.TO.PedidoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class ForncedorPO implements InterfacePO<FornecedorTO> {
	
	private ForncedorPO(){}
	
	private static ForncedorPO instancia;

	private static synchronized ForncedorPO getInstancia() {
		if (instancia == null)
			return instancia = new ForncedorPO();
		return instancia;
	}

	private FornecedorTO forncedor;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO FORNECEDOR(NOME_EMPRESA, CNPJ, F_RAZAO, COD_CIDADE,ENDERECO,TELEFONE)");
		sql.append(" VALUES(?,?,?,?,?,?)");

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
		sql.append(
				"UPDATE FORNECEDOR SET NOME_EMPRESA =?, CNPJ = ?, F_RAZAO =?, COD_CIDADE=?, ENDERECO=?, TELEFONE =? ");
		sql.append("WHERE COD_FORNECEDOR = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(7, this.forncedor.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM FORNECEDOR WHERE COD_FORNECEDOR = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.forncedor.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public List<FornecedorTO> select() throws Exception {
		List<FornecedorTO> listaFornecedor = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FORNECEDOR WHERE 1=1 ");

		if (this.forncedor.getCodigo() != 0) {
			sql.append(" AND COD_FORNECEDOR = " + this.forncedor.getCodigo());
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.forncedor = this.tranferencia(resultSet);
						listaFornecedor.add(forncedor);
					}
				}
			}
		}
		return listaFornecedor;
	}

	private void setStatement(PreparedStatement statement) throws SQLException {
		statement.setString(1, this.forncedor.getNome());
		statement.setString(2, this.forncedor.getCnpj());
		statement.setString(3, this.forncedor.getRazao());
		statement.setLong(4, this.forncedor.getCidadeTO().getCodigo());
		statement.setString(5, this.forncedor.getEdereco());
		statement.setString(6, this.forncedor.getTelefone());
	}

	private FornecedorTO tranferencia(ResultSet resultSet) throws SQLException {

		CidadeTO cidadeTO = new CidadeTO();
		cidadeTO.setCodigo(resultSet.getLong("COD_CIDADE"));

		FornecedorTO fornecedorTO = new FornecedorTO();

		fornecedorTO.setCodigo(resultSet.getLong("COD_FORNECEDOR"));
		fornecedorTO.setNome(resultSet.getString("NOME_EMPRESA"));
		fornecedorTO.setCnpj(resultSet.getString("CNPJ"));
		fornecedorTO.setRazao(resultSet.getString("F_RAZAO"));
		fornecedorTO.setEdereco(resultSet.getString("ENDERECO"));
		fornecedorTO.setTelefone(resultSet.getString("TELEFONE"));
		fornecedorTO.setCidadeTO(cidadeTO);

		return fornecedorTO;
	}

	public FornecedorTO getForncedor() {
		return forncedor;
	}

	public void setForncedor(FornecedorTO forncedor) {
		this.forncedor = forncedor;
	}

}
