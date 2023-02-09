package fr.greg.main.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.greg.main.Main;
import fr.greg.main.utils.Utils;

public class ConfigFile {
	
	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	
	public ConfigFile(Main main, String file_name) {
		_main = main;
		_yaml = loadConfig(file_name);
	}
	
	private YamlConfiguration loadConfig(String file_name) {
		
		if(!_main.getDirectory().exists()) {
			_main.getDirectory().mkdir();
		}
		
		_file = new File(_main.getDataFolder(), file_name);
		
		if (!_file.exists()) {
			_main.saveResource(file_name, false);
		}
		
		return YamlConfiguration.loadConfiguration(_file);
	}
	
	public void save() {
        try {
        	_yaml.save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void fillConfig() {
		
		Location spawn = Utils.parseStringToLoc(_yaml.getString("spawn.world"), _yaml.getString("spawn.loc"));

		_main.config.setDisableWorlds(_yaml.getStringList("disable_worlds"));
		_main.config.setSpawn(spawn);
		_main.config.setDelay(_yaml.getInt("teleportation_delay"));
		_main.config.setWorlds(_yaml.getStringList("worlds_without_delay"));
		_main.config.setHomePrice(_yaml.getDouble("home.price"));
		_main.config.setHomeWorld(_yaml.getString("home.world"));
		
		Location survie_spawn = Utils.parseStringToLoc(_yaml.getString("survie.world"), _yaml.getString("survie.loc"));
		_main.config.setSurvieSpawn(survie_spawn);
		
		Location nether_spawn = Utils.parseStringToLoc(_yaml.getString("nether.world"), _yaml.getString("nether.loc"));
		_main.config.setNetherSpawn(nether_spawn);
		_main.config.setNetherPerm(_yaml.getString("nether.perm"));
		_main.config.setNetherAccessMessage(_yaml.getString("nether.message"));
		
		Location ender_spawn = Utils.parseStringToLoc(_yaml.getString("ender.world"), _yaml.getString("ender.loc"));
		_main.config.setEnderSpawn(ender_spawn);
		_main.config.setEnderPerm(_yaml.getString("ender.perm"));
		_main.config.setEnderAccessMessage(_yaml.getString("ender.message"));
	}
	
	public void fillHome() {
		
		ConfigurationSection section;
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("" + i)) != null) {
			String player_name = section.getString("player");
			Location loc = Utils.parseStringToLoc(_main.config.getHomeWorld(), section.getString("location"));
			_main.home.addPlayerHome(player_name, loc);
			i++;
		}
	}
	
	public void setHome(String player_name, Location location) {
		
		ConfigurationSection section;
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("" + i)) != null) {
			if (section.getString("player").equals(player_name)) {
				section.set("location", Utils.parseLocToString(location));
				save();
				return;
			}
			i++;
		}
		_yaml.set(i + ".player", player_name);
		_yaml.set(i + ".location", Utils.parseLocToString(location));
		save();
	}
}
