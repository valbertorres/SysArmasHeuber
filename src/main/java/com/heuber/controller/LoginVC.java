package com.heuber.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.heuber.view.DesktopPaneView;
import com.heuber.view.LoginView;

public class LoginVC {

	private static LoginVC instancia;
	private LoginView loginView;

	public static synchronized LoginVC getInstancia() {
		if (instancia == null)
			return instancia = new LoginVC();
		return instancia;
	}

	private JButton btnLogar;
	private JButton btnCancelar;

	public void inicializar() {
		this.inicializarComponente();
		this.inicializarListen();

	}

	public void inicializarListen() {
		this.btnLogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DesktopPaneView desktop = new DesktopPaneView();
				desktop.setExtendedState(desktop.MAXIMIZED_BOTH);
				desktop.setVisible(true);
			}
		});

		this.btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginView.dispose();
			}
		});
	}

	public void inicializarComponente() {
		this.btnLogar = this.loginView.getBtnLogar();
		this.btnCancelar = this.loginView.getBtnCancelar();
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

}


