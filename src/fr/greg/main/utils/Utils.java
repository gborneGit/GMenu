package fr.greg.main.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
	
	public static Location parseStringToLoc(String world, String string) {
		
		String[] parseLoc = string.split(",");
		World w = Bukkit.getWorld(world);
		
		if (w == null || parseLoc.length != 5)
			return null;
		
		double x = Double.valueOf(parseLoc[0]);
		double y = Double.valueOf(parseLoc[1]);
		double z = Double.valueOf(parseLoc[2]);
		float face_x = Float.valueOf(parseLoc[3]);
		float face_y = Float.valueOf(parseLoc[4]);
		
		Location loc = new Location(w, x, y, z, face_x, face_y);
		
		return loc;
	}
	
	public static String parseLocToString(Location loc) {
		String string = loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();  
		return string;
	}

	public static ItemStack getHeadInfo(Player player, double balance) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7▪ §6" + balance + "⛃");
        skull.setLore(lore);
        skull.setOwnerProfile(player.getPlayerProfile());
        item.setItemMeta(skull);
        return item;
    }
	
	public static ItemStack getItem(Material material, String custom_name, List<String> lore) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(custom_name);
		if (lore != null && !lore.isEmpty())
			itemM.setLore(lore);
		item.setItemMeta(itemM);;
		return item;
	}

}
