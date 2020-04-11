package br.com.enxada.model;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public interface MarketDAO {
	boolean itemExist();
	
	void addItem(int id);
	void deleteItem(int id);
	void updateItem(int id,String name, double value);
	
	ItemStack findItem();
	
	double getPrice();
	
	List<ItemStack> listItens();
	
	
}
