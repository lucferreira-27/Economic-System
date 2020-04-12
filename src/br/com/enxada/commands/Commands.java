package br.com.enxada.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

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
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
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
				if(args.length == 1 && !(args[0] instanceof String) ) {
					value = Double.parseDouble(args[0]);
					
					user.updatePlayer(player, value);
					player.sendMessage(Util.chat("&eSaldo atualizado com sucesso!"));
					return true;
				}
				if(args.length == 2 && Bukkit.getPlayerExact(args[0]) != null) {

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
				
					
					if(Bukkit.getPlayerExact(args[0]) != null) {
						playerOnTarget = Bukkit.getPlayerExact(args[0]);
						user.deletePlayer(playerOnTarget);
						return true;
					}
					if(Bukkit.getOfflinePlayer(args[0]) != null) {
					
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
	

}
