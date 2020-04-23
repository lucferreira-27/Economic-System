package br.com.enxada.util;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import br.com.enxada.exceptions.EconomicException;

public class InventoriesUtil {
	public static boolean checkPlayerInventory(Player player, ItemStack item) {
		PlayerInventory pInveotory =  player.getInventory();
		
		pInveotory.getItemInHand();
		
		for(int i = 0; i < pInveotory.getSize(); i++) {
			ItemStack itemsCheck = pInveotory.getItem(i);
			
			if(itemsCheck != null) 
				if(itemsCheck.getData().equals(item.getData())) {
					return true;
				}
			
		}
		
		return false;
	
	}
	public static Inventory getChestInventory(Chest chest) {
		System.out.println("!"+chest);
		if(chest instanceof Chest) {

			return chest.getInventory();
		}
		return null;
	}
	public static int getSlotItemInventory(Player player, ItemStack item) {
		
		
		PlayerInventory pInveotory =  player.getInventory();
		
		pInveotory.getItemInHand();
		
		if(checkPlayerInventory(player, item))
			for(int i = 0; i < pInveotory.getSize(); i++) {
				ItemStack itemsCheck = pInveotory.getItem(i);
				
				if(itemsCheck != null) 
					if(itemsCheck.getData().equals(item.getData())) {
						return i;
					}
				
			}
		
		return 0;
	
	}
	public static ItemStack getItemInSlot(Player player, int slot) {
		PlayerInventory pInveotory =  player.getInventory();
		
		pInveotory.getItemInHand();
		
		ItemStack item = pInveotory.getItem(slot);
		
		if(item != null)
			return item;
		
		return null;
	}
	
	public static void trasecItem(Chest chest, Player player1,  ItemStack item1) {
		
		//Transferir item de player1 para player2
		System.out.println("entrou!");
		
			PlayerInventory pInveotory =  player1.getInventory();
			Inventory chestInventory = getChestInventory(chest);
		if(checkShopInventory(chestInventory, item1)) {
			System.out.println(pInveotory);
			System.out.println(chestInventory);
			
			chestInventory.removeItem(item1);
				
			pInveotory.addItem(item1);
			player1.updateInventory();
			return;
		}
		else
			throw new EconomicException("Estoque vazio!",player1);
	}
	
	public static void trasecItem(Player player1, Chest chest, ItemStack item1) {
		
		//Transferir item de player1 para player2
		
		if(checkPlayerInventory(player1, item1)) {
			PlayerInventory pInveotory =  player1.getInventory();
			Inventory chestInventory = getChestInventory(chest);
			System.out.println(pInveotory);
			System.out.println(chestInventory);
			pInveotory.removeItem(item1);
	
			chestInventory.addItem(item1);
			player1.updateInventory();
					
		}
	}
	public static void trasecItem(OfflinePlayer player1, OfflinePlayer player2, ItemStack item1) {
		
		//Transferir item de player1 para player2
		Player p1 = Util.getPlayer(player1);
		Player p2 =Util.getPlayer(player2);
		if(checkPlayerInventory(p1, item1)) {
			PlayerInventory pInveotory1 =  p1.getInventory();
			PlayerInventory pInveotory2 =  p2.getInventory();
			Inventory chestInventory;
			


			
			pInveotory1.removeItem(item1);
			pInveotory2.addItem(item1);
			
			p1.updateInventory();
			p2.updateInventory();
			//player2.updateInventory();
		
		}
	}
	
	public static boolean checkShopInventory(Inventory shopInveotory, ItemStack item1) {
		
		
		
		for(ItemStack item : shopInveotory.getContents()) {
			if(item != null) {
				System.out.println("---Item1: " + item1 + "---Item:"+item);
				if(item.getTypeId() == item1.getTypeId()) {
					
					return true;
				}
			}
		}
		
		return false;
	
	}
	
	
	
	public static Inventory findChestInventory(Location loc) {
		
		Block block = loc.getBlock();
		BlockState state = block.getState();
		
		if(state instanceof Chest) {
			Chest chest = (Chest) state;
			return chest.getBlockInventory();
		}
		return null;
	}
}
