package com.heuber.BO;

import java.nio.channels.ClosedByInterruptException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;

import com.heuber.PO.ClientePO;
import com.heuber.TO.CidadeTO;
import com.heuber.TO.ClienteTO;
import com.heuber.util.UtilCpf;
import com.heuber.util.UtilData;
import com.heuber.util.UtilEmail;
import com.heuber.util.UtilVariados;
import com.heuber.view.CadastroClienteView;

public class CadastroClienteBO {

	private static CadastroClienteBO instancia;

	private CadastroClienteView cadastroClienteView;

	public static synchronized CadastroClienteBO getInstancia() {
		if (instancia == null)
			return instancia = new CadastroClienteBO();
		return instancia;
	}

	private void validarCampos() throws Exception {
		String nome = this.cadastroClienteView.getTxtNome().getText();
		String dataNascimento = this.cadastroClienteView.getTxtDataNasc().getText();
		String sexo = (String) this.cadastroClienteView.getComboBoxSexo().getSelectedItem();
		String cpf = this.cadastroClienteView.getTxtCpf().getText();
		String email = this.cadastroClienteView.getTxtEmail().getText();
		String telefone = this.cadastroClienteView.getTxtTelefone().getText();
		String dataRegistro = this.cadastroClienteView.getTxtDataRegistro().getText();
		String cep = this.cadastroClienteView.getTextCep().getText();
		String numero = this.cadastroClienteView.getTextNumero().getText();
		String rua = this.cadastroClienteView.getTxtRua().getText();
		String setor = this.cadastroClienteView.getTxtSetor().getText();
		CidadeTO cidade = (CidadeTO) this.cadastroClienteView.getComboBoxCidade().getSelectedItem();
		String bairro = this.cadastroClienteView.getTextfBairro().getText();

		if (nome.isEmpty()) {
			throw new Exception("Informe o nome !");
		}

		if (dataNascimento.isEmpty()) {
			throw new Exception("Informe a data de nascimento !");
		}

		if (!UtilData.validatData(dataNascimento)) {
			throw new Exception(
					"Infome data " + this.cadastroClienteView.getTxtDataNasc().getText() + " nascimento invalidar !	");
		}

		if (sexo.equals("Selecione")) {
			throw new Exception("Informe o sexo !");
		}

		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if (!UtilCpf.isCPF(cpf)) {
			throw new Exception("Infome cpf invalido!");
		}

		if (!UtilEmail.isEmailValid(email)) {
			throw new Exception("Email invalido!");
		}

		if (telefone.isEmpty()) {
			throw new Exception("Informe o telefone!");
		}
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		if (!UtilVariados.eNumerico(telefone)) {
			throw new Exception("Informe o telefone valido!");
		}

		if (dataRegistro.isEmpty()) {
			throw new Exception("Informe a data registro!");
		}

		if (cep.isEmpty()) {
			throw new Exception("Informe o cep!");
		}

		if (numero.isEmpty()) {
			throw new Exception("Informe o número!");
		}

		if (rua.isEmpty()) {
			throw new Exception("Informe a rua!");
		}

		if (setor.isEmpty()) {
			throw new Exception("Informe o setor !");
		}

		if (cidade.getNome().equals("Selecione")) {
			throw new Exception("Informe a ciade!");
		}

		if (bairro.isEmpty()) {
			throw new Exception("Informe o bairro!");
		}

		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setNome(nome);
		int diaN = Integer.parseInt(dataNascimento.substring(0, 2));
		int mesN = Integer.parseInt(dataNascimento.substring(3, 5));
		int anoN = Integer.parseInt(dataNascimento.substring(6, 10));
		clienteTO.setDataNascimento(LocalDate.of(anoN, mesN, diaN));

		clienteTO.setSexo(sexo);
		clienteTO.setCpf(cpf);
		clienteTO.setEmail(email);
		clienteTO.setTelefone(telefone);

		int diaR = Integer.parseInt(dataNascimento.substring(0, 2));
		int mesR = Integer.parseInt(dataNascimento.substring(3, 5));
		int anoR = Integer.parseInt(dataNascimento.substring(6, 10));
		clienteTO.setDataRegistro(LocalDate.of(anoR, mesR, diaR));

		clienteTO.setCep(cep);
		clienteTO.setNumero(numero);
		clienteTO.setRua(rua);
		clienteTO.setEndereco(setor);

		CidadeTO cidadeTO = (CidadeTO) this.cadastroClienteView.getComboBoxCidade().getSelectedItem();
		clienteTO.setCidadeTO(cidadeTO);
		clienteTO.setBairro(bairro);

		ClientePO clientePO = ClientePO.getInstancia();
		clientePO.setCliente(clienteTO);
		clientePO.salvar();

	}

	public void teste() {
		ClienteTO clienteTO = new ClienteTO();
		CidadeTO cidadeTO = (CidadeTO) this.cadastroClienteView.getComboBoxCidade().getSelectedItem();
		clienteTO.setCidadeTO(cidadeTO);
		System.out.println("cidade " + cidadeTO.getCodigo());
	}

	public void validarCpf() throws Exception {
		String cpf = this.cadastroClienteView.getTxtCpf().getText();

		if (!UtilVariados.eNumerico(cpf)) {
			throw new Exception("Infome cpf somente numéricos!	");
		}

	}

	public void validarTelefone() throws Exception {
		String telefone = this.cadastroClienteView.getTxtTelefone().getText();

		if (!UtilVariados.eNumerico(telefone)) {
			throw new Exception("Infome telefone somente numéricos!	");
		}

		if (telefone.length() > 20) {
			throw new Exception("Infome telefone valido!");
		}

	}

	public void validarCep() throws Exception {
		String cep = this.cadastroClienteView.getTextCep().getText();

		if (!UtilVariados.eNumerico(cep)) {
			throw new Exception("Infome cep somente numéricos!	");
		}

		if (cep.length() > 20) {
			throw new Exception("Infome cep valido!");
		}

	}

	public void validarNumero() throws Exception {
		String numero = this.cadastroClienteView.getTextNumero().getText();

		if (!UtilVariados.eNumerico(numero)) {
			throw new Exception("Infome número somente numéricos!	");
		}

		if (numero.length() > 4) {
			throw new Exception("Infome número valido!");
		}

	}

	public void salvar() throws Exception {
		this.validarCampos();
	}

	public CadastroClienteView getCadastroClienteView() {
		return cadastroClienteView;
	}

	public void setCadastroClienteView(CadastroClienteView cadastroClienteView) {
		this.cadastroClienteView = cadastroClienteView;
	}

}
