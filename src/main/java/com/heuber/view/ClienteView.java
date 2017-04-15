package com.heuber.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ClienteView extends JWindow {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private CadastroClienteView cadastroClienteView;
	private JTable tabelaClientes;
	private JTextField textField;
	private JButton btnCadastrarCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteView frame = new ClienteView();
					frame.setVisible(true);
					// frame.setClosable(false);
					// frame.setIconifiable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static ClienteView instancia;

	public static synchronized ClienteView getInstancia() {
		if (instancia == null)
			return instancia = new ClienteView();
		return instancia;
	}

	private void inicializar() {
		
	}

	private ClienteView() {
		this.GuiView();
	}

	public void GuiView() {

		// getContentPane().setLayout(null);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(10, 60, 1000, 600);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Novo Cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Avenir Next", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 106, 85, 16);
		contentPane.add(lblNewLabel);

		btnCadastrarCliente = new JButton("");
		btnCadastrarCliente.setBounds(30, 34, 60, 60);
		btnCadastrarCliente.setBorderPainted(false);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(
					"C:\\eclips mars\\workspace\\PjHeuberSysArma\\src\\main\\java\\imagens\\cadastroIcon.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		Image scaled = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaled);
		btnCadastrarCliente.setIcon(icon);
		contentPane.add(btnCadastrarCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 161, 750, 370);
		contentPane.add(scrollPane);

		tabelaClientes = new JTable();
		scrollPane.setViewportView(tabelaClientes);
		tabelaClientes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nome", "Documento", "Idade", "Telefone", "Estado", "Cidade" }));

		JLabel lblEditarCliente = new JLabel("Editar Cliente");
		lblEditarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarCliente.setFont(new Font("Avenir Next", Font.BOLD, 12));
		lblEditarCliente.setBounds(117, 105, 85, 16);
		contentPane.add(lblEditarCliente);

		JButton btnEditarCliente = new JButton("");
		btnEditarCliente.setBounds(130, 34, 60, 60);
		btnEditarCliente.setBorderPainted(false);
		BufferedImage imgEditar = null;
		try {
			imgEditar = ImageIO.read(
					new File("C:\\eclips mars\\workspace\\PjHeuberSysArma\\src\\main\\java\\imagens\\editarIcon.png"));
			// .read(new
			// File("/Users/MaryBeds/git/SysArmasHeuber/src/main/java/imagens/editarIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image scaledEditar = imgEditar.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon iconEditar = new ImageIcon(scaledEditar);
		btnEditarCliente.setIcon(iconEditar);
		contentPane.add(btnEditarCliente);

		JButton btnDeletar = new JButton("");
		btnDeletar.setBounds(223, 34, 60, 60);
		btnDeletar.setBorderPainted(false);
		BufferedImage imgDeletar = null;
		try {
			imgDeletar = ImageIO.read(
					new File("C:\\eclips mars\\workspace\\PjHeuberSysArma\\src\\main\\java\\imagens\\deletarIcon.png"));
			// .read(new
			// File("/Users/MaryBeds/git/SysArmasHeuber/src/main/java/imagens/deletarIcon.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		Image scaledDeletar = imgDeletar.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon iconDeletar = new ImageIcon(scaledDeletar);
		btnDeletar.setIcon(iconDeletar);
		contentPane.add(btnDeletar);

		JLabel lblDeletar = new JLabel("Deletar Cliente");
		lblDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletar.setFont(new Font("Avenir Next", Font.BOLD, 12));
		lblDeletar.setBounds(214, 106, 93, 16);
		contentPane.add(lblDeletar);

		JLabel lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblFiltrar.setBounds(320, 72, 41, 16);
		contentPane.add(lblFiltrar);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(361, 67, 136, 27);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Pesquisar:");
		lblNewLabel_1.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(514, 72, 70, 16);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(580, 68, 256, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnIr = new JButton("");
		btnIr.setBounds(841, 67, 30, 30);
		btnIr.setBorderPainted(false);
		BufferedImage imgIr = null;
		try {
			imgIr = ImageIO
					.read(new File("C:\\eclips mars\\workspace\\PjHeuberSysArma\\src\\main\\java\\imagens\\btnIr.png"));
			// imgIr = ImageIO.read(new
			// File("/Users/MaryBeds/git/SysArmasHeuber/src/main/java/imagens/btnIr.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		Image scaledIr = imgIr.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconIr = new ImageIcon(scaledIr);
		btnIr.setIcon(iconIr);
		contentPane.add(btnIr);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public CadastroClienteView getCadastroClienteView() {
		return cadastroClienteView;
	}

	public void setCadastroClienteView(CadastroClienteView cadastroClienteView) {
		this.cadastroClienteView = cadastroClienteView;
	}

	public JTable getTabelaClientes() {
		return tabelaClientes;
	}

	public void setTabelaClientes(JTable tabelaClientes) {
		this.tabelaClientes = tabelaClientes;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnCadastrarCliente() {
		return btnCadastrarCliente;
	}

	public void setBtnCadastrarCliente(JButton btnCadastrarCliente) {
		this.btnCadastrarCliente = btnCadastrarCliente;
	}

}
