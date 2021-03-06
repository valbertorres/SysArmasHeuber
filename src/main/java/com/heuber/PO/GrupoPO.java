package com.heuber.PO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heuber.TO.GrupoTO;
import com.heuber.fabricadeconexao.FabricaDeConexao;
import com.heuber.interfacePO.InterfacePO;

public class GrupoPO implements InterfacePO<GrupoTO> {

	private GrupoPO() {
	}
	
	private static GrupoPO instancia;

	private static synchronized GrupoPO getInstancia() {
		if (instancia == null)
			return instancia = new GrupoPO();
		return instancia;
	}

	private GrupoTO grupoTO;

	public void salvar() throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO GRUPO (GP_NOME,GP_MODELO) VALUES(?,?)");
		try (Connection conexao = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = conexao.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.execute();
			}
		}

	}

	@Override
	public void atualizar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE GRUPO SET GP_NOME = ?, GP_MODELO = ? WHERE COD_GRUPO = ?");

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				this.setStatement(statement);
				statement.setLong(3, this.grupoTO.getCodigo());
				statement.execute();
			}
		}
	}

	@Override
	public void deletar() throws Exception {

	}

	@Override
	public List<GrupoTO> select() throws ClassNotFoundException, SQLException {
		List<GrupoTO> listaProduto = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM GRUPO WHERE 1=1");

		if (this.grupoTO.getCodigo() != 0) {
			sql.append(" AND COD_GRUPO = " + this.grupoTO.getCodigo());
		}

		try (Connection connection = FabricaDeConexao.getInstancia().getConexao()) {
			try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						grupoTO = this.transferencia(resultSet);
						listaProduto.add(this.grupoTO);
					}
				}
			}
		}
		return listaProduto;
	}

	private GrupoTO transferencia(ResultSet resultSet) {
		GrupoTO grupoTO = new GrupoTO();
		try {
			grupoTO.setCodigo(resultSet.getLong("cod_grupo"));
			grupoTO.setModelo(resultSet.getString("gp_modelo"));
			grupoTO.setNome(resultSet.getString("gp_nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grupoTO;
	}

	private void setStatement(PreparedStatement statement) {
		try {
			statement.setString(1, this.grupoTO.getNome());
			statement.setString(2, this.grupoTO.getModelo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}

}
