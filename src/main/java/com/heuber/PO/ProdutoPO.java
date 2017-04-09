package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.GrupoTO;
import com.heuber.TO.ProdutoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class ProdutoPO implements InterfacePO<ProdutoTO> {

	private ProdutoTO produtoTO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.heuber.interfacePO.InterfacePO#salvar()
	 */
	@Override
	public void salvar() throws Exception, SQLException {
		String sql = "INSERT INTO PRODUTO(DESCRICAO, ESTOQUE ,VALOR, COD_GRUPO, CALIBRE) VALUES(?,?,?,?,?)";

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				this.setStatement(statement);
				statement.execute();
			}
		}
	}

	@Override
	public void atualizar() throws Exception {
		String sql = "UPDATE PRODUTO SET DESCRICAO = ?, ESTOQUE = ?, VALOR =?,"
				+ " COD_GRUPO = ?, CALIBRE = ? WHERE COD_PRODUTO = ?";

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				this.setStatement(statement);
				statement.setLong(6, this.getProdutoTO().getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {
		String sql = "DELET FROM PRODUTO WHERE COD_PRODUTO = ?";

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setLong(1, this.produtoTO.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public List<ProdutoTO> select() throws Exception {
		List<ProdutoTO> listaProduto = new ArrayList<>();
		String sql = "SELECT * FROM PRODUTO WHERE 1=1";

		if (this.produtoTO.getCodigo() != 0) {
			sql += " AND COD_PRODUTO = " + this.produtoTO.getCodigo();
		}

		if (this.produtoTO.getDescricao() != null) {
			sql += " AND DESCRICAO = %" + this.produtoTO.getDescricao() + "%";
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						produtoTO = this.transferencia(resultSet);
						listaProduto.add(produtoTO);
					}
				}
			}
		}
		return listaProduto;
	}

	private void setStatement(PreparedStatement statement) throws SQLException {
		statement.setString(1, this.produtoTO.getDescricao());
		statement.setInt(2, this.produtoTO.getEstoque());
		statement.setDouble(3, this.produtoTO.getValor());
		statement.setLong(4, this.produtoTO.getGrupoTO().getCodigo());
		System.out.println(this.produtoTO.getGrupoTO().getCodigo());
		statement.setInt(5, this.produtoTO.getCalibe());
	}

	private ProdutoTO transferencia(ResultSet resultSet) throws SQLException {
		ProdutoTO produtoTO = new ProdutoTO();

		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setCodigo(resultSet.getLong("COD_GRUPO"));

		produtoTO.setCodigo(resultSet.getLong("COD_PRODUTO"));
		produtoTO.setDescricao(resultSet.getString("DESCRICAO"));
		produtoTO.setEstoque(resultSet.getInt("ESTOQUE"));
		produtoTO.setGrupoTO(grupoTO);
		produtoTO.setValor(resultSet.getDouble("VALOR"));
		produtoTO.setCalibe(resultSet.getInt("CALIBRE"));

		return produtoTO;
	}

	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}

	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}

	public static void main(String[] args) {
		GrupoTO grupoTO = new GrupoTO();
		grupoTO.setCodigo(1);
		ProdutoTO produtoTO = new ProdutoTO();

		produtoTO.setEstoque(30);
		produtoTO.setGrupoTO(grupoTO);
		produtoTO.setValor(150);
		produtoTO.setCalibe(35);
		produtoTO.setGrupoTO(grupoTO);
		produtoTO.setCodigo(11);

		ProdutoPO po = new ProdutoPO();
		po.setProdutoTO(produtoTO);
		try {
			po.atualizar();

			System.out.println("select");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
