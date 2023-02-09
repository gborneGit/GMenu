package fr.greg.main.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.greg.main.Main;

public class TeleportListener implements Listener {
	
	private Main					_main;
	
	public TeleportListener(Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		if (_main.teleport.getMap().get(p.getUniqueId()) != null) {
			if (isXYZMove(p.getLocation(), e.getTo()) == true) {
				_main.teleport.getMap().remove(p.getUniqueId()).cancel();
				p.sendMessage("§e[⚐] §cVous venez de bouger, TP annulée..");
			}
		}
		else if (_main.config.getWorlds().contains(p.getWorld().getName())) {
			if (e.getTo().getBlockY() < 0) {
				
				Entity vehicle = p.getVehicle();
				
				if (vehicle != null) {
					vehicle.eject();
				}
				p.teleport(_main.config.getSpawn());
				p.sendMessage("§e[⚐] §cAttention à la marche..");
			}
				
		}
			
	}
	
	public boolean isXYZMove(Location to, Location from) {
		if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
			return true;
		}
		return false;
	}

}
