package com.heuber.TO;

public class ClienteTO {

	private long codigo;
	private String cpf;
	private String nome;
	private String telefone;
	private CidadeTO cidadeTO;
	private String endereco;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public CidadeTO getCidadeTO() {
		return cidadeTO;
	}

	public void setCidadeTO(CidadeTO cidadeTO) {
		this.cidadeTO = cidadeTO;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
