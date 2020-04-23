package br.com.enxada.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;


public class TransactionUtil {
	private static User user = new User();
	private static Market market = new Market();
	
	public static void buyTranserShop(OfflinePlayer shop, double shopBalance,double itemPrice) {
		user.updatePlayer(shop.getUniqueId(), shopBalance - itemPrice); 
	}
	public static void sellTranserShop(OfflinePlayer shop, double shopBalance,double itemPrice) {
		user.updatePlayer(shop.getUniqueId(), shopBalance + itemPrice); 
	}
	
	public static void buyTranserClient(Player client, double clientBalance,double itemPrice) {
		user.updatePlayer(client.getUniqueId(), clientBalance - itemPrice); 
	}
	public static void sellTranserClient(Player client, double clientBalance,double itemPrice) {
		user.updatePlayer(client.getUniqueId(), clientBalance + itemPrice); 
	}
	
	
}
