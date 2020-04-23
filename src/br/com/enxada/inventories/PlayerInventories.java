package br.com.enxada.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerInventories {
	private Player player;
	private ItemStack item;

	public PlayerInventories(Player player, ItemStack item) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.item = item;
	}
	
	
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
	

	
}
