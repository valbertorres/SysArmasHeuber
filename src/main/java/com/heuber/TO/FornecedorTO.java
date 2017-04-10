package com.heuber.TO;

public class FornecedorTO {

	private long codigo;
	private String nome;
	private String cnpj;
	private String razao;
	private CidadeTO cidadeTO;
	private String edereco;
	private String telefone;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public CidadeTO getCidadeTO() {
		return cidadeTO;
	}

	public void setCidadeTO(CidadeTO cidadeTO) {
		this.cidadeTO = cidadeTO;
	}

	public String getEdereco() {
		return edereco;
	}

	public void setEdereco(String edereco) {
		this.edereco = edereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
