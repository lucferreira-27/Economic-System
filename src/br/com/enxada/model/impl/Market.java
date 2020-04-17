package br.com.enxada.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import br.com.enxada.Main;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.model.MarketDAO;
import br.com.enxada.util.Util;

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
		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			st = plugin.getConnection().prepareStatement("DELETE FROM " +  plugin.getTableMarket() + " WHERE ID = ?" );
			st.setInt(1, id);
			st.executeUpdate();
			return;
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void updateItem(int id, double value) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = plugin.getConnection().prepareStatement("UPDATE " + plugin.getTableMarket() + " SET Price = ? WHERE ID = ?");
			st.setDouble(1, value);
			st.setInt(2, id);
			
			st.executeUpdate();
			return;
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public ItemStack findItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice(int id) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			if(itemExist(id)) {
				st = plugin.getConnection().prepareStatement("Select * FROM " +plugin.getTableMarket() + " WHERE ID = ?");
				st.setInt(1, id);
				
				rs = st.executeQuery();
				rs.next();
				double price =  rs.getDouble("Price");
				
				return price;
				
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<ItemStack> listItens() {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ItemStack> listItens = new ArrayList<>();
		try {
			st = plugin.getConnection().prepareStatement("SELECT Name FROM " +plugin.getTableMarket());
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("Name");

				listItens.add(Util.getItemStack(name));
			}
			
			return listItens;
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		
	}

}
