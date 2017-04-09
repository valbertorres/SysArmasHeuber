package com.heuber.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.heuber.view.DesktopPaneView;
import com.heuber.view.LoginView;
import com.heuber.view.ClienteView;

public class DesktopPaneVC {

	private static DesktopPaneVC instancia;
	private DesktopPaneView desktopClienteView;

	public static synchronized DesktopPaneVC getInstancia() {
		if (instancia == null)
			return instancia = new DesktopPaneVC();
		return instancia;
	}

	private JPanel contentPane;
	private JMenuItem mntmCliente;
	//private JMenuItem mntmCadastroDeFornecedor;
	//private JMenuItem mntmAutomel;
	//private JMenu mnInformcoes;
	//private JMenuItem mntmContato;
	//private JMenuItem mntmInformaes;
	private JDesktopPane desktopPane;
	//private JMenuItem mntmSair;
	//private JMenuItem mntmFazerLogin;
	//private JMenuItem mntmLocaoes;
	private ClienteView cadastroClienteView;
	//private CadastroVeiculoView cadastroVeiculoView;
	//private LocacoesView locacoesView;
	//private LocalizarAutomovelView automovelView;

	public void inicializar() {
		this.inicializarComponent();
		this.inicializarListen();
	}

	private void inicializarComponent() {
		this.mntmCliente = this.desktopClienteView.getMntmCliente();
		//this.mntmCadastroDeFornecedor = this.desktopClienteView.getMntmCadastroDeFornecedor();
		//this.mntmAutomel = this.desktopClienteView.getMntmAutomel();
		//this.mnInformcoes = this.desktopClienteView.getMnInformcoes();
		//this.mntmContato = this.desktopClienteView.getMntmContato();
		//this.mntmInformaes = this.desktopClienteView.getMntmInformaes();
		this.desktopPane = this.desktopClienteView.getDesktopPane();
		//this.mntmSair = this.desktopClienteView.getMntmSair();
		//this.mntmFazerLogin = this.desktopClienteView.getMntmFazerLogin();
		//this.mntmLocaoes = this.desktopClienteView.getMntmLocaoes();

	}

	private void abriCadastroCliente() {
		
		if (this.cadastroClienteView == null) {
			this.cadastroClienteView = new ClienteView();
			this.desktopPane.add(cadastroClienteView);
			this.cadastroClienteView.setLocation(400, 150);
		}
		this.cadastroClienteView.setVisible(true);
	}

	public void fecharCadastroCliente() {
		this.cadastroClienteView.setVisible(false);
	}
	/*
	private void abriCadastroVeiculo() {
		if (this.cadastroVeiculoView == null) {
			this.cadastroVeiculoView = new CadastroVeiculoView();
			this.desktopPane.add(this.cadastroVeiculoView);
			this.cadastroVeiculoView.setLocation(400, 150);
		}
		this.cadastroVeiculoView.setVisible(true);
	}

	public void fecharCadastroVeiculo() {
		this.cadastroVeiculoView.setVisible(false);
	}

	private void abrirLocacoes() {
		if (this.locacoesView == null) {
			this.locacoesView = new LocacoesView();
			this.desktopPane.add(locacoesView);
			this.locacoesView.setLocation(400, 150);
		}
		this.locacoesView.setVisible(true);
	}

	public void fecharLocacoes() {
		this.locacoesView.setVisible(false);
	}

	private void abriBuscaAutomovel() {
		if (this.automovelView == null) {
			this.automovelView = new LocalizarAutomovelView();
			this.desktopPane.add(automovelView);
			this.automovelView.setLocation(400, 150);
		}
		this.automovelView.setVisible(true);
	}

	public void fecharBusca() {
		this.automovelView.setVisible(false);
	}
*/
	private void inicializarListen() {
		this.mntmCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				abriCadastroCliente();
			}
		});
/*
		this.mntmCadatroDeAutomovel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abriCadastroVeiculo();
			}
		});

		this.mntmLocaoes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirLocacoes();
			}
		});

		this.mntmAutomel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abriBuscaAutomovel();
			}
		});

		this.mntmSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				desktopClienteView.dispose();
			}
		});

		this.mntmFazerLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView loginView = new LoginView();
				desktopClienteView.dispose();
				loginView.setLocationRelativeTo(null);
				loginView.setVisible(true);
			}
		});
*/
	}

	public DesktopPaneView getCadastroClienteView() {
		return desktopClienteView;
	}

	public void setCadastroClienteView(DesktopPaneView desktopClienteView) {
		this.desktopClienteView = desktopClienteView;
	}

}

