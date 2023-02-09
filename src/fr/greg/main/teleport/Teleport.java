package fr.greg.main.teleport;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import fr.greg.main.Main;

public class Teleport {
	
	private Main	_main;
	public Map<UUID, BukkitTask>	_map = new HashMap<UUID, BukkitTask>();
	
	public Teleport(Main main) {
		_main = main;
	}
	
	public void launch(Player player, Location loc, int delay, String title, String subtitle, boolean breakable) {

		if (_map.containsKey(player.getUniqueId()) == false) {
			
			if (player.hasPermission("gmenu.delay") || (_main.config.getWorlds().contains(player.getWorld().getName()) && delay < 0))
				delay = 0;
			else if (delay < 0)
				delay = _main.config.getDelay();
		
			TeleportTask task = new TeleportTask(_map, delay, player, loc, title, subtitle);
			
			if (delay > 0 && breakable)
				_map.put(player.getUniqueId(), task.runTaskTimer(_main, 0, 20));
			else {
				task.runTaskTimer(_main, 0, 20);
				_map.put(player.getUniqueId(), null);
			}
		}
		else
			player.sendMessage("§e[⚐] §cTéléportation déjà lancée..");
	}
	
	public Map<UUID, BukkitTask> getMap() {
		return _map;
	}
}
