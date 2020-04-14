package br.com.enxada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.enxada.commands.Commands;
import br.com.enxada.events.Events;
import br.com.enxada.exceptions.DbException;
import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.util.Util;

public class Main extends JavaPlugin implements Listener {
	private Connection connection;
	private String host, database, username, password;
	private String port;
	private String tablePlayers, tableMarket;
	private String dbURL = "";
	

	
	@Override
	public void onEnable() {
		
		Events event = new Events();
		getServer().getPluginManager().registerEvents((Listener) new Events(), (Plugin) this);
		
		Commands cmd = new Commands();
		
		getCommand(cmd.getCmdSaldo()).setExecutor(cmd);
		getCommand(cmd.getCmdAtualizarSaldo()).setExecutor(cmd);
		getCommand(cmd.getCmdDeletarConta()).setExecutor(cmd);
		getCommand(cmd.getCmdAddConta()).setExecutor(cmd);
		getCommand(cmd.getCmdListarPlayers()).setExecutor(cmd);
		
		getCommand(cmd.getCmdAddItem()).setExecutor(cmd);
		System.out.println(Util.chat("Plugin Economic System - Online"));

		if(!loadDatabase())
			onDisable();
	}
	
	@Override
	public void onDisable() {
		System.out.println(Util.chat("Plugin Economic System - Offline"));
		
		
		Util.clearChat();
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
	
	public boolean loadDatabase() {
		loadConfig();
	    
		this.dbURL += "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?characterEncoding=utf8"; 
		
	    try {
			synchronized (this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return false;
				}
			}
			//setConnection(DriverManager.getConnection(this.host +":"+this.port+"/"+this.database, this.username, this.password));
			
			Class.forName("com.mysql.jdbc.Driver");
			setConnection(DriverManager.getConnection(dbURL,this.username,this.password));

			Bukkit.broadcastMessage(Util.chat("&5Economic System Database - ONLINE "));
			System.out.println(Util.chat("Economic System Database - ONLINE "));
			return true;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e.getMessage());
			
		}
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new EconomicException(e.getMessage());
		}
		
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getHost() {
		return host;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPort() {
		return port;
	}

	public String getTablePlayers() {
		return tablePlayers;
	}

	public String getTableMarket() {
		return tableMarket;
	}
	
	
}
