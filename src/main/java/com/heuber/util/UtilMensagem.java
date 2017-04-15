package com.heuber.util;

import javax.swing.JOptionPane;

public class UtilMensagem {

	public UtilMensagem() {
	}

	public static int message(String strMessage) {
		Object[] opcao = { "ok!" };
		return JOptionPane.showOptionDialog(null, strMessage, "Erro", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
	}

	public static void message(Exception exception) {
		message(exception.getMessage());
	}

}
