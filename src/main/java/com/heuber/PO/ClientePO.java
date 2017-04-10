package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.CidadeTO;
import com.heuber.TO.ClienteTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class ClientePO implements InterfacePO<ClienteTO> {
	
	private ClientePO(){}
	
	private static ClientePO instancia;

	private static synchronized ClientePO getInstancia() {
		if (instancia == null)
			return instancia = new ClientePO();
		return instancia;
	}

	private ClienteTO cliente;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CLIENTE (CPF, NOME, TELEFONE, COD_CIDADE, ENDERECO) VALUES(?,?,?,?,?)");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatemnet(statement);
				statement.execute();
			}
		}

	}

	@Override
	public void atualizar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE CLIENTE SET CPF =? ,NOME =?, TELEFONE =?, COD_CIDADE =?, ENDERECO = ? WHERE COD_CLIENTE = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatemnet(statement);
				statement.setLong(6, this.cliente.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CLIENTE WHERE COD_CLIENTE = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.cliente.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public List<ClienteTO> select() throws Exception {
		List<ClienteTO> listaCliente = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CLIENTE WHERE 1=1 ");

		if (this.cliente.getCodigo() != 0) {
			sql.append(" AND COD_CLIENTE = " + this.cliente.getCodigo());
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.cliente = this.tranferencia(resultSet);
						listaCliente.add(cliente);
					}
				}
			}
		}
		return listaCliente;
	}

	private ClienteTO tranferencia(ResultSet resultSet) throws SQLException {
		CidadeTO cidadeTO = new CidadeTO();
		cidadeTO.setCodigo(resultSet.getLong("COD_CIDADE"));

		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setCodigo(resultSet.getLong("COD_CLIENTE"));
		clienteTO.setCpf(resultSet.getString("CPF"));
		clienteTO.setNome(resultSet.getString("TELEFONE"));
		clienteTO.setTelefone(resultSet.getString("TELEFONE"));
		clienteTO.setEndereco(resultSet.getString("ENDERECO"));
		clienteTO.setCidadeTO(cidadeTO);

		return clienteTO;
	}

	private void setStatemnet(PreparedStatement statement) {
		try {
			statement.setString(1, this.cliente.getCpf());
			statement.setString(2, this.cliente.getNome());
			statement.setString(3, this.cliente.getTelefone());
			statement.setLong(4, this.cliente.getCidadeTO().getCodigo());
			statement.setString(5, this.cliente.getEndereco());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}

}
