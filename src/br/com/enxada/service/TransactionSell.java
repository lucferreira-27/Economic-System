package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.Util;

public class TransactionSell extends Transaction {
	private Market market = new Market();
	private User user = new User();
	private Player player;
	private UUID playerId; 
	private int itemId;
	
	
	
	public TransactionSell(Sign sign, Player player) {
		super(sign, player);
		// TODO Auto-generated constructor stub
		this.player = player;
	}



	public void doTransaction() {
		// TODO Auto-generated method stub
		if(allowTransaction()) {
			
		}
		player.sendMessage("&cTransação falhou!");

	}


	
	public boolean allowTransaction() {
		// TODO Auto-generated method stub

		if(authenticateItemId() && authenticatePlayerId()) {
			return true;
		}
		
			
		return false;

	}
	public boolean authenticateItemId() {
		if(Util.isNumber(getItemId())) {
			this.itemId = Integer.parseInt(getItemId());
		}else {
			this.itemId = Util.getId(getItemId());
		}
		
		if(market.itemExist(itemId)) {
			return true;
		}
		player.sendMessage(Util.chat("&cItem não pode ser encontrado no mercado"));
		return false;
		
	}
	public boolean authenticatePlayerId() {
		this.playerId =  getPlayer().getUniqueId();
		
		if(user.playerExist(playerId)) {
			return true;
		
		}
		player.sendMessage(Util.chat("&cPlayer não pode ser encontrado no banco de dados"));
		return false;
	}

}
