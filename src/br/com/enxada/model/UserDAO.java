package br.com.enxada.model;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public interface UserDAO {
	boolean playerExist(Player player);
	
	void addPlayer(Player player);
	void updatePlayer(Player player, double value);
	void deletePlayer(Player player);
	

	
	double getBalance(Player player);
	
	List<String> listPlayersNames();
	
	
	
}
