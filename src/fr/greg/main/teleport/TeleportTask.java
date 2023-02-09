package fr.greg.main.teleport;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TeleportTask extends BukkitRunnable {

	private String					_title = null;
	private String					_subtitle = null;
	private int 					_timer;
	private Player 					_player;
	private Location				_loc;
	private Map<UUID, BukkitTask>	_map;
	
	public TeleportTask(Map<UUID, BukkitTask> map, int timer, Player player, Location loc, String title, String subtitle) {
		if (title != null)
			_title = title.replace("&", "§");
		if (subtitle != null)
			_subtitle = subtitle.replace("&", "§");
		_timer = timer;
		_player = player;
		_loc = loc;
		_map = map;
	}
	
	@Override
	public void run() {
		if (_timer > 0)
			_player.sendTitle("§e" + _timer, null, 10,20,10);
		
		_player.getWorld().spawnParticle(Particle.PORTAL, _player.getLocation().add(0, 1, 0), 50);
		
		if (_timer == 0) {
			_player.teleport(_loc);
			_player.playSound(_player, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
			_map.remove(_player.getUniqueId());
			if (_title != null || _subtitle != null)
				_player.sendTitle(_title, _subtitle, 20, 40, 20);
		}
		if (_timer == -5) {
			cancel();
		}
		_timer--;
	}

}
