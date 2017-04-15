package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.CidadeTO;
import com.heuber.TO.ClienteTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class ClientePO implements InterfacePO<ClienteTO> {

	private ClientePO() {
	}

	private static ClientePO instancia;

	public static synchronized ClientePO getInstancia() {
		if (instancia == null)
			return instancia = new ClientePO();
		return instancia;
	}

	private ClienteTO cliente;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO CLIENTE (CPF, NOME, TELEFONE, COD_CIDADE, ENDERECO,EMAIL,CEP,BAIRRO,DATA_REGISTRO,DATA_NASCIMENTO) VALUES(?,?,?,?,?,?,?,?,?,?)");

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
		sql.append("UPDATE CLIENTE SET CPF=?, NOME=?, TELEFONE=?, COD_CIDADE=?, ENDERECO=?,EMAIL=?,"
				+ "CEP=?,BAIRRO=?,DATA_REGISTRO=?,DATA_NASCIMENTO=? WHERE COD_CLIENTE = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatemnet(statement);
				statement.setLong(11, this.cliente.getCodigo());
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
		clienteTO.setBairro(resultSet.getString("BAIRRO"));
		clienteTO.setDataRegistro(resultSet.getDate("DATA_REGISTRO").toLocalDate());
		clienteTO.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO").toLocalDate());
		clienteTO.setCidadeTO(cidadeTO);

		return clienteTO;
	}

	private void setStatemnet(PreparedStatement statement) {
		try {
			java.util.Date dateNas = java.sql.Date.valueOf(this.cliente.getDataNascimento());
			java.util.Date dateReg = java.sql.Date.valueOf(this.cliente.getDataRegistro());

			statement.setString(1, this.cliente.getCpf());
			statement.setString(2, this.cliente.getNome());
			statement.setString(3, this.cliente.getTelefone());
			statement.setLong(4, this.cliente.getCidadeTO().getCodigo());
			statement.setString(5, this.cliente.getEndereco());
			statement.setString(6, this.cliente.getEmail());
			statement.setString(7, this.cliente.getCep());
			statement.setString(8, this.cliente.getBairro());
			statement.setDate(9, new java.sql.Date(dateReg.getTime()));
			statement.setDate(10, new java.sql.Date(dateNas.getTime()));
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
	
	public static void main(String[] args) {
		CidadeTO cidadeTO = new CidadeTO();
		cidadeTO.setCodigo(1);
		
		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setCidadeTO(cidadeTO);
		clienteTO.setBairro("d");
		clienteTO.setCep("55");
		clienteTO.setCpf("54");
		clienteTO.setDataNascimento(LocalDate.of(2015, 2, 2));
		clienteTO.setDataRegistro(LocalDate.of(2015, 02, 02));
		clienteTO.setEmail("c");
		clienteTO.setEndereco("s");
		clienteTO.setNome("d");
		clienteTO.setNumero("dd");
		clienteTO.setRua("d");
		clienteTO.setSexo("m");
		clienteTO.setTelefone("554");
		
		ClientePO clientePO =ClientePO.getInstancia();
		clientePO.setCliente(clienteTO);
		try {
			clientePO.salvar();
			System.out.println("Salvo com sucesso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
