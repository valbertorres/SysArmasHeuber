package com.heuber.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import com.heuber.BO.CadastroClienteBO;
import com.heuber.PO.CidadePO;
import com.heuber.TO.CidadeTO;
import com.heuber.util.UtilMensagem;
import com.heuber.view.CadastroClienteView;
import com.mchange.v2.codegen.bean.PropertyMapConstructorGeneratorExtension;

public class CadastroClienteCtrl {
	private static CadastroClienteCtrl instancia;
	private CadastroClienteView cadastroClienteView;

	public static synchronized CadastroClienteCtrl getInstancia() {
		if (instancia == null)
			return instancia = new CadastroClienteCtrl();
		return instancia;
	}

	private JTextField txtNome;
	private JTextField txtDataNasc;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtDataRegistro;
	private JTextField txtRua;
	private JTextField txtSetor;
	private JComboBox comboBoxCidade;
	private JTextField textCep;
	private JTextField textNumero;
	private JButton btnDeletar;
	private JComboBox comboBoxSexo;
	private JButton btnCancelar;
	private JButton btnSalvar;
	CadastroClienteBO bo;
	private JTextField textfBairro;

	public void inicializar() {
		this.inicializarComponente();
		this.inicializarListen();
		this.inicializarComboCidade();
	}

	public void inicializarComponente() {
		this.bo = CadastroClienteBO.getInstancia();
		bo.setCadastroClienteView(cadastroClienteView);
		this.txtNome = this.cadastroClienteView.getTxtNome();
		this.txtDataNasc = this.cadastroClienteView.getTxtDataNasc();
		this.txtRua = this.cadastroClienteView.getTxtRua();
		this.btnSalvar = this.cadastroClienteView.getBtnSalvar();
		this.txtCpf = this.cadastroClienteView.getTxtCpf();
		this.txtEmail = this.cadastroClienteView.getTxtEmail();
		this.txtTelefone = this.cadastroClienteView.getTxtTelefone();
		this.textCep = this.cadastroClienteView.getTextCep();
		this.textNumero = this.cadastroClienteView.getTextNumero();
		this.txtDataRegistro = this.cadastroClienteView.getTxtDataRegistro();
		this.txtSetor = this.cadastroClienteView.getTxtSetor();
		this.textfBairro = this.cadastroClienteView.getTextfBairro();
		this.comboBoxCidade = this.cadastroClienteView.getComboBoxCidade();
		this.mascaras();
	}

	private void mascaras() {
		this.mascaraNascimento();
		this.mascaraTelefone();
		this.mascaraCpf();
		this.iniciaDataRegistro();
	}

	private void iniciaDataRegistro() {
		LocalDate data = LocalDate.now();
		this.txtDataRegistro.setText((String) data.toString());
		this.txtDataRegistro.setEditable(false);

	}

	private void inicializarComboCidade() {
		try {
			DefaultComboBoxModel model = (DefaultComboBoxModel) this.comboBoxCidade.getModel();

			CidadePO cidadePO = CidadePO.getInstancia();
			CidadeTO cidadeTO = new CidadeTO();
			cidadePO.setCidade(cidadeTO);
			List<CidadeTO> lista = cidadePO.select();
			cidadeTO.setNome("Selecione");
			lista.add(0, cidadeTO);
			for (CidadeTO to : lista) {
				model.addElement(to);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mascaraCpf() {
		try {

			MaskFormatter mascara = new MaskFormatter("###.###.###-##");
			mascara.setPlaceholderCharacter('_');

			((JFormattedTextField) txtCpf).setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascara));

		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

	}

	private void mascaraTelefone() {
		try {

			MaskFormatter mascara = new MaskFormatter("(##)####-####");
			mascara.setPlaceholderCharacter('_');

			((JFormattedTextField) txtTelefone)
					.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascara));

		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

	}

	private void mascaraNascimento() {
		try {

			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');

			((JFormattedTextField) txtDataNasc)
					.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascara));

		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

	}

	private void inicializarListen() {
		this.btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bo = CadastroClienteBO.getInstancia();
				try {
					bo.salvar();
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
				} catch (Exception e) {
					validaMensagemCampos(e.getMessage());
				}
			}
		});

		this.textCep.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				this.validarCampoCep();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			private void validarCampoCep() {
				bo = CadastroClienteBO.getInstancia();
				try {
					bo.validarCep();
				} catch (Exception e) {
					validarCep(e.getMessage());
				}
			}
		});

		this.textNumero.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				this.validarCampoNumero();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void validarCampoNumero() {
				bo = CadastroClienteBO.getInstancia();
				try {
					bo.validarNumero();
				} catch (Exception e) {
					validarNumero(e.getMessage());
				}
			}
		});

	}

	private void validaMensagemCampos(String strMsg) {
		int resposta = UtilMensagem.message(strMsg);

		if (resposta == 0) {
			if (strMsg.contains("nome")) {
				this.txtNome.setText("");
				this.txtNome.requestFocus();
			}

			if (strMsg.contains("nascimento")) {
				this.txtDataNasc.setText("");
				this.txtDataNasc.requestFocus();
			}

			if (strMsg.contains("cpf")) {
				this.txtCpf.setText("");
				this.txtCpf.requestFocus();
			}

			if (strMsg.contains("Email")) {
				this.txtEmail.setText("");
				this.txtEmail.requestFocus();
			}

			if (strMsg.contains("telefone")) {
				this.txtTelefone.setText("");
				this.txtTelefone.requestFocus();
			}

			if (strMsg.contains("registro")) {
				txtDataRegistro.setText(null);
				txtDataRegistro.requestFocus();
			}

			if (strMsg.contains("cep")) {
				textCep.setText(null);
				textCep.requestFocus();
			}

			if (strMsg.contains("número")) {
				this.textNumero.setText("");
				this.textNumero.requestFocus();
			}

			if (strMsg.contains("rua")) {
				this.txtRua.setText("");
				this.txtRua.requestFocus();
			}

			if (strMsg.contains("bairro")) {
				this.textfBairro.setText("");
				this.textfBairro.requestFocus();
			}

			if (strMsg.contains("setor")) {
				this.txtSetor.setText("");
				this.txtSetor.requestFocus();
			}

		}
	}

	public void validarNascimento(String msg) {

		int resposta = UtilMensagem.message(msg);

		if (this.txtDataNasc.getText().length() == 3) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					txtDataNasc.setText("/");
					txtDataNasc.requestFocus();
				}
			});
		}

		if (resposta == 0) {
			if (msg.contains("nascimento")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						txtDataNasc.setText(null);
						txtDataNasc.requestFocus();
					}
				});
			}
		}

	}

	private void validarTelefone(String msg) {
		int resposta = UtilMensagem.message(msg);

		if (resposta == 0) {
			if (msg.contains("telefone")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						txtTelefone.setText(null);
						txtTelefone.requestFocus();
					}
				});
			}
		}
	}

	private void validarCep(String msg) {
		int resposta = UtilMensagem.message(msg);

		if (resposta == 0) {
			if (msg.contains("cep")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						textCep.setText(null);
						textCep.requestFocus();
					}
				});
			}
		}
	}

	private void validarNumero(String msg) {
		int resposta = UtilMensagem.message(msg);

		if (resposta == 0) {
			if (msg.contains("número")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						textNumero.setText("");
						textNumero.requestFocus();
					}
				});
			}
		}
	}

	public void validarDataRegistro(String msg) {

		int resposta = UtilMensagem.message(msg);

		if (resposta == 0) {
			if (msg.contains("registro")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						txtDataRegistro.setText(null);
						txtDataRegistro.requestFocus();
					}
				});
			}
		}
	}

	public CadastroClienteView getCadastroClienteView() {
		return cadastroClienteView;
	}

	public void setCadastroClienteView(CadastroClienteView cadastroClienteView) {
		this.cadastroClienteView = cadastroClienteView;
	}

}
