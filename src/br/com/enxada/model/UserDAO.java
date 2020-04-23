package br.com.enxada.model;

import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface UserDAO {
	boolean playerExist(UUID uuid);
	
	void addPlayer(UUID uuid);
	void updatePlayer(UUID uuid, double value);
	void deletePlayer(UUID uuid);

	

	
	double getBalance(UUID uuid);
	
	List<String> listPlayersNames();
	
	
	
}
