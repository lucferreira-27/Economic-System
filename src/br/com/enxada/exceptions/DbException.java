package br.com.enxada.exceptions;

import java.io.Serializable;

import br.com.enxada.Main;
import br.com.enxada.util.Util;

public class DbException extends RuntimeException {
	
	Main plugin = Main.getPlugin(Main.class);
	
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		// TODO Auto-generated constructor stub
		super (msg);
		plugin.getServer().broadcastMessage(Util.chat("&c SQL ERROR: "+msg) );
		
	}

}
