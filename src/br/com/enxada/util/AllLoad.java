package br.com.enxada.util;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.enxada.commands.Commands;
import br.com.enxada.commands.CommandsMarket;
import br.com.enxada.commands.CommandsUser;
import br.com.enxada.events.EventsJoin;

public class AllLoad extends JavaPlugin {
	public void allLoad() {
		loadCommands();
		loadEvents();
	}
	
	public void loadCommands() {

		CommandsUser cmdUser = new CommandsUser();
		CommandsMarket cmdMarket = new CommandsMarket();
		
		getCommand(cmdUser.getCmdSaldo()).setExecutor(cmdUser);
		getCommand(cmdUser.getCmdAtualizarSaldo()).setExecutor(cmdUser);
		getCommand(cmdUser.getCmdDeletarConta()).setExecutor(cmdUser);
		getCommand(cmdUser.getCmdAddConta()).setExecutor(cmdUser);
		getCommand(cmdUser.getCmdListarPlayers()).setExecutor(cmdUser);
		
		getCommand(cmdMarket.getCmdAddItem()).setExecutor(cmdMarket);
	}
	
	public void loadEvents() {
		EventsJoin event = new EventsJoin();
		getServer().getPluginManager().registerEvents((Listener) new EventsJoin(), (Plugin) this);
	}
	
}
