package com.heuber.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.heuber.view.DesktopPaneView;
import com.heuber.view.LoginView;
import com.heuber.view.CadastroClienteView;
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

	public void inicializar() {
		this.inicializarComponent();
		this.inicializarListen();
	}

	private void inicializarListen() {
		this.mntmCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abriCliente(true);
			}
		});
	}

	private void inicializarComponent() {
		this.desktopPane = this.desktopClienteView.getDesktopPane();
		this.mntmCliente = this.desktopClienteView.getMntmCliente();

	}

	private void abriCliente(boolean view) {
		CadastroClienteView clienteView = CadastroClienteView.getInstancia();
		clienteView.setLocation(280, 130);
		this.desktopPane.add(clienteView);
		clienteView.setVisible(true);
		
	}

	public DesktopPaneView getCadastroClienteView() {
		return desktopClienteView;
	}

	public void setCadastroClienteView(DesktopPaneView desktopClienteView) {
		this.desktopClienteView = desktopClienteView;
	}

}
