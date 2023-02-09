package fr.greg.main.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.greg.main.Main;
import fr.greg.main.utils.Utils;

public class MenuGuy {
	
	public static boolean isGuy(ItemStack item) {
		if (item != null
				&& item.getType() != null
				&& item.getType() == Material.COMPASS
				&& item.getItemMeta() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().equals("§e(→) §cMenu"))
		{
			return true;
		}
		return false;
	}
	
	public static ItemStack getGuy() {
		
		ItemStack	customGuy = new ItemStack(Material.COMPASS, 1);
		ItemMeta	customM = customGuy.getItemMeta();
		customM.setDisplayName("§e(→) §cMenu");
		customM.setLore(Arrays.asList("§7▪ §fTout est ici ツ"));
		customGuy.setItemMeta(customM);
		
		return customGuy;
	}
	
	public static void openGuy(Player player, Main main) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§8Menu");
		
		// VOTES
		List<String> vote_lore = new ArrayList<String>();
		vote_lore.add("§e(●) §aVoir");
		inv.setItem(0, Utils.getItem(Material.FEATHER, "§bVote", vote_lore));
		
		
		// SURVIE
		List<String> survie_lore = new ArrayList<String>();
		survie_lore.add("§e(●) §aTéléportation");
		inv.setItem(12, Utils.getItem(Material.CREEPER_HEAD, "§2Survie", survie_lore));
		
		// NETHER
		List<String> nether_lore = new ArrayList<String>();
		if (player.hasPermission(main.config.getNetherPerm()))
			nether_lore.add("§e(●) §aTéléportation");
		else
			nether_lore.add("§cVérouillé");
		inv.setItem(13, Utils.getItem(Material.WITHER_SKELETON_SKULL, "§cNether", nether_lore));
				
		// ENDER
		List<String> ender_lore = new ArrayList<String>();
		if (player.hasPermission(main.config.getEnderPerm()))
			ender_lore.add("§e(●) §aTéléportation");
		else
			ender_lore.add("§cVérouillé");
		inv.setItem(14, Utils.getItem(Material.DRAGON_HEAD, "§bEnder", ender_lore));
		
		// SPAWN
		List<String> spawn_lore = new ArrayList<String>();
		spawn_lore.add("§7▪ §eShops");
		spawn_lore.add("§7▪ §dGrades");
		spawn_lore.add("§e(●) §aTéléportation");
		inv.setItem(22, Utils.getItem(Material.APPLE, "§eSpawn", spawn_lore));
		
		// ARENA
		List<String> arena_lore = new ArrayList<String>();
		arena_lore.add("§e(●) §aVoir");
		inv.setItem(23, Utils.getItem(Material.SHIELD, "§6Arène", arena_lore));
		
		// CITY
		List<String> city_lore = new ArrayList<String>();
		city_lore.add("§7▪ §9Maisons");
		city_lore.add("§e(●) §aVoir");
		inv.setItem(21, Utils.getItem(Material.BRICKS, "§9Villes", city_lore));
		
		// PLAYER INFO
		inv.setItem(18, Utils.getHeadInfo(player, main.eco.getBalance(player)));
		
		// MONTURES
		List<String> monture_lore = new ArrayList<String>();
		monture_lore.add("§e(●) §aVoir");
		inv.setItem(19, Utils.getItem(Material.SADDLE, "§6Montures", monture_lore));
		
		// PLAYER HOME
		List<String> home_lore = new ArrayList<String>();
		home_lore.add("§e(→) §aPlacer §6" + main.config.getHomePrice() + "⛃");
		home_lore.add("§e(←) §aTéléportation");
		inv.setItem(8, Utils.getItem(Material.YELLOW_BED, "§eHome", home_lore));
		
		// PLAYER PARCELLES
		List<String> plots_lore = new ArrayList<String>();
		plots_lore.add("§e(●) §aVoir");
		inv.setItem(26, Utils.getItem(Material.CYAN_BED, "§9Maisons", plots_lore));
		
		player.openInventory(inv);
	}
}
