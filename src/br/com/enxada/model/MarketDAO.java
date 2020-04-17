package br.com.enxada.model;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public interface MarketDAO {
	boolean itemExist(int id);
	
	void addItem(int id);
	void addItem(int id,double price);
	void deleteItem(int id);
	void updateItem(int id, double value);
	
	ItemStack findItem();
	
	double getPrice(int id);
	
	List<ItemStack> listItens();
	
	
}
