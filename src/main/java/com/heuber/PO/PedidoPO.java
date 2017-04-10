package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.PedidoTO;
import com.heuber.TO.ProdutoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class PedidoPO implements InterfacePO<PedidoTO> {

	private PedidoPO() {
	}
	
	private static PedidoPO instancia;

	private static synchronized PedidoPO getInstancia() {
		if (instancia == null)
			return instancia = new PedidoPO();
		return instancia;
	}

	private PedidoTO pedido;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PEDIDO (COD_PRODUTO, VALOR_TOTAL, QUANTIDADE) VALUES(?,?,?)");

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
		sql.append("UPDATE PEDIDO SET COD_PRODUTO = ?, VALOR_TOTAL = ?, QUANTIDADE = ? WHERE COD_PEDIDO = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(4, this.pedido.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELET FROM PEDIDO WHERE COD_PEDIDO = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.pedido.getCodigo());
				statement.execute();
			}
		}

	}

	@Override
	public List<PedidoTO> select() throws Exception {
		List<PedidoTO> listaPedidos = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PEDIDO WHERE 1=1");

		if (this.pedido.getCodigo() != 0) {
			sql.append(" AND COD_PEDIDO = " + this.pedido.getCodigo());
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.pedido = this.transferencia(resultSet);
						listaPedidos.add(pedido);
					}
				}
			}
		}
		return listaPedidos;
	}

	private PedidoTO transferencia(ResultSet resultSet) throws SQLException {

		ProdutoTO produtoTO = new ProdutoTO();
		produtoTO.setCodigo(resultSet.getLong("COD_PRODUTO"));

		PedidoTO pedidoTO = new PedidoTO();
		pedidoTO.setCodigo(resultSet.getLong("COD_PEDIDO"));
		pedidoTO.setQuantidade(resultSet.getInt("QUANTIDADE"));
		pedidoTO.setValoPedido(resultSet.getDouble("VALOR_TOTAL"));
		pedidoTO.setProdutoTO(produtoTO);

		return pedidoTO;
	}

	private void setStatement(PreparedStatement statement) {
		try {
			statement.setLong(1, this.pedido.getProdutoTO().getCodigo());
			statement.setDouble(2, this.pedido.getValoPedido());
			statement.setInt(3, this.pedido.getQuantidade());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public PedidoTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoTO pedido) {
		this.pedido = pedido;
	}

	public static void main(String[] args) {

		PedidoTO pedidoTO = new PedidoTO();
		PedidoPO pedidoPO = new PedidoPO();
		pedidoPO.setPedido(pedidoTO);
		try {
			for (PedidoTO TO : pedidoPO.select()) {
				System.out.println(TO.getQuantidade());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
