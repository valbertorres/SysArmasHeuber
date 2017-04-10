package com.heuber.PO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.heuber.TO.EntregaTO;
import com.heuber.TO.PedidoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class EntregaPO implements InterfacePO<EntregaTO> {

	private EntregaPO() {
	}

	private static EntregaPO instancia;

	private static synchronized EntregaPO getInstancia() {
		if (instancia == null)
			return instancia = new EntregaPO();
		return instancia;
	}

	private EntregaTO entrega;

	@Override
	public void salvar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ENTREGA");
		sql.append(" (ENDERECO,DATA,COD_PEDIDO) VALUES(?,?,?)");

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
		sql.append("UPDATE ENTREGA SET ");
		sql.append(" ENDERECO =?, DATA = ?, COD_PEDIDO=? WHERE COD_ENTREGA= ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(4, this.entrega.getCodigo());
				statement.execute();
			}
		}

	}

	@Override
	public void deletar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ENTREGA WHERE  COD_ENTREGA = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				statement.setLong(1, this.entrega.getCodigo());
				statement.execute();
			}
		}

	}

	@Override
	public List<EntregaTO> select() throws Exception {
		List<EntregaTO> listaEntrega = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM ENTREGA WHERE 1=1 ");
		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						this.entrega = this.transferencia(resultSet);
						listaEntrega.add(entrega);
					}
				}
			}
		}
		return listaEntrega;
	}

	private EntregaTO transferencia(ResultSet resultSet) throws SQLException {
		PedidoTO pedidoTO = new PedidoTO();
		pedidoTO.setCodigo(resultSet.getLong("COD_PEDIDO"));

		EntregaTO entregaTO = new EntregaTO();
		entregaTO.setData(resultSet.getDate("DATA").toLocalDate());
		entregaTO.setCodigo(resultSet.getLong("COD_ENTREGA"));
		entregaTO.setEndereco(resultSet.getString("ENDERECO"));
		return entregaTO;
	}

	private void setStatement(PreparedStatement statement) throws SQLException, ParseException {
		java.util.Date date = java.sql.Date.valueOf(this.entrega.getData());
		statement.setString(1, this.entrega.getEndereco());
		statement.setDate(2, new java.sql.Date(date.getTime()));
		statement.setLong(3, this.entrega.getPedidoTO().getCodigo());
	}

	public EntregaTO getEntrega() {
		return entrega;
	}

	public void setEntrega(EntregaTO entrega) {
		this.entrega = entrega;
	}

	public static void main(String[] args) throws Exception {
		PedidoTO pedidoTO = new PedidoTO();
		pedidoTO.setCodigo(1);

		EntregaTO entregaTO = new EntregaTO();
		entregaTO.setCodigo(4);
		entregaTO.setData(LocalDate.of(2017, 01, 05));
		entregaTO.setEndereco("teste");
		entregaTO.setPedidoTO(pedidoTO);

		EntregaPO entregaPO = new EntregaPO();
		entregaPO.setEntrega(entregaTO);
		entregaPO.atualizar();

		for (EntregaTO to : entregaPO.select()) {
			System.out.println(to.getData());
		}
	}

}
