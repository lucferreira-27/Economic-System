package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.Util;

public class TransactionSell extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private Player player;


	public TransactionSell(Sign sign, Player player) {
		super(sign, player);
		// TODO Auto-generated constructor stub
		this.player = getPlayer();
	}

	public void doTransaction() {
		// TODO Auto-generated method stub
		if (allowTransaction()) {

			double price;
			double balance = user.getBalance(player);

			if (getPrice() == -1) {
				price = market.getPrice(getItemId());
			} else {
				price = getPrice();
			}
				user.updatePlayer(getPlayer(), balance + price); 
				balance = user.getBalance(getPlayer());
				player.sendMessage(Util.chat("&eBalance atual: &a" + balance + "\n&ePreço: &a" + price));
				player.sendMessage(Util.chat("&aTransação realizada!"));
				
				return;

		}
		player.sendMessage(Util.chat("&cTransação falhou!"));

	}
	
	public void getInvoice() {
		
	}
	
	public void setInvoice() {
		
	}


}
