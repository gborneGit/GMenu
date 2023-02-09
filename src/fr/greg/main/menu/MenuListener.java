package fr.greg.main.menu;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import fr.greg.main.Main;

public class MenuListener implements Listener {

	private Main					_main;

	public MenuListener (Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player	player = event.getPlayer();

		if (player.getInventory().getItem(8) == null || !MenuGuy.isGuy(player.getInventory().getItem(8))) {
			player.getInventory().clear(8);
			player.getInventory().setItem(8, MenuGuy.getGuy());
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if (it != null) {
			
			if (MenuGuy.isGuy(it)){
				
				if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
					
					if (_main.config.getDisableWorlds().contains(player.getWorld().getName()))
						player.sendMessage("§c[✖] §cLe menu n'est pas disponible ici");
					else
						MenuGuy.openGuy(player, _main);
				}
			}
		}
	}
	
	@EventHandler
    public void onDrop(PlayerDropItemEvent event){
        ItemStack item = event.getItemDrop().getItemStack();
     
        if (MenuGuy.isGuy(item)){
            event.setCancelled(true);
            event.getPlayer().updateInventory();
        }
    }
	
	@EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent event){
        ItemStack item = event.getOffHandItem();
     
        if (MenuGuy.isGuy(item)){
            event.setCancelled(true);
            event.getPlayer().updateInventory();
        }
    }
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		// Protection Dans le Menu
		if (current != null && current.getType() != null) {
			
			if (event.getView().getTitle().equals("§8Menu")) {
				
				event.setCancelled(true);
				player.closeInventory();
				player.updateInventory();
				
				switch (current.getType()) {
					case FEATHER:
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vote " + player.getName());
						break;
					case SHIELD:
						Bukkit.dispatchCommand(player, "duel");
						break;
					case APPLE:
						_main.teleport.launch(player, _main.config.getSpawn(), -1, "&eSpawn", null, true);
						break;
					case CREEPER_HEAD:
						_main.teleport.launch(player, _main.config.getSurvieSpawn(), -1, "&2Survie", null, true);
						break;
					case WITHER_SKELETON_SKULL:
						if (player.hasPermission(_main.config.getNetherPerm()))
							_main.teleport.launch(player, _main.config.getNetherSpawn(), -1, "&cNether", null, true);
						else
							player.sendMessage(_main.config.getNetherAccesMessage());
						break;
					case DRAGON_HEAD:
						if (player.hasPermission(_main.config.getEnderPerm()))
							_main.teleport.launch(player, _main.config.getEnderSpawn(), -1, "&bEnder", null, true);
						else
							player.sendMessage(_main.config.getEnderAccesMessage());
						break;
					case BRICKS:
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "city " + player.getName());
						break;
					case CYAN_BED:
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "city " + player.getName() + " myplots");
						break;
					case PLAYER_HEAD:
						MenuGuy.openGuy(player, _main);
						break;
					case SADDLE:
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "monture " + player.getName() + " inv");
						break;
					case YELLOW_BED:
						if (event.getClick().isLeftClick()) {
							
							Location loc = _main.home.getPlayerHome(player);
							
							if (loc == null)
								player.sendMessage("§e[⚐] §cPlacez un §eHome");
							else
								_main.teleport.launch(player, loc, -1, "&eHome", null, true);
						}
						else if (event.getClick().isRightClick()) {
							
							if (_main.config.getHomeWorld().equals(player.getWorld().getName())) {
								
								double price = _main.config.getHomePrice();
								
								if (_main.eco.getBalance(player) >= price) {
									
									Location loc = player.getLocation();
									
									_main.eco.withdrawPlayer(player, price);
									_main.home.addPlayerHome(player.getName(), loc);
									player.sendMessage("§a[⛃] Vous avez acheter §eHome §e(" + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + ")§a pour §6" + price + "⛃");
								}
								else {
									player.sendMessage("§c[⛃] §cVous n'avez pas assez d'argent");
								}
							}
							else
								player.sendMessage("§e[⚐] §cVous devez être en §2Survie");
						}
						else
							MenuGuy.openGuy(player, _main);
						break;
					default:
						break;
				}
			}
		}
	}
}
