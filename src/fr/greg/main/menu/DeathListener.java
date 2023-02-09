package fr.greg.main.menu;

import java.util.Iterator;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		List<ItemStack> list = e.getDrops();
		Iterator<ItemStack> i = list.iterator();
		
		while(i.hasNext()) {
			ItemStack item = i.next();
			if (MenuGuy.isGuy(item)) {
				i.remove();
			}
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		Inventory inv = e.getPlayer().getInventory();
		
		if (inv.getItem(8) == null) {
			inv.setItem(8, MenuGuy.getGuy());
		}
	}
}
