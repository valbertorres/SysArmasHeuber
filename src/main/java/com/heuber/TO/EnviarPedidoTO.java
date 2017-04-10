package com.heuber.TO;

public class EnviarPedidoTO {

	private long codigo;
	private PedidoTO pedidoTO;
	private FornecedorTO fornecedorTO;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public PedidoTO getPedidoTO() {
		return pedidoTO;
	}

	public void setPedidoTO(PedidoTO pedidoTO) {
		this.pedidoTO = pedidoTO;
	}

	public FornecedorTO getFornecedorTO() {
		return fornecedorTO;
	}

	public void setFornecedorTO(FornecedorTO fornecedorTO) {
		this.fornecedorTO = fornecedorTO;
	}

}
