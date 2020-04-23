package br.com.enxada.service;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.InventoriesUtil;
import br.com.enxada.util.TransactionUtil;
import br.com.enxada.util.Util;

public class TransactionBuy extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private OfflinePlayer player;
	private OfflinePlayer shopOwn;
	private Player playerOn = null;
	private Player shopOwnOn;
	public TransactionBuy(Sign sign, org.bukkit.material.Sign signMaterial, Player player) {
		super(sign, signMaterial, player);
		// TODO Auto-generated constructor stub
		this.player = getPlayer();
		if(player.isOnline()) {
			playerOn = player;
		}
	}



	public void doTransaction() {
		// TODO Auto-generated method stub
		if (allowTransaction()) {
			System.out.println(getOwnShopIdOff());
			
			
			double price;


			if (getPrice() == -1) {
				price = market.getPrice(getItemId());
			} else {
				price = getPrice();
			}
			
			
			if(checkBalance(getPlayer(), getItemId()) >= 0) {
				
				shopOwn = getOwnShopIdOff();
				if(shopOwn.isOnline()) 
					shopOwnOn = Util.getPlayer(shopOwn);
				
				
				double oldBalance = user.getBalance(player.getUniqueId());
				double oldShopBalance = user.getBalance(getShopIdOff().getUniqueId());
				
				TransactionUtil.sellTranserShop(getShopIdOff(), oldShopBalance, getPrice());
				TransactionUtil.buyTranserClient(getPlayer(), oldBalance, getPrice());
				
				setChest(Util.getChestInLocation(getLoc()));
				InventoriesUtil.trasecItem(getChest(), getPlayer(), Util.getItemStack(getItemId()));
				
				double balance = user.getBalance(getPlayer().getUniqueId());
				double shopBalance = user.getBalance(getShopIdOff().getUniqueId());
				
				System.out.println("--------------->Cliente: " + balance +":"+ getPlayer().toString());
				System.out.println("---------------->Mercado: " + shopBalance +":"+ getOwnShopIdOff().toString());
				
				
					
					playerOn.sendMessage(Util.chat("&eSaldo atual: &a" + balance + " &eSaldo antigo: &a"+ oldBalance +"\n&ePreço: &a" + price));
					playerOn.sendMessage(Util.chat("&aTransação realizada!"));
				
				
				if(shopOwn.isOnline()) {
					shopOwnOn.sendMessage(Util.chat("&eVoce recebeu &a" + price + " &ede " +shopOwnOn.getName()));
					if(getPlayer().isOnline())
						getPlayer().sendMessage(Util.chat("&eForam enviados  &a" + price + " &ede sua conta para &a" + shopOwnOn.getName()));
				}
				return;
			}else {
				
				playerOn.sendMessage(Util.chat("&eSaldo insuficiente"));
				playerOn.sendMessage(Util.chat("&cTransação falhou!"));
				return;
			
			}


			
		}
		
		if(player.isOnline())
			cancelTrasaction("&cTransação falhou!");
	}

	
	public void getInvoice() {
		
	}
	
	public void setInvoice() {
		
	}


















}
