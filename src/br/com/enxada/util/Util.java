package br.com.enxada.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.enxada.Main;
import br.com.enxada.exceptions.EconomicException;
import br.com.enxada.model.impl.Market;
import br.com.enxada.model.impl.User;

public class Util {
	private static Main plugin = Main.getPlugin(Main.class);
	
	private static User user = new User();
	private static Market market = new Market();
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
//		&0 Black
//		&1 Dark Blue
//		&2 Dark Green
//		&3 Dark Aqua
//		&4 Dark Red
//		&5 Purple
//		&6 Gold
//		&7 Gray
//		&8 Dark Gray
//		&9 Indigo
//		&a Bright Green
//		&b Aqua
//		&c Red
//		&d Pink
//		&e Yellow
//		&f White
	}

	public static void clearChat() {
		for (int i = 0; i < 250; i++)
			plugin.getServer().broadcastMessage("");

		plugin.getServer().broadcastMessage(chat("&6--------- CHAT APAGADO! ---------"));
	}

	public static OfflinePlayer fromUUID(UUID uuid) {
		System.out.println("tesdada");
	

			return plugin.getServer().getOfflinePlayer(uuid);


	}

	public static boolean isNumber(String c) {

		for (int i = 0; i < c.length(); i++) {

			if (Character.isDigit(c.charAt(i))) {
				continue;
			}

			return false;

		}

		return true;
	}

	public static boolean divEqualsDouInt(int n1, int n2) {

		int div1 = n1 / n2;
		System.out.println(div1);
		double div2 = (double) n1 / n2;
		System.out.println(div2);

		if (div1 == div2)
			return true;

		return false;
	}

	@SuppressWarnings("unused")

	public static ItemStack getItemStack(String name) {
		ItemStack item = new ItemStack(Material.getMaterial(name));
		return item;
	}

	public static ItemStack getItemStack(int id) {
		ItemStack item = new ItemStack(Material.getMaterial(id));
		return item;
	}

	public static List<ItemStack> getItemStack(List<String> listName) {
		List<ItemStack> listItemStack = new ArrayList<>();
		;
		for (String name : listName) {

			if (isItem(name)) {
				ItemStack item = new ItemStack(Material.getMaterial(name));
				listItemStack.add(item);
			} else {
				throw new NullPointerException();
			}
		}
		return listItemStack;
	}

	public static boolean isItem(int id) {
		try {
			ItemStack item = new ItemStack(Material.getMaterial(id));

			if (item.getType() != Material.AIR) {
				System.out.println(item.getType().toString());
				return true;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}

		return false;

	}

	public static boolean isItem(String name) {
		try {
			ItemStack item = new ItemStack(Material.getMaterial(name));
			return true;
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}

	}
	
	public static boolean isRegistredItem(int id) {
		if(market.itemExist(id))
			return true;
		return false;
	}
	public static boolean isRegistredItem(String name) {
		if(market.itemExist(Util.getId(name)))
			return true;
		return false;
	}
	public static boolean isRegistredItem(ItemStack item) {
		if(market.itemExist(Util.getId(item)))
			return true;
		return false;
	}
	
	public static String getItemName(int id) {

		ItemStack item = new ItemStack(Material.getMaterial(id));
		return item.getType().toString();
	}

	public static int getItemId(String name) {
		try {
			ItemStack item = new ItemStack(Material.getMaterial(name));
			return item.getTypeId();
		} catch (NullPointerException e) {
			// TODO: handle exception
			return 0;
		}

	}
	
	public static String getItemName(ItemStack item) {
		String name = item.getType().toString();
		return name;
	}

	public static int getItemId(ItemStack item) {
		try {

			return item.getTypeId();

		} catch (NullPointerException e) {
			// TODO: handle exception
			return 0;
		}

	}

	public static int getId(String args) {
		String arg = args.toUpperCase();
		int number = 0;

		if (isNumber(arg)) {
			number = Integer.parseInt(arg);
			if (isItem(number))
				return number;
			else
				return 0;
		}

		if (!isNumber(arg)) {
			number = getItemId(arg);
			if (isItem(number))
				return number;
			else
				return 0;
		}

		return 0;

	}

	public static String getId(Player player) {
		UUID uuid = player.getUniqueId();

		return uuid.toString();
	}

	public static int getId(ItemStack item) {
		return 0;
	}

	public static <T> Map<Integer, List> divList(List<T> list, int size) {
		int listSize = 0;
		int rest = 0;
		int i = -1;
		int sz = 0;
		int s = 0;
		int div = 0;

		if (Util.divEqualsDouInt(list.size(), size)) {

			div = list.size() / size;
		} else {

			div = (list.size() / size) + 1;
		}
		Map<Integer, List> mapList = new HashMap<Integer, List>();
		List<T> listString = new ArrayList<>();
		;

		if (list.size() > size && size != 0) {
			listSize = list.size();
			System.out.println("listSize: " + listSize);
			sz = size;
		} else {
			System.out.println("return null");
			return null;
		}

		rest = list.size() - size;

		do {
			listString = new ArrayList<>();
			i++;

			for (int c = s; c < sz; c++) {
				listString.add(list.get(c));
//				System.out.println("listString.add(list.get("+ c+"));");
//				// System.out.println(listString.get(c));
//				System.out.println(list.get(c));

			}

			if (rest > size) {
				s = sz;
				sz += size;

			} else {

				s = sz;
				sz += rest;

			}
			if (rest != 0)
				rest -= size;

			mapList.put(i, listString);

			listString = mapList.get(i);

//			for (String string : listString) {
//				System.out.println("-> " + string);
//			}
//			System.out.println("REST :" + rest + "\nSIZE: " + size);
		} while (Util.mapKeyQuantity(mapList) != div);

		return mapList;
	}

	public static int mapKeyQuantity(Map map) {
		boolean c = false;
		int i = 0;
		if (map.isEmpty())
			return 0;

		while (!c) {

			if (map.get(i) == null) {
				return i;
			}
			i++;
		}
		return 0;

	}
	public static boolean isPlayer(UUID player) {
		if (Bukkit.getPlayer(player) != null) {
			return true;
		}

		return false;
	}
	public static boolean isPlayer(String player) {
		if (Bukkit.getPlayer(player) != null) {
			return true;
		}
		if(Bukkit.getOfflinePlayer(player) != null) {
			return true;
		}

		
		return false;
	}
	public static boolean isRegistredPlayer(Player player) {

		if(user.playerExist(player.getUniqueId()))
			return true;
		return false;
	}
	
	public static boolean isRegistredPlayer(String name) {
		if(isPlayer(name))
			try {
				if(user.playerExist(getPlayer(name).getUniqueId()) && !name.equals("")) {
					
					return true;
				}
			}catch (NullPointerException e) {
				// TODO: handle exception
				if(user.playerExist(getOfflinePlayer(name).getUniqueId()) && !name.equals("")) {
					
					return true;
				}
				return false;
			}
		System.out.println(name);
		return false;
	}
	public static boolean isRegistredPlayer(UUID uuid) {
		if(isPlayer(uuid))
			if(user.playerExist(uuid))
				return true;
		return false;
	}
	

	public static boolean isOnlinePlayers(String player) {
		if (Bukkit.getPlayer(player) != null) {
			return true;
		}
		return false;
	}
	public static Player getPlayer(String string) {
		if(isPlayer(string)) {
			
			return Bukkit.getServer().getPlayer(string);
			
		}
		return null;
	}
	public static Player getPlayer(UUID uuid) {
		if(isPlayer(uuid)) {
			
			return Bukkit.getServer().getPlayer(uuid);
			
		}
		return null;
	}
	public static Player getPlayer(OfflinePlayer playeroff) {
		if(playeroff.isOnline()) {
			return getPlayer(playeroff.getName());
		}
		return null;
	}

	public static OfflinePlayer getOfflinePlayer(String string) {
		if(isPlayer(string)) {
			
			return Bukkit.getServer().getOfflinePlayer(string);
			
		}
		return null;
	}

	public static OfflinePlayer getOfflinePlayer(Player player) {
		
		if(!player.isOnline()) {
			return getOfflinePlayer(player.getName());		
		}
		return null;
	}
	
	public static OfflinePlayer getOfflinePlayer(UUID uuid) {
		
		if(Bukkit.getOfflinePlayer(uuid) != null) {
			return Bukkit.getOfflinePlayer(uuid);
		}
		return null;
		
	}	
 	public static double thisPrice(Sign sign, Player player) {
		if (!sign.getLine(2).isEmpty()) {

			try {

				return Double.parseDouble(sign.getLine(2));
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println(sign.getLine(2));
				if (sign.getLine(2).equalsIgnoreCase("Free")) {

					return 0;
				}
				throw new EconomicException("Somente n�meros em pre�o (*FREE � uma exce��o)",player);
			}

		}
		return -1;
	}
	
	public static boolean signIsEmpty(Sign sign) {
		
		for(String line : sign.getLines()) {
				if(!line.equalsIgnoreCase("")) {
					return false;
				}
		}
		
		return true;
	}
	public static boolean signLineisEmpty(Sign sign, int p) {
		if(sign.getLine(p).equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	public static void callEvent(Event event) {
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
	public static boolean isShopRegistred() {

		return false;
	}
	
	public static BlockFace getClosestFace(float direction) {
        direction = direction % 360;

        if (direction < 0)
            direction += 360;

        direction = Math.round(direction / 45);

        switch ((int) direction) {
        case 0:
            return BlockFace.WEST;
        case 1:
            return BlockFace.NORTH_WEST;
        case 2:
            return BlockFace.NORTH;
        case 3:
            return BlockFace.NORTH_EAST;
        case 4:
        	return BlockFace.EAST;
        case 5:
            return BlockFace.SOUTH_EAST;
        case 6:
            return BlockFace.SOUTH;
        case 7:
            return BlockFace.SOUTH_WEST;
        default:
            return BlockFace.WEST;
        
        
        }
	}
	public static Location getSignWallOrientation(org.bukkit.material.Sign signMaterial, Sign signBlock ,Player player) {
		
		BlockFace signFace = signMaterial.getFacing();
		
		System.out.println(signFace);
		
		if(signFace.toString().equalsIgnoreCase("North")) {
			Location loc = new Location(player.getWorld(), signBlock.getX(), signBlock.getY(), signBlock.getZ() + 1);
			return loc;
		}
		if(signFace.toString().equalsIgnoreCase("West")) {
			Location loc = new Location(player.getWorld(), signBlock.getX() + 1, signBlock.getY(), signBlock.getZ());
			return loc;
		}
		if(signFace.toString().equalsIgnoreCase("East")) {
			Location loc = new Location(player.getWorld(), signBlock.getX() - 1, signBlock.getY(), signBlock.getZ());
			return loc;
		}
		if(signFace.toString().equalsIgnoreCase("South")) {
			Location loc = new Location(player.getWorld(), signBlock.getX(), signBlock.getY(), signBlock.getZ() - 1);
			return loc;
		}
		return null;
	}
	public static Location getFrontBlock(Block block , Player player) {
		
		Location pLocation = player.getLocation();
		BlockFace face =  getClosestFace(pLocation.getYaw());
		System.out.println(face);
		
		return null;
	}
	
	public static boolean isSignInChest(Location loc) {
		
		Material material = whatBlockTypeInLocation(loc);
		if(material.equals(Material.CHEST)){
			return true;
		}
		return false;
	}

	public static Location getBehindBlock(Block block, Player player) {
		Location loc = block.getLocation();
		BlockFace face = block.getFace(block);
		
		return null;
	}
	public static Block getBlockInLocation(Location loc) {
		
		if(loc != null)
			return loc.getBlock();
		return null;
	}
	public static Chest getChestInLocation(Location loc) {
		
		if(loc != null) {
			System.out.println(loc.getBlock().getType());
			if(loc.getBlock().getType().equals(Material.CHEST)) {
				return (Chest)loc.getBlock().getState();
			}
			
		}
		return null;
	}
	
	public static Material whatBlockTypeInLocation(Location loc) {
		return loc.getBlock().getType();
	}
	public static boolean checkTypeBlock(Block block1, Block block2) {
		if(block1.getType() == block2.getType()) {
			return true;
		}	
		return false;
	}
}
