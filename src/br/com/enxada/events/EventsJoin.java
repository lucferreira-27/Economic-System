package br.com.enxada.events;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.service.Transaction;
import br.com.enxada.service.TransactionBuy;
import br.com.enxada.service.TransactionSell;
import br.com.enxada.util.Util;

public class EventsJoin implements Listener {
	User tablePlayer = new User();
	Market tableMarket = new Market();

	@EventHandler

	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		if (!tablePlayer.playerExist(player.getUniqueId()))
			tablePlayer.addPlayer(player);
	}



}
