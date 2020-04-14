package br.com.enxada.util;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.enxada.commands.Commands;
import br.com.enxada.events.Events;

public class AllLoad extends JavaPlugin {
	public void allLoad() {
		loadCommands();
		loadEvents();
	}
	
	public void loadCommands() {
		Commands cmd = new Commands();
		
		getCommand(cmd.getCmdSaldo()).setExecutor(cmd);
		getCommand(cmd.getCmdAtualizarSaldo()).setExecutor(cmd);
		getCommand(cmd.getCmdDeletarConta()).setExecutor(cmd);
		getCommand(cmd.getCmdAddConta()).setExecutor(cmd);
		getCommand(cmd.getCmdListarPlayers()).setExecutor(cmd);
	}
	
	public void loadEvents() {
		Events event = new Events();
		getServer().getPluginManager().registerEvents((Listener) new Events(), (Plugin) this);
	}
	
}
