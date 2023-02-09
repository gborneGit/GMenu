package fr.greg.main.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.greg.main.Main;

public class Home {
	
	private class PlayerHome {
		
		public PlayerHome(String p_name, Location l) {
			player_name = p_name;
			loc = l;
		}
		public String	player_name;
		public Location	loc;
	}

	private Main _main;
	private List<PlayerHome>	_player_home = new ArrayList<PlayerHome>();
	
	
	public Home(Main main) {
		_main = main;
	}
	
	private boolean setPlayerHome(String p_name, Location loc) {
		for (int i = 0; i < _player_home.size(); i++) {
			if (_player_home.get(i).player_name.equals(p_name)) {
				_player_home.get(i).loc = loc;
				return true;
			}
		}
		return false;
	}
	
	// SETTERS
	
	public void addPlayerHome(String player_name, Location loc) {
		if (setPlayerHome(player_name, loc) == false) {
			PlayerHome	player_home = new PlayerHome(player_name, loc);
			_player_home.add(player_home);
		}
		_main.home_file.setHome(player_name, loc);
	}
	
	// GETTERS
	
	public Location getPlayerHome(Player player) {
		
		for (int i = 0; i < _player_home.size(); i++) {
			if (_player_home.get(i).player_name.equals(player.getName()))
				return _player_home.get(i).loc;
		}
		return null;
	}
	
}
