package br.com.enxada.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import br.com.enxada.Main;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.model.MarketDAO;

public class Market implements MarketDAO {
	Main plugin = Main.getPlugin(Main.class);
	@Override
	public boolean itemExist(int id) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.getTableMarket() + " WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
		
			if(rs.next()) {
				return true;
			}
		
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		
		return false;
	}

	@Override
	public void addItem(int id) {
		// TODO Auto-generated method stub
		if(!itemExist(id)) {
			ItemStack item = new ItemStack(Material.getMaterial(id));
			String name = item.getType().toString();
			double price = 0;
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				
				st  = plugin.getConnection().prepareStatement("INSERT INTO " +  plugin.getTableMarket()  + " (Id,Name,Price) VALUES (?,?,?)");
				st.setInt(1, id);
				st.setString(2, name);
				st.setDouble(3, price);
				st.executeUpdate();
				
			}catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		
		}
	}
	public void addItem(int id,double value) {
		// TODO Auto-generated method stub
		if(!itemExist(id)) {
			ItemStack item = new ItemStack(Material.getMaterial(id));
			String name = item.getType().toString();
			double price = value;
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
			st  = plugin.getConnection().prepareStatement("INSERT INTO " +  plugin.getTableMarket()  + " (Id,Name,Price) VALUES (?,?,?)");
			st.setInt(1, id);
			st.setString(2, name);
			st.setDouble(3, price);
			st.executeUpdate();

			return;
			}catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}
		
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
