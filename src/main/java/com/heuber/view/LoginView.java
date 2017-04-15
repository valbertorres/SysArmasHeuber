package com.heuber.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.heuber.controller.LoginVC;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.Image;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLogar;
	private JButton btnCancelar;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static LoginView instancia;

	public static synchronized LoginView getInstancia() {
		if (instancia == null)
			return instancia = new LoginView();
		return instancia;
	}

	private void inicializar() {
		LoginVC loginVC = LoginVC.getInstancia();
		loginVC.setLoginView(this);
		loginVC.inicializar();
	}

	private LoginView() {
		this.GuiView();
		this.inicializar();
	}

	public void GuiView() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 440, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel iconLogo = new JLabel("");
		iconLogo.setBounds(0, 0, 440, 260);
		BufferedImage img = null;
		try {
			img = ImageIO
					.read(new File("C:\\eclips mars\\workspace\\PjHeuberSysArma\\src\\main\\java\\imagens\\logo.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Image scaled = img.getScaledInstance(440, 260, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaled);
		iconLogo.setIcon(icon);
		panel.add(iconLogo);

		btnLogar = new JButton("Entrar");
		btnLogar.setBounds(115, 383, 100, 30);
		panel.add(btnLogar);
		btnLogar.setForeground(SystemColor.textHighlightText);
		btnLogar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLogar.setBackground(SystemColor.textHighlight);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 383, 100, 30);
		panel.add(btnCancelar);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Avenir Next", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(255, 69, 0));

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(95, 269, 61, 16);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(90, 345, 260, 26);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(95, 326, 61, 16);
		panel.add(lblSenha);

		textField_1 = new JTextField();
		textField_1.setBounds(90, 288, 260, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

	}

	public JButton getBtnLogar() {
		return btnLogar;
	}

	public void setBtnLogar(JButton btnLogar) {
		this.btnLogar = btnLogar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
