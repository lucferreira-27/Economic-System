package br.com.enxada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.enxada.events.Events;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.util.Util;

public class Main extends JavaPlugin implements Listener {
	private Connection connection;
	private String host, database, username, password;
	private String port;
	private String tablePlayers, tableMarket;
	

	
	@Override
	public void onEnable() {
		
		Events event = new Events();
		getServer().getPluginManager().registerEvents((Listener) new Events(), (Plugin) this);
		loadDatabase();
		
		System.out.println(Util.chat("&8Plugin Economic System - Online"));
		Bukkit.broadcastMessage(Util.chat("&2Plugin Economic System  - Online"));
	}
	
	@Override
	public void onDisable() {
		System.out.println(Util.chat("&4Plugin Economic System - Offline"));
		
		
		
		Bukkit.broadcastMessage(Util.chat("&4Plugin Economic System - Offline"));
	}
	public void loadConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();

		host = this.getConfig().getString("host");
		port = this.getConfig().getString("port");
		database = this.getConfig().getString("database");
		username = this.getConfig().getString("username");
		password = this.getConfig().getString("password");
		
		tablePlayers = this.getConfig().getString("tablePlayers");
		tableMarket = this.getConfig().getString("tableMarket");

	}
	
	public void loadDatabase() {
		loadConfig();
		try {
			synchronized (this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			setConnection(DriverManager.getConnection(this.host +":"+this.port+"/"+this.database, this.username, this.password));
			

			Bukkit.broadcastMessage(Util.chat("&5Economic System Database - ONLINE "));
			System.out.println(Util.chat("&5Economic System Database - ONLINE "));
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
}
