package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.inventories.PlayerInventory;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.TransactionUtil;
import br.com.enxada.util.Util;

public class TransactionSell extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private PlayerInventory inventory;
	private Player player;
	private Player shopOwn;


	public TransactionSell(Sign sign, Player player) {
		super(sign, player);
		// TODO Auto-generated constructor stub
		this.player = getPlayer();
	}

	public void doTransaction() {
		// TODO Auto-generated method stub
		if (allowTransaction()) {

			double price;
			

			
			if(checkBalance(getOwnShopId(), getItemId()) == -1) {
				player.sendMessage(Util.chat("&c Shop não possui saldo suficiente para realizar a transação"));
				return;
			}
			
			
			if (getPrice() == -1) {
				price = market.getPrice(getItemId());
			} else {
				price = getPrice();
			}

			shopOwn = getOwnShopId();
	
			
			double oldBalance = user.getBalance(player);
			double oldShopBalance = user.getBalance(getShopId());
				
			TransactionUtil.sellTranserClient(getPlayer(), oldBalance, getPrice());
			TransactionUtil.buyTranserShop(getShopId(), oldShopBalance, getPrice());
				
			double balance = user.getBalance(getPlayer());
			double shopBalance = user.getBalance(getShopId());
				
			//System.out.println("--------------->Cliente: " + balance +":"+ getPlayer().toString());
			//System.out.println("---------------->Mercado: " + shopBalance +":"+ getOwnShopId().toString());
			
			shopOwn.sendMessage(Util.chat("&eForam enviados  &a" + price + " &ede sua conta para &a" + player.getName()));
				
			player.sendMessage(Util.chat("&eSaldo atual: &a" + balance + " &eSaldo antigo: &a"+ oldBalance +"\n&ePreço: &a" + price));
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
