package br.com.enxada.service;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class Transaction {
	private Sign sign = null;
	private String transactionType = null;
	private Player player;
	private String itemId = null;
	private double price = 0;
	
	public Transaction(Sign sign, Player player) {
		// TODO Auto-generated constructor stub
		this.sign = getSign();
		this.transactionType = sign.getLine(0);
		this.itemId = sign.getLine(1);
		this.player = player;
		this.price = Double.parseDouble(sign.getLine(2));
	}


	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public Sign getSign() {
		return sign;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
