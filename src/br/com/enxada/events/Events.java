package br.com.enxada.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;

public class Events implements Listener {
	User tablePlayer = new User(); 
	Market tableMarket = new Market();
	
	@EventHandler
	 
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer(); 
		
		if(!tablePlayer.playerExist(player.getUniqueId()))
			tablePlayer.addPlayer(player);
	 }
	 
}
