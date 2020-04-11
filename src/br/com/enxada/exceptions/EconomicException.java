package br.com.enxada.exceptions;

import java.io.Serializable;

public class EconomicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EconomicException(String msg) {
		super(msg);
	}
	
}
