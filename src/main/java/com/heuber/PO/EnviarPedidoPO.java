package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.EnviarPedidoTO;
import com.heuber.TO.FornecedorTO;
import com.heuber.TO.PedidoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class EnviarPedidoPO implements InterfacePO<EnviarPedidoTO> {

	private EnviarPedidoPO() {
	}

	private static EnviarPedidoPO instancia;

	private static synchronized EnviarPedidoPO getInstancia() {
		if (instancia == null)
			return instancia = new EnviarPedidoPO();
		return instancia;
	}

	private EnviarPedidoTO enviarPedido;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ENVIAR_PEDIDO");
		sql.append(" (COD_PEDIDO,COD_FORNECEDOR) VALUES(?,?)");

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
		sql.append("UPDATE ENVIAR_PEDIDO SET COD_PEDIDO = ? , COD_FORNECEDOR = ? WHERE COD_ENVIA_PEDIDO = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(3, this.enviarPedido.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FORM ENVIAR_PEDIDO WHERE COD_EVIA_PEDIDO = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.enviarPedido.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public List<EnviarPedidoTO> select() throws Exception {
		List<EnviarPedidoTO> listaEnviaPedido = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ENVIAR_PEDIDO WHERE 1=1");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.enviarPedido = this.transferenca(resultSet);
						listaEnviaPedido.add(enviarPedido);
					}
				}
			}
		}
		return listaEnviaPedido;
	}

	private void setStatement(PreparedStatement statement) throws SQLException {
		statement.setLong(1, this.enviarPedido.getPedidoTO().getCodigo());
		statement.setLong(2, this.enviarPedido.getFornecedorTO().getCodigo());
	}

	private EnviarPedidoTO transferenca(ResultSet resultSet) throws SQLException {
		PedidoTO pedidoTO = new PedidoTO();
		pedidoTO.setCodigo(resultSet.getLong("COD_PEDIDO"));

		FornecedorTO fornecedorTO = new FornecedorTO();
		fornecedorTO.setCodigo(resultSet.getLong("COD_FORNECEDOR"));

		EnviarPedidoTO enviarPedidoTO = new EnviarPedidoTO();
		enviarPedidoTO.setCodigo(resultSet.getLong("COD_ENVIA_PEDIDO"));
		enviarPedidoTO.setPedidoTO(pedidoTO);
		enviarPedidoTO.setFornecedorTO(fornecedorTO);

		return enviarPedidoTO;
	}

	public EnviarPedidoTO getEnviarPedido() {
		return enviarPedido;
	}

	public void setEnviarPedido(EnviarPedidoTO enviarPedido) {
		this.enviarPedido = enviarPedido;
	}

}
