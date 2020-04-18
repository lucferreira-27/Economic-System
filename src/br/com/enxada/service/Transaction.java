package br.com.enxada.service;

import java.util.UUID;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.Util;

public class Transaction {
	
	private Market market = new Market();
	private User user = new User();
	
	private Sign sign = null;
	private String transactionType = null;
	private Player player;
	private UUID playerId;
	private int itemId = 0;
	private String itemIdString = null;
	private double price = 0;
	
	public Transaction(Sign sign, Player player) {
		// TODO Auto-generated constructor stub
		this.sign = getSign();
		this.transactionType = sign.getLine(0);
		this.itemIdString = sign.getLine(1);
		this.player = player;
		this.price = Util.thisPrice(sign, player);
	}

	public boolean allowTransaction() {
		// TODO Auto-generated method stub

		if (authenticateItemId() && authenticatePlayerId()) {
			return true;
		}

		return false;

	}

	public boolean authenticateItemId() {
		if (Util.isNumber(getItemIdString())) {
			this.itemId = Integer.parseInt(getItemIdString());
		} else {
			this.itemId = Util.getId(getItemIdString());
		}

		if (market.itemExist(itemId)) {
			System.out.println(itemId);
			return true;
		}
		player.sendMessage(Util.chat("&cItem n�o pode ser encontrado no mercado"));
		return false;

	}

	public boolean authenticatePlayerId() {

		
		this.playerId = getPlayer().getUniqueId();

		if (user.playerExist(playerId)) {
			return true;

		}
		player.sendMessage(Util.chat("&cPlayer n�o pode ser encontrado no banco de dados"));
		return false;
	}
	
	protected int checkBalance(Player player, int itemId) {
		double balance = user.getBalance(getPlayer());
		double price =  market.getPrice(itemId);
		System.out.println("\n"+balance);
		System.out.println("\n"+price);
		if(balance == price) {
			return 0;
		}
		if(balance > price) {
			return 1;
		}
		if(balance < price) {
			return -1;
		}
		
		throw new EconomicException("Erro ao tentar acessar saldo ou/e pre�o");
	}
	
	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemIdString() {
		return itemIdString;
	}

	public void setItemIdString(String itemIdString) {
		this.itemIdString = itemIdString;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	
	
}
