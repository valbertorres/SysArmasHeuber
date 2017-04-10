package com.heuber.TO;

import java.time.LocalDate;

public class EntregaTO {

	private long codigo;
	private String endereco;
	private LocalDate data;
	private PedidoTO pedidoTO;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public PedidoTO getPedidoTO() {
		return pedidoTO;
	}

	public void setPedidoTO(PedidoTO pedidoTO) {
		this.pedidoTO = pedidoTO;
	}

}
