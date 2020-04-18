package br.com.enxada.exceptions;

import org.bukkit.entity.Player;

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
		plugin.getServer().broadcastMessage(Util.chat("&c Economic ERROR: " + msg));
	}

	public EconomicException(String msg, Player player) {
		super(msg);
		player.sendMessage(Util.chat("&c" + msg));
	}

}
