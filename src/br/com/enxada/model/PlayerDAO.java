package br.com.enxada.model;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public interface PlayerDAO {
	boolean playerExist(UUID uuid);
	
	void addPlayer(Player player);
	void updatePlayer(UUID uuid, double value);
	void deletePlayer(UUID uuid);
	
	Player findPlayer(UUID uuid);
	
	double getBalance();
	
	List<Player> listPlayers();
	
	
	
}
