package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.Util;

public class TransactionBuy extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private Player player;


	public TransactionBuy(Sign sign, Player player) {
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
			
			
			if(checkBalance(getPlayer(), getItemId()) >= 0) {
				
				user.updatePlayer(getPlayer(), balance - price);
				balance = user.getBalance(getPlayer());
				player.sendMessage(Util.chat("&eSaldo atual: &a" + balance + "\n&ePreço: &a" + price));
				player.sendMessage(Util.chat("&aTransação realizada!"));

				return;
			}else {
				
				player.sendMessage(Util.chat("&eSaldo insuficiente"));
				player.sendMessage(Util.chat("&eSaldo atual: &a" + balance + "\n&ePreço: &a" + price));
				player.sendMessage(Util.chat("&cTransação falhou!"));
				return;
			
			}


			
		}
		
		player.sendMessage(Util.chat("&cTransação falhou!"));
		throw new EconomicException("Algo inesperado aconteceu!");
	}

	
	public void getInvoice() {
		
	}
	
	public void setInvoice() {
		
	}


















}
