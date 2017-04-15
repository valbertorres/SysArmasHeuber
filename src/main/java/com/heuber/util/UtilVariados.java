package com.heuber.util;

public abstract class UtilVariados {

	private UtilVariados() {
	}

	public static boolean eNumerico(String numero) {
		try {
			Long.parseLong(numero);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
