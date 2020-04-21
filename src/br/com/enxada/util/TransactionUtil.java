package br.com.enxada.util;

import org.bukkit.entity.Player;

import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;


public class TransactionUtil {
	private static User user = new User();
	private static Market market = new Market();
	
	public static void buyTranserShop(Player shop, double shopBalance,double itemPrice) {
		user.updatePlayer(shop, shopBalance - itemPrice); 
	}
	public static void sellTranserShop(Player shop, double shopBalance,double itemPrice) {
		user.updatePlayer(shop, shopBalance + itemPrice); 
	}
	
	public static void buyTranserClient(Player client, double clientBalance,double itemPrice) {
		user.updatePlayer(client, clientBalance - itemPrice); 
	}
	public static void sellTranserClient(Player client, double clientBalance,double itemPrice) {
		user.updatePlayer(client, clientBalance + itemPrice); 
	}
}
