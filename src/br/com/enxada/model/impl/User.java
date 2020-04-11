package br.com.enxada.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.enxada.Main;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.model.UserDAO;
import br.com.enxada.util.Util;

public class User implements UserDAO {
	Main plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean playerExist(Player player) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		UUID uuid = player.getUniqueId();
		try {
		st = plugin.getConnection().prepareStatement("SELECT * FROM Players WHERE Id = ? ");
		st.setString(1, uuid.toString());
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
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		try {
		PreparedStatement st = null;
		ResultSet rs = null;
		UUID uuid = player.getUniqueId();
		if(!playerExist(player)) {
			st = plugin.getConnection().prepareStatement("INSERT INTO Players (Id,Name,Balance) VALUES (?,?,?)");
			st.setString(1, uuid.toString());
			st.setString(2, player.getDisplayName());
			st.setDouble(3, 1000.00);
			st.executeUpdate();
			player.sendMessage(Util.chat("&5[Player Adicionado]"));
		}else {
			player.sendMessage(Util.chat("&cERROR: Player j� adicionado"));
		}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void updatePlayer(Player player, double value) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		UUID uuid = player.getUniqueId();
		try {
			if(playerExist(player)) {
				st = plugin.getConnection().prepareStatement("UPDATE Players SET Balance = ? WHERE ID = ?");
				st.setDouble(1, value);
				st.setString(2, uuid.toString());
				st.executeUpdate();
				Bukkit.getPlayer(uuid).sendMessage(Util.chat("&eSaldo atualizado ["+ value +"]"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new  DbException(e.getMessage());
		}
	}

	@Override
	public void deletePlayer(Player player) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		UUID uuid = player.getUniqueId();
		try {
			if(playerExist(player)) {
				st = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.getTablePlayers() +  " WHERE ID = ?");
				st.setString(1, uuid.toString());
				st.executeUpdate();
				Bukkit.broadcastMessage(Util.chat("&5PLAYER &c" +player.getCustomName() +"&5FOI DELETADO"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		
	}



	@Override
	public double getBalance(Player player) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		UUID uuid = player.getUniqueId();
		try {
			if(playerExist(player)) {
			st = plugin.getConnection().prepareStatement("SELECT Balance FROM " + plugin.getTablePlayers() + " WHERE Id = ?");
			st.setString(1, uuid.toString());
			 rs = st.executeQuery();
			 
			 return rs.getDouble("Balance");
			}
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<String> listPlayersNames() {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			st = plugin.getConnection().prepareStatement("Select Name FROM " + plugin.getTablePlayers());
			
			rs = st.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("Name"));
			}
			return list;
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
	
	}

}
