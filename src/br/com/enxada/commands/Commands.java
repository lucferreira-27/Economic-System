package br.com.enxada.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import br.com.enxada.exceptions.DbException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;
import br.com.enxada.util.Util;
import net.minecraft.server.v1_8_R3.CommandExecute;


public class Commands extends CommandExecute implements CommandExecutor, Listener {
	
	//User - Default  commands
	private String cmdSaldo = "saldo";
	//User = Developer commands
	private String cmdAddConta = "addconta";
	private String cmdAtualizarSaldo = "atualizarsaldo";
	private String cmdDeletarConta = "deletarconta";
	private String cmdListarPlayers = "listarplayers";
	
	//Market- Default  commands	
	private String cmdPreço = "preço";
	
	//Market = Developer commands
	private String cmdAddItem = "additem";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		Market market = new Market();
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase(cmdSaldo)) {
			
			double value = 0;
			
			if(args.length == 0) {
				
				value = user.getBalance(player);
				player.sendMessage(Util.chat("&eSaldo: &a" +  String.valueOf(value)));
				
				return true;
			
			}else if(Bukkit.getPlayer(args[0]) != null){
				
				Player playerTarget = Bukkit.getPlayer(args[0]);
				
				value = user.getBalance(playerTarget);
				if(sender instanceof Player) {
					player.sendMessage(Util.chat("&eSaldo: &a" +  String.valueOf(value)));
				}else
					Bukkit.broadcastMessage("Saldo: " +  String.valueOf(value));
				return true;
			}
			
			player.sendMessage(Util.chat("&cO Player não pode ser encontrado."));
			return true;
			
		}
		if(cmd.getName().equalsIgnoreCase(cmdAtualizarSaldo)) {
			if(args.length != 0) {
				double value = 0;
				Player playerTarget;
				if(args.length == 1 && (Bukkit.getPlayer(args[0]) == null) ) {
					value = Double.parseDouble(args[0]);
					
					user.updatePlayer(player, value);
					player.sendMessage(Util.chat("&eSaldo atualizado com sucesso!"));
					return true;
				}
				if(args.length == 2 && user.playerExist(Bukkit.getPlayer(args[0]).getUniqueId())) {

					playerTarget =  Bukkit.getPlayerExact(args[0]);
					value = Double.parseDouble(args[1]);
					user.updatePlayer(playerTarget, value);
					player.sendMessage(Util.chat("&eSaldo atualizado com sucesso!"));
					return true;

				}else {
					
					player.sendMessage(Util.chat("&cSem parâmetros (player, valor )"));
					return true;
				}
			}else {
				player.sendMessage(Util.chat("&cSem parâmetros (valor)"));
				return true;
			}
		}if(cmd.getName().equalsIgnoreCase(cmdDeletarConta)) {
			if(args.length != 0) {
				
				Player playerOnTarget = null;
				OfflinePlayer playerOffTarget = null;
				
					
					if(user.playerExist(Bukkit.getPlayer(args[0]).getUniqueId())) {
						
						playerOnTarget = Bukkit.getPlayerExact(args[0]);
						user.deletePlayer(playerOnTarget);
						return true;
					}
					if(user.playerExist(Bukkit.getOfflinePlayer(args[0]).getUniqueId())) {
					
						playerOffTarget = Bukkit.getOfflinePlayer(args[0]);
						user.deletePlayer(playerOffTarget);
						return true;
					}
					player.sendMessage(Util.chat("&cNão foi possivel encontrar o Player"));
					return true;
				
					
			}
			player.sendMessage(Util.chat("&cSem parâmetros (Nome do player)"));
			return true;

		}
		if(cmd.getName().equalsIgnoreCase(cmdAddConta)) {
			if(args.length != 0) {
				if(Bukkit.getPlayer(args[0]) != null) {
					
					Player playerTarget = Bukkit.getPlayerExact(args[0]);
					user.addPlayer(playerTarget);
					player.sendMessage(Util.chat("&a"+ playerTarget.getDisplayName()+ " &eadicionado!"));
					return true;
					
				}else {
					player.sendMessage(Util.chat("&cPlayer não está online ou não seu nome foi informado incorretamente"));
					return true;
				}
			}
			player.sendMessage(Util.chat("&cSem parâmetros (Nome do player)"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase(cmdListarPlayers)) {
			
			List<Player> listPlayer = user.listPlayers();
			
			
			for(Player p : listPlayer) {
				double balance = user.getBalance(p);
				String name = p.getDisplayName();
				player.sendMessage(Util.chat("-> &eNome: &a" + name + " &eBalance: &a" + balance));
			}
		
			return true;
		}
		if(cmd.getName().equalsIgnoreCase(cmdAddItem)) {
			
			
			
			if(args.length == 1) {
				int id = 0;
	
				if(Util.isNumber(args[0])) {
					try {
						id = Integer.parseInt(args[0]);
						market.addItem(id);
					}catch (NullPointerException e) {
						// TODO: handle exception
						player.sendMessage(Util.chat("&cSem parâmetros: /additem [id] ou <nome do item>"));
						return true;
					}
				}else  {
					
					try {
						ItemStack item = new ItemStack(Material.getMaterial(args[0]));
						id = item.getTypeId();
						market.addItem(id);
					}catch (NullPointerException e) {
						// TODO: handle exception
						player.sendMessage(Util.chat("&cSem parâmetros: /additem [id] ou <nome do item>"));
						return true;
					}
				}
				
				
				ItemStack item = new ItemStack(Material.getMaterial(id));
				String itemName = item.getType().toString();
				
				player.sendMessage(Util.chat("&eItem &c[&f"+ id +"&c] &9"+ itemName + " &aAdicionado!"));
				return true;
			}
			if(args.length == 2) {
				int id  = 0;
				double price = 0;
				if(Util.isNumber(args[1])) {
					if(Util.isNumber(args[0])) { 
						id = Integer.parseInt(args[0]);
						price = Double.parseDouble(args[1]);
						
						market.addItem(id, price);
					
						player.sendMessage(Util.chat("&eItem &c[&f"+ id +"&c] &9  &aAdicionado!"));
						return true;
					}
					else {
						try {
						
							ItemStack item = new ItemStack(Material.getMaterial(args[0]));
							id = item.getTypeId();
							price = Double.parseDouble(args[1]);
							
							market.addItem(id, price);
						
							player.sendMessage(Util.chat("&eItem &c[&f"+ id +"&c] &9  &aAdicionado!"));
							return true;
						}
						catch (NullPointerException e) {
							// TODO: handle exception
							player.sendMessage(Util.chat("&cParâmetros inválidos: /additem [id] ou <nome do item> [preço]"));
							return true;
						}
					}
				
					
					
				}
				player.sendMessage(Util.chat("&cParâmetros inválidos: /additem [id] ou <nome do item> [preço]"));
				return true;
			}



		}
		return false;
	}

	public String getCmdSaldo() {
		return cmdSaldo;
	}

	public void setCmdSaldo(String cmdSaldo) {
		this.cmdSaldo = cmdSaldo;
	}

	public String getCmdAddConta() {
		return cmdAddConta;
	}

	public void setCmdAddConta(String cmdAddConta) {
		this.cmdAddConta = cmdAddConta;
	}

	public String getCmdAtualizarSaldo() {
		return cmdAtualizarSaldo;
	}

	public void setCmdAtualizarSaldo(String cmdAtualizarSaldo) {
		this.cmdAtualizarSaldo = cmdAtualizarSaldo;
	}

	public String getCmdDeletarConta() {
		return cmdDeletarConta;
	}

	public void setCmdDeletarConta(String cmdDeletarConta) {
		this.cmdDeletarConta = cmdDeletarConta;
	}

	public String getCmdListarPlayers() {
		return cmdListarPlayers;
	}

	public void setCmdListarPlayers(String cmdListarPlayers) {
		this.cmdListarPlayers = cmdListarPlayers;
	}

	public String getCmdPreço() {
		return cmdPreço;
	}

	public void setCmdPreço(String cmdPreço) {
		this.cmdPreço = cmdPreço;
	}

	public String getCmdAddItem() {
		return cmdAddItem;
	}

	public void setCmdAddItem(String cmdAddItem) {
		this.cmdAddItem = cmdAddItem;
	}
	

}
