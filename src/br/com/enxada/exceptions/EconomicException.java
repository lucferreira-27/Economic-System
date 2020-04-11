package br.com.enxada.exceptions;

import java.io.Serializable;

import br.com.enxada.Main;
import br.com.enxada.util.Util;

public class EconomicException extends RuntimeException {
	Main plugin = Main.getPlugin(Main.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EconomicException(String msg) {
		super(msg);
		plugin.getServer().broadcastMessage(Util.chat("&c Economic ERROR: "+msg) );
	}
	
}
