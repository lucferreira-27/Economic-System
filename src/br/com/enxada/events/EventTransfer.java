package br.com.enxada.events;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import br.com.enxada.service.Transaction;
import br.com.enxada.service.TransactionBuy;
import br.com.enxada.service.TransactionSell;
import br.com.enxada.util.Util;

public class EventTransfer implements Listener {
	@EventHandler
	public void onClickSignSneaking(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = (Block) event.getClickedBlock();
		BlockState state = block.getState();
		if(player.isSneaking()) {
			
			if (state instanceof Sign) {
				Sign sign = (Sign) state;
	
				String line = sign.getLine(0);
	
				if (line.equals("Comprar") || line.equals("Buy")) {
					
					Transaction transaction = new Transaction(sign, player);
					transaction.registerShop(player);
	
	
	
				}
				
				if (line.equals("Vender") || line.equals("Sell")) {
					Transaction transaction = new Transaction(sign, player);
					transaction.registerShop(player);
	
				}
			}
		}
	}
	
	
	
	
	@EventHandler
	public void onClickSign(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Block block = (Block) event.getClickedBlock();
		BlockState state = block.getState();
		
		if(player.isSneaking()) 
			return;
		
		if (state instanceof Sign) {
			Sign sign = (Sign) state;

			String line = sign.getLine(0);

			if (line.equals("Comprar") || line.equals("Buy")) {
				
				TransactionBuy buy = new TransactionBuy(sign, player);
				player.sendMessage(Util.chat("&a[Comprar]"));
				buy.doTransaction();

			}
			if (line.equals("Vender") || line.equals("Sell")) {
				player.sendMessage(Util.chat("&e[Vender]"));
				
				TransactionSell sell = new TransactionSell(sign, player);
				sell.doTransaction();
			}
		}
		
	}
	
	
}
