package br.com.enxada.events;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import br.com.enxada.service.TransactionBuy;
import br.com.enxada.service.TransactionSell;
import br.com.enxada.util.Util;

public class EventTransfer implements Listener {
	
	@EventHandler
	public void onClickSign(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Block block = (Block) event.getClickedBlock();
		BlockState state = block.getState();

		if (state instanceof Sign) {
			Sign sign = (Sign) state;

			String line = sign.getLine(0);

			if (line.equals("Comprar") || line.equals("Buy")) {
				player.sendMessage(Util.chat("&a[Comprar] - &f" + block.getType().toString()));

				TransactionBuy buy = new TransactionBuy(sign, player);
				buy.doTransaction();

			}
			if (line.equals("Vender") || line.equals("Sell")) {
				player.sendMessage(Util.chat("&e[Vender] - &f" + block.getType().toString()));
				TransactionSell sell = new TransactionSell(sign, player);
				sell.doTransaction();
			}
		}

	}
	
	
}
