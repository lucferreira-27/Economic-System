package br.com.enxada.util;

import org.bukkit.ChatColor;

import br.com.enxada.Main;

public class Util {
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
//		&0 Black
//		&1 Dark Blue
//		&2 Dark Green
//		&3 Dark Aqua
//		&4 Dark Red
//		&5 Purple
//		&6 Gold
//		&7 Gray
//		&8 Dark Gray
//		&9 Indigo
//		&a Bright Green
//		&b Aqua
//		&c Red
//		&d Pink
//		&e Yellow
//		&f White
	}
	
	public static void clearChat() {
		Main plugin = Main.getPlugin(Main.class);
		for(int i = 0; i < 250; i++)
			plugin.getServer().broadcastMessage("");
		
		plugin.getServer().broadcastMessage(chat("&6--------- CHAT APAGADO! ---------"));
	}
	

}
