package br.com.enxada.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import br.com.enxada.model.impl.Market;
import br.com.enxada.util.Util;
import net.minecraft.server.v1_8_R3.CommandExecute;

public class CommandsMarket extends CommandExecute implements CommandExecutor, Listener {
	//Market- Default  commands	
	private String cmdPreço = "preço";
	
	//Market = Developer commands
	private String cmdPreco = "preço";
	private String cmdAddItem = "additem";
	private String cmdRemoveItem = "removeritem";
	private String cmdAtualizarItem = "atualizaritem";
	private String cmdListarItens = "listaritems";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		Market market = new Market();
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase(cmdPreco)) {
			if(args.length == 1) {
				int id = Util.getId(args[0]);
				if(id != 0) {
					
					double price = market.getPrice(id);
					String name = Util.getItemName(id);
					player.sendMessage(Util.chat("&eItem: &9" + name + "\n&ePreço: &a" + price));
					return true;
				}
				player.sendMessage(Util.chat("&cParâmetros inválidos: /preço [id] ou <nome do item>"));
				return true;
			}
			player.sendMessage(Util.chat("&cSem parâmetro: /preço [id] ou <nome do item>"));
			return true;
		
			
		}
		
		
		if(cmd.getName().equalsIgnoreCase(cmdAddItem)) {
	
			
			if(args.length == 1) {
				
					int id  = Util.getId(args[0]);
					if(id != 0) {
						if(!market.itemExist(id)) {
							market.addItem(id);
							player.sendMessage(Util.chat("&eItem &c[&f"+ id +"&c] &9"+ Util.getItemName(id) +" &aAdicionado!"));
							return true;
						}else {
							player.sendMessage(Util.chat("&cItem já está registrado!"));
							return true;
						}
					}
					player.sendMessage(Util.chat("&cParâmetros inválidos: /additem [id] ou <nome do item>"));
					return true;
	
			}

					
			
			if(args.length == 2) {
				if(Util.isNumber(args[1])) {
					int id = Util.getId(args[0]);
					double price = Double.parseDouble(args[1]);
					if(id != 0) {
						if(!market.itemExist(id)) {
							market.addItem(id,price);
							player.sendMessage(Util.chat("&eItem &c[&f"+ id +"&c] &9"+ Util.getItemName(id) +" &aAdicionado! \n&ePreço: &f" + price));
							return true;
						}else {
							player.sendMessage(Util.chat("&cItem já está registrado!"));
							return true;
						}
					}else {
						player.sendMessage(Util.chat("&cParâmetros inválidos: /additem [id] ou <nome do item> <preço>"));
						return true;
					}
				}
				player.sendMessage(Util.chat("&cParâmetros inválidos: /additem [id] ou <nome do item> <preço>"));
				return true;
			}
			
			player.sendMessage(Util.chat("&cSem parâmetros: /additem [id] ou <nome do item> <preço>"));
			return true;


		}
		
		if(cmd.getName().equalsIgnoreCase(cmdRemoveItem)) {
			if(args.length == 1) {
				
				int id = Util.getId(args[0]);
				
				if(id != 0 ) {
					if(market.itemExist(id)) {
						market.deleteItem(id);
						player.sendMessage(Util.chat("&eItem &9" + Util.getItemName(id) + "&c Deletado"));
						return true;
					}else
						player.sendMessage(Util.chat("&cEsse item ainda não foi registrado"));
					return true;
				}
				
				player.sendMessage(Util.chat("&cParâmetros inválidos: /removeritem [id] ou <nome do item>"));
				return true;

			}
			player.sendMessage(Util.chat("&cSem parâmetros: /removeritem [id] ou <nome do item>"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase(cmdAtualizarItem)) {
			
			if(args.length == 2) {
				
				if(Util.isNumber(args[1])) {
					
				
					int id = Util.getId(args[0]);
					if(id != 0 ) {
						if(market.itemExist(id)) {
							
							double value = Double.parseDouble(args[1]);
							market.updateItem(id, value);
							player.sendMessage(Util.chat("&eItem &9" + Util.getItemName(id) + "&a Atualizado\n&ePreço atual: &f" + value));
							return true;
						
						}else {
							player.sendMessage(Util.chat("&cEsse item ainda não foi registrado"));
							return true;
						}
	
					}
					player.sendMessage(Util.chat("&cParâmetros inválidos: /atualizaritem [id] ou <nome do item> <preço>"));
					return true;
			}
				player.sendMessage(Util.chat("&cParâmetros inválidos: /atualizaritem [id] ou <nome do item> <preço>"));
				return true;
			}
			
			player.sendMessage(Util.chat("&cSem parâmetros: /atualizaritem [id] ou <nome do item> <preço>"));
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase(cmdListarItens)) {
			List<ItemStack> listItem = new ArrayList<ItemStack>();
		
			
			player.sendMessage(Util.chat("&cCARREGANDO LISTA......"));
			
			listItem = market.listItens();
			
			Map map = Util.divList(listItem, 5);
			
			if(args.length == 0) {

				
				player.sendMessage(Util.chat("&f----------&6[Lista]&7 (1/" +Util.mapKeyQuantity(map) +")&f--------------------"
				+"\n&7Use /listaritems [n] para ir para pagina n \n&f----&9NOME &f----&aPREÇO &f----&cID"));


				listItem = (List<ItemStack>) map.get(1);
				
				
				for(ItemStack item : listItem) {
					
					String name  = Util.getItemName(item);
					int id = Util.getItemId(item);
					double price = market.getPrice(id);
					
					player.sendMessage(Util.chat("-> &9["+name+"] &a["+price+"] &c["+id+"]"));
				}
			return true;
		}
			if(args.length == 1) {
				
				
				int p = Integer.parseInt(args[0]);
				if(p <= 0) {
					  player.sendMessage(Util.chat("&cNúmero minímo de paginas é: "+ 1));
					  return true;
				}
				if(p <= Util.mapKeyQuantity(map)) {
					listItem = (List<ItemStack>) map.get(p - 1);
					
					player.sendMessage(Util.chat("&f----------&6[Lista]&7 ("+args[0]+"/" +Util.mapKeyQuantity(map) 
					+"&f--------------------\n&f----&9NOME &f----&aPREÇO &f----&cID"));
					
					
					for(ItemStack item : listItem) {
						
						String name  = Util.getItemName(item);
						int id = Util.getItemId(item);
						double price = market.getPrice(id);
						
						player.sendMessage(Util.chat("-> &9["+name+"] &a["+price+"] &c["+id+"]"));
					}
					return true;
				}
			  player.sendMessage(Util.chat("&cNúmero maximo de paginas é: " + Util.mapKeyQuantity(map)));
			  return true;
			}
		
			return true;
		}
		return false;
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
	public String getCmdPreco() {
		return cmdPreco;
	}
	public void setCmdPreco(String cmdPreco) {
		this.cmdPreco = cmdPreco;
	}
	public String getCmdRemoveItem() {
		return cmdRemoveItem;
	}
	public void setCmdRemoveItem(String cmdRemoveItem) {
		this.cmdRemoveItem = cmdRemoveItem;
	}
	public String getCmdAtualizaritem() {
		return cmdAtualizarItem;
	}
	public void setCmdAtualizaritem(String cmdatualizaritem) {
		this.cmdAtualizarItem = cmdatualizaritem;
	}
	public String getCmdAtualizarItem() {
		return cmdAtualizarItem;
	}
	public void setCmdAtualizarItem(String cmdAtualizarItem) {
		this.cmdAtualizarItem = cmdAtualizarItem;
	}
	public String getCmdListarItens() {
		return cmdListarItens;
	}
	public void setCmdListarItens(String cmdListarItens) {
		this.cmdListarItens = cmdListarItens;
	}

	

}
