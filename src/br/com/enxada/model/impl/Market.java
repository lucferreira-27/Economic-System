package br.com.enxada.model.impl;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import br.com.enxada.model.MarketDAO;

public class Market implements MarketDAO {

	@Override
	public boolean itemExist() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItem(int id, String name, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack findItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItemStack> listItens() {
		// TODO Auto-generated method stub
		return null;
	}

}
