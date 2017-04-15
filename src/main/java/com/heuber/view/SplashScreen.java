package com.heuber.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

public class SplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private JPanel header;
	private JPanel footer;
	private ImageIcon imagem;
	private JLabel logo;
	private static JProgressBar barra;
	private static Timer timer;
	private static int DURACTION = 25, perceMin = 1, perceMax = 100;
	private LoginView loginView;

	ActionListener action = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			barra.setValue(perceMin);
			if (perceMax == perceMin) {
				timer.stop();
				SplashScreen.this.setVisible(false);
				dispose();
			}
			perceMin++;

			if (perceMin == 101) {
				loginView = LoginView.getInstancia();
				loginView.setLocationRelativeTo(null);
				loginView.setVisible(true);
			}
		}

	};

	public SplashScreen() {
		createGui();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		starBarra();

	}

	public void starBarra() {
		SplashScreen.timer = new Timer(SplashScreen.DURACTION, action);
		SplashScreen.timer.start();
	}

	public void createGui() {

		this.container = getContentPane();
		this.header = new JPanel();
		this.footer = new JPanel();
		this.imagem = new ImageIcon(getClass().getResource("/imagens/logo.jpg"));
		this.logo = new JLabel(imagem);
		this.barra = new JProgressBar();

		this.header.setBorder(new EtchedBorder());
		this.header.add(logo, BorderLayout.CENTER);

		this.footer.setBorder(new EtchedBorder());
		this.footer.setLayout(new BoxLayout(footer, BoxLayout.PAGE_AXIS));
		this.footer.add(barra);

		this.container.setLayout(new BorderLayout());
		this.container.add(header, BorderLayout.CENTER);
		this.container.add(footer, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SplashScreen view = new SplashScreen();

	}

}
