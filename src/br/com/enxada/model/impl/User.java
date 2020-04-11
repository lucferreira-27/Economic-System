package br.com.enxada.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.enxada.Main;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.model.PlayerDAO;
import br.com.enxada.util.Util;

public class User implements PlayerDAO {
	Main plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean playerExist(UUID uuid) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
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
		if(!playerExist(uuid)) {
			st = plugin.getConnection().prepareStatement("INSERT INTO Players (Id,Name,Balance) VALUES (?,?,?)");
			st.setString(1, uuid.toString());
			st.setString(2, player.getDisplayName());
			st.setDouble(3, 1000.00);
			st.executeUpdate();
			Bukkit.broadcastMessage(Util.chat("&5 [Player Adicionado]"));
		}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void updatePlayer(UUID uuid, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePlayer(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public org.bukkit.entity.Player findPlayer(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<org.bukkit.entity.Player> listPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

}
