package com.heuber.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.heuber.TO.CidadeTO;
import com.heuber.controller.CadastroClienteCtrl;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class CadastroClienteView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblDataDeNasc;
	private JTextField txtDataNasc;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JTextField txtTelefone;
	private JLabel lblTelefone;
	private JTextField txtDataRegistro;
	private JLabel lblDataDeRegistro;
	private JLabel lblSexo;
	private JLabel lblCpf;
	private JPanel panel_1;
	private JLabel lblRua;
	private JTextField txtRua;
	private JTextField txtSetor;
	private JLabel lblSetor;
	private JLabel lblCidade;
	private JComboBox comboBoxCidade;
	private JLabel lblBairro;
	private JLabel label;
	private JTextField textCep;
	private JLabel label_1;
	private JTextField textNumero;
	private JButton btnDeletar;
	private JComboBox comboBoxSexo;

	private JButton btnCancelar;
	private JButton btnSalvar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteView frame = new CadastroClienteView();
					frame.setVisible(true);
					frame.setClosable(false);
					frame.setIconifiable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static CadastroClienteView instancia;
	private JTextField textfBairro;

	public static synchronized CadastroClienteView getInstancia() {
		if (instancia == null)
			return instancia = new CadastroClienteView();
		return instancia;
	}

	private CadastroClienteView() {
		setTitle("CADASTRO DE CLIENTE");
		this.GuiView();
		this.inicializar();
	}

	private void inicializar() {
		CadastroClienteCtrl ctrl = CadastroClienteCtrl.getInstancia();
		ctrl.setCadastroClienteView(this);
		ctrl.inicializar();
	}

	public void GuiView() {

		getContentPane().setLayout(null);
		setBounds(10, 60, 860, 500);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "INFORMA\u00C7\u00D5ES PESSOAIS", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(16, 17, 800, 168);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome Completo*:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblNewLabel.setBounds(25, 44, 102, 16);
		panel.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtNome.setBounds(131, 39, 270, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);

		lblDataDeNasc = new JLabel("Data de Nasc.*:");
		lblDataDeNasc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeNasc.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblDataDeNasc.setBounds(25, 79, 102, 16);
		panel.add(lblDataDeNasc);

		txtDataNasc = new JFormattedTextField();
		txtDataNasc.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtDataNasc.setColumns(10);
		txtDataNasc.setBounds(131, 73, 80, 26);
		panel.add(txtDataNasc);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblSexo.setBounds(223, 77, 31, 16);
		panel.add(lblSexo);

		comboBoxSexo = new JComboBox();
		comboBoxSexo.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		comboBoxSexo.setBounds(261, 74, 140, 27);
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] { "Selecione", "Masculino", "Feminino" }));
		panel.add(comboBoxSexo);

		lblCpf = new JLabel("CPF*:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblCpf.setBounds(25, 112, 102, 16);
		panel.add(lblCpf);

		txtCpf = new JFormattedTextField();
		txtCpf.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtCpf.setColumns(10);
		txtCpf.setBounds(131, 107, 270, 26);
		panel.add(txtCpf);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(563, 39, 210, 26);
		panel.add(txtEmail);

		lblEmail = new JLabel("Email*:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblEmail.setBounds(457, 44, 102, 16);
		panel.add(lblEmail);

		txtTelefone = new JFormattedTextField();
		txtTelefone.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(563, 69, 210, 26);
		panel.add(txtTelefone);

		lblTelefone = new JLabel("Telefone*:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblTelefone.setBounds(457, 74, 102, 16);
		panel.add(lblTelefone);

		txtDataRegistro = new JFormattedTextField();
		txtDataRegistro.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtDataRegistro.setColumns(10);
		txtDataRegistro.setBounds(563, 102, 210, 26);
		panel.add(txtDataRegistro);

		lblDataDeRegistro = new JLabel("Data de Registro:");
		lblDataDeRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeRegistro.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblDataDeRegistro.setBounds(457, 107, 102, 16);
		panel.add(lblDataDeRegistro);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"INFORMA\u00C7\u00D5ES DE ENDERE\u00C7O", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(16, 218, 800, 160);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblRua = new JLabel("Rua*:");
		lblRua.setBounds(25, 79, 102, 17);
		lblRua.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRua.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		panel_1.add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(131, 73, 270, 27);
		txtRua.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtRua.setColumns(10);
		panel_1.add(txtRua);

		txtSetor = new JTextField();
		txtSetor.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		txtSetor.setColumns(10);
		txtSetor.setBounds(131, 105, 270, 27);
		panel_1.add(txtSetor);

		lblSetor = new JLabel("Complemento:");
		lblSetor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSetor.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblSetor.setBounds(25, 110, 102, 17);
		panel_1.add(lblSetor);

		lblCidade = new JLabel("Cidade*:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblCidade.setBounds(457, 77, 102, 16);
		panel_1.add(lblCidade);

		comboBoxCidade = new JComboBox();
		comboBoxCidade.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		comboBoxCidade.setBounds(571, 73, 200, 27);
		panel_1.add(comboBoxCidade);

		lblBairro = new JLabel("Bairro*:");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblBairro.setBounds(457, 110, 102, 16);
		panel_1.add(lblBairro);

		label = new JLabel("CEP*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		label.setBounds(25, 45, 102, 17);
		panel_1.add(label);

		textCep = new JFormattedTextField();
		textCep.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		textCep.setColumns(10);
		textCep.setBounds(131, 40, 130, 27);
		panel_1.add(textCep);

		label_1 = new JLabel("Número*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		label_1.setBounds(280, 45, 54, 17);
		panel_1.add(label_1);

		textNumero = new JTextField();
		textNumero.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		textNumero.setColumns(10);
		textNumero.setBounds(335, 40, 66, 27);
		panel_1.add(textNumero);

		textfBairro = new JTextField();
		textfBairro.setBounds(571, 109, 200, 27);
		panel_1.add(textfBairro);
		textfBairro.setColumns(10);

		btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(CadastroClienteView.class.getResource("/imagens/btnSalvar.png")));
		btnSalvar.setFont(new Font("Avenir Next", Font.BOLD, 13));
		btnSalvar.setBounds(548, 400, 117, 40);
		contentPane.add(btnSalvar);

		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(CadastroClienteView.class.getResource("/imagens/btnCanelar.png")));
		btnCancelar.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		btnCancelar.setBounds(696, 400, 117, 40);
		contentPane.add(btnCancelar);

		btnDeletar = new JButton("");
		btnDeletar.setIcon(new ImageIcon(CadastroClienteView.class.getResource("/imagens/btnDeletar.png")));
		btnDeletar.setBounds(16, 400, 117, 40);
		contentPane.add(btnDeletar);

	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTextField getTxtDataNasc() {
		return txtDataNasc;
	}

	public void setTxtDataNasc(JTextField txtDataNasc) {
		this.txtDataNasc = txtDataNasc;
	}

	public JTextField getTxtCpf() {
		return txtCpf;
	}

	public void setTxtCpf(JTextField txtCpf) {
		this.txtCpf = txtCpf;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtTelefone() {
		return txtTelefone;
	}

	public void setTxtTelefone(JTextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}

	public JTextField getTxtDataRegistro() {
		return txtDataRegistro;
	}

	public void setTxtDataRegistro(JTextField txtDataRegistro) {
		this.txtDataRegistro = txtDataRegistro;
	}

	public JTextField getTxtRua() {
		return txtRua;
	}

	public void setTxtRua(JTextField txtRua) {
		this.txtRua = txtRua;
	}

	public JTextField getTxtSetor() {
		return txtSetor;
	}

	public void setTxtSetor(JTextField txtSetor) {
		this.txtSetor = txtSetor;
	}


	public JComboBox getComboBoxCidade() {
		return comboBoxCidade;
	}

	public void setComboBoxCidade(JComboBox comboBoxCidade) {
		this.comboBoxCidade = comboBoxCidade;
	}

	public JTextField getTextfBairro() {
		return textfBairro;
	}

	public void setTextfBairro(JTextField textfBairro) {
		this.textfBairro = textfBairro;
	}

	public JTextField getTextCep() {
		return textCep;
	}

	public void setTextCep(JTextField textCep) {
		this.textCep = textCep;
	}

	public JTextField getTextNumero() {
		return textNumero;
	}

	public void setTextNumero(JTextField textNumero) {
		this.textNumero = textNumero;
	}

	public JButton getBtnDeletar() {
		return btnDeletar;
	}

	public void setBtnDeletar(JButton btnDeletar) {
		this.btnDeletar = btnDeletar;
	}

	public JComboBox getComboBoxSexo() {
		return comboBoxSexo;
	}

	public void setComboBoxSexo(JComboBox comboBoxSexo) {
		this.comboBoxSexo = comboBoxSexo;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

}
