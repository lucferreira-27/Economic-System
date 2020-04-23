package br.com.enxada.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopInventories {
	
	private Player shopOwn;
	private ItemStack item;
	
	public ShopInventories(Player shopOwn, ItemStack item) {
		// TODO Auto-generated constructor stub
		this.shopOwn = shopOwn;
		this.item = item;
	}

	
	public Player getShopOwn() {
		return shopOwn;
	}

	public void setShopOwn(Player shopOwn) {
		this.shopOwn = shopOwn;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
	
}
