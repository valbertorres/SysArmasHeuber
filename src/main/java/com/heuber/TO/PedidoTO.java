package com.heuber.TO;

public class PedidoTO {

	private long codigo;
	private int quantidade;
	private double valoPedido;
	private ProdutoTO produtoTO;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValoPedido() {
		return valoPedido;
	}

	public void setValoPedido(double valoPedido) {
		this.valoPedido = valoPedido;
	}

	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}

	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}

}
