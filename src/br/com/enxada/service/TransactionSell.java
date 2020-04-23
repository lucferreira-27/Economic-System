package br.com.enxada.service;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.inventories.PlayerInventories;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.InventoriesUtil;
import br.com.enxada.util.TransactionUtil;
import br.com.enxada.util.Util;

public class TransactionSell extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private PlayerInventories pInventory;
	private Player player;

	private OfflinePlayer shopOwn;
	private Player shopOwnOn;

	public TransactionSell(Sign sign,org.bukkit.material.Sign signMaterial, Player player) {
		super(sign, signMaterial, player);
		// TODO Auto-generated constructor stub
		
		this.player = player;
		this.shopOwn = getOwnShopIdOff();
		if (getOwnShopIdOff().isOnline())
			setShopIdOn(Util.getPlayer(getOwnShopIdOff()));
		this.shopOwnOn = getShopIdOn();

	}

	public void doTransaction() {
		// TODO Auto-generated method stub
		if (allowTransaction()) {

			double price;

			if (checkBalance(getOwnShopIdOff(), getItemId()) == -1) {
				player.sendMessage(Util.chat("&c Shop não possui saldo suficiente para realizar a transação"));
				return;
			}

			if (InventoriesUtil.checkPlayerInventory(getPlayer(), Util.getItemStack(getItemId()))) {
				player.sendMessage(Util.chat("&eItem encontrado!"));
			} else {
				cancelTrasaction("&cItem não encontrado!");
			}

			if (getPrice() == -1) {
				price = market.getPrice(getItemId());
			} else {
				price = getPrice();
			}

			
			setChest(Util.getChestInLocation(getLoc()));
			InventoriesUtil.trasecItem(getPlayer(), getChest(), Util.getItemStack(getItemId()));
			
			shopOwn = getOwnShopIdOff();

			if (shopOwn.isOnline())
				shopOwnOn = Util.getPlayer(shopOwn);

			double oldBalance = user.getBalance(player.getUniqueId());
			double oldShopBalance = user.getBalance(getShopIdOff().getUniqueId());

			System.out.println(getPlayer());
			System.out.println(getShopIdOff());

			TransactionUtil.sellTranserClient(getPlayer(), oldBalance, getPrice());
			TransactionUtil.buyTranserShop(getShopIdOff(), oldShopBalance, getPrice());

			double balance = user.getBalance(getPlayer().getUniqueId());
			double shopBalance = user.getBalance(getShopIdOff().getUniqueId());

			// System.out.println("--------------->Cliente: " + balance +":"+
			// getPlayer().toString());
			// System.out.println("---------------->Mercado: " + shopBalance +":"+
			// getOwnShopId().toString());

			if (shopOwn.isOnline()) {
				shopOwnOn.sendMessage(Util.chat("&eVoce recebeu &a" + price + " &ede " +getPlayer().getName()));
				getPlayer().sendMessage(Util.chat("&eForam enviados  &a" + price + " &ede sua conta para &a" + shopOwn.getName()));
			}
			player.sendMessage(Util.chat(
					"&eSaldo atual: &a" + balance + " &eSaldo antigo: &a" + oldBalance + "\n&ePreço: &a" + price));
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
