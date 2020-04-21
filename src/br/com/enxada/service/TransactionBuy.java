package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.TransactionUtil;
import br.com.enxada.util.Util;

public class TransactionBuy extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private Player player;
	private Player shopOwn;

	public TransactionBuy(Sign sign, Player player) {
		super(sign, player);
		// TODO Auto-generated constructor stub
		this.player = getPlayer();
	}



	public void doTransaction() {
		// TODO Auto-generated method stub
		if (allowTransaction()) {
			System.out.println(getOwnShopId());
			
			
			double price;


			if (getPrice() == -1) {
				price = market.getPrice(getItemId());
			} else {
				price = getPrice();
			}
			
			
			if(checkBalance(getPlayer(), getItemId()) >= 0) {
				
				shopOwn = getOwnShopId();
				
				double oldBalance = user.getBalance(player);
				double oldShopBalance = user.getBalance(getShopId());
				
				TransactionUtil.sellTranserShop(getShopId(), oldShopBalance, getPrice());
				TransactionUtil.buyTranserClient(getPlayer(), oldBalance, getPrice());
				
				double balance = user.getBalance(getPlayer());
				double shopBalance = user.getBalance(getShopId());
				
				//System.out.println("--------------->Cliente: " + balance +":"+ getPlayer().toString());
				//System.out.println("---------------->Mercado: " + shopBalance +":"+ getOwnShopId().toString());
				
				player.sendMessage(Util.chat("&eSaldo atual: &a" + balance + " &eSaldo antigo: &a"+ oldBalance +"\n&ePreço: &a" + price));
				player.sendMessage(Util.chat("&aTransação realizada!"));
				
				
				shopOwn.sendMessage(Util.chat("&eVoce recebeu &a" + price + " &ede " + player.getName()));

				return;
			}else {
				
				player.sendMessage(Util.chat("&eSaldo insuficiente"));
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
