package br.com.enxada.exceptions;

import java.io.Serializable;

public class DbException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		// TODO Auto-generated constructor stub
		super (msg);
	}

}
