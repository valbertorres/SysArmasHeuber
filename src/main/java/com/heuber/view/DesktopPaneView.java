package com.heuber.view;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.heuber.controller.DesktopPaneVC;

import javax.swing.JButton;

public class DesktopPaneView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnCadastro;
	private JMenuItem mntmCliente;
	private JMenuItem mntmCadastroDeFornecedor;
	private JMenu mnLocalizar;
	private JMenuItem mntmAutomel;
	private JMenu mnInformcoes;
	private JMenuItem mntmContato;
	private JMenuItem mntmInformaes;
	private JMenuItem mntmSair;
	private JMenuItem mntmFazerLogin;
	private JDesktopPane desktopPane;
	private JMenu mnLocaes;
	private JMenuItem mntmLocaoes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesktopPaneView frame = new DesktopPaneView();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static DesktopPaneView instancia;

	public static synchronized DesktopPaneView getInstancia() {
		if (instancia == null)
			return instancia = new DesktopPaneView();
		return instancia;
	}

	private DesktopPaneView() {
		this.Gui();
		this.inicializar();
	}

	private void inicializar() {
		DesktopPaneVC desktopPaneCtrl = new DesktopPaneVC().getInstancia();
		desktopPaneCtrl.setCadastroClienteView(this);
		desktopPaneCtrl.inicializar();
	}

	private void Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 520);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 15));
		setJMenuBar(menuBar);

		mnInformcoes = new JMenu("Menu Principal");
		menuBar.add(mnInformcoes);

		mntmInformaes = new JMenuItem("Importar");
		mnInformcoes.add(mntmInformaes);

		mntmFazerLogin = new JMenuItem("Fazer Login");
		mnInformcoes.add(mntmFazerLogin);

		mntmSair = new JMenuItem("Sair");
		mnInformcoes.add(mntmSair);

		mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		mntmCliente = new JMenuItem("Cliente");
		mnCadastro.add(mntmCliente);

		mntmCadastroDeFornecedor = new JMenuItem("Fornecedor");
		mnCadastro.add(mntmCadastroDeFornecedor);

		JMenu mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Produtos");
		mnProduto.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Categorias");
		mnProduto.add(mntmNewMenuItem_2);

		mnLocalizar = new JMenu("Vendas");
		menuBar.add(mnLocalizar);
		mntmAutomel = new JMenuItem("Orçamento");
		mnLocalizar.add(mntmAutomel);

		JMenuItem mntmNewMenuItem = new JMenuItem("Venda(NF-e)");
		mnLocalizar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Devolução");
		mnLocalizar.add(mntmNewMenuItem_1);

		mnLocaes = new JMenu("Ajuda");
		menuBar.add(mnLocaes);
		mntmLocaoes = new JMenuItem("Sobre");
		mnLocaes.add(mntmLocaoes);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(desktopPane,
				GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(desktopPane,
				GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE));

		getContentPane().setLayout(groupLayout);
	}

	public JMenu getMnCadastro() {
		return mnCadastro;
	}

	public void setMnCadastro(JMenu mnCadastro) {
		this.mnCadastro = mnCadastro;
	}

	public JMenuItem getMntmCliente() {
		return mntmCliente;
	}

	public void setMntmCliente(JMenuItem mntmCliente) {
		this.mntmCliente = mntmCliente;
	}

	public JMenuItem getMntmCadastroDeFornecedor() {
		return mntmCadastroDeFornecedor;
	}

	public void setMntmCadastroDeFornecedor(JMenuItem mntmCadastroDeFornecedor) {
		this.mntmCadastroDeFornecedor = mntmCadastroDeFornecedor;
	}

	public JMenu getMnLocalizar() {
		return mnLocalizar;
	}

	public void setMnLocalizar(JMenu mnLocalizar) {
		this.mnLocalizar = mnLocalizar;
	}

	public JMenuItem getMntmAutomel() {
		return mntmAutomel;
	}

	public void setMntmAutomel(JMenuItem mntmAutomel) {
		this.mntmAutomel = mntmAutomel;
	}

	public JMenu getMnInformcoes() {
		return mnInformcoes;
	}

	public void setMnInformcoes(JMenu mnInformcoes) {
		this.mnInformcoes = mnInformcoes;
	}

	public JMenuItem getMntmContato() {
		return mntmContato;
	}

	public void setMntmContato(JMenuItem mntmContato) {
		this.mntmContato = mntmContato;
	}

	public JMenuItem getMntmInformaes() {
		return mntmInformaes;
	}

	public void setMntmInformaes(JMenuItem mntmInformaes) {
		this.mntmInformaes = mntmInformaes;
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

	public void setMntmSair(JMenuItem mntmSair) {
		this.mntmSair = mntmSair;
	}

	public JMenuItem getMntmFazerLogin() {
		return mntmFazerLogin;
	}

	public void setMntmFazerLogin(JMenuItem mntmFazerLogin) {
		this.mntmFazerLogin = mntmFazerLogin;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public JMenu getMnLocaes() {
		return mnLocaes;
	}

	public void setMnLocaes(JMenu mnLocaes) {
		this.mnLocaes = mnLocaes;
	}

	public JMenuItem getMntmLocaoes() {
		return mntmLocaoes;
	}

	public void setMntmLocaoes(JMenuItem mntmLocaoes) {
		this.mntmLocaoes = mntmLocaoes;
	}

}
