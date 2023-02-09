package fr.greg.main.config;

import java.util.List;

import org.bukkit.Location;

public class Config {
	
	private Location	_spawn;
	
	List<String>		_worlds_disabled;
	
	private int			_delay;
	List<String>		_worlds;
	
	private double		_home_price;
	private String		_home_world;
	
	private Location	_survie_spawn;
	
	private Location	_nether_spawn;
	private String		_nether_perm;
	private String		_nether_access_message;
	
	private Location	_ender_spawn;
	private String		_ender_perm;
	private String		_ender_access_message;
	
	public Config() {}
	
	// SETTERS
	
	public void setSpawn(Location loc) {
		_spawn = loc;
	}
	
	public void setDelay(int delay) {
		_delay = delay;
	}
	
	public void setDisableWorlds(List<String> worlds) {
		_worlds_disabled = worlds;
	}
	
	public void setWorlds(List<String> worlds) {
		_worlds = worlds;
	}
	
	public void setHomePrice(double price) {
		_home_price = price;
	}
	
	public void setHomeWorld(String world_name) {
		_home_world = world_name;
	}
	
	public void setSurvieSpawn(Location loc) {
		_survie_spawn = loc;
	}
	
	public void setNetherSpawn(Location loc) {
		_nether_spawn = loc;
	}
	
	public void setNetherPerm(String perm) {
		_nether_perm = perm;
	}
	
	public void setNetherAccessMessage(String msg) {
		_nether_access_message = msg;
	}
	
	public void setEnderSpawn(Location loc) {
		_ender_spawn = loc;
	}
	
	public void setEnderPerm(String perm) {
		_ender_perm = perm;
	}
	
	public void setEnderAccessMessage(String msg) {
		_ender_access_message = msg;
	}
	
	// GETTERS
	
	public Location getSpawn() {
		return _spawn;
	}
	
	public int getDelay() {
		return _delay;
	}
	
	public List<String> getDisableWorlds() {
		return _worlds_disabled;
	}
	
	public List<String> getWorlds() {
		return _worlds;
	}
	
	public double getHomePrice() {
		return _home_price;
	}
	
	public String getHomeWorld() {
		return _home_world;
	}
	
	public Location getSurvieSpawn() {
		return _survie_spawn;
	}
	
	public Location getNetherSpawn() {
		return _nether_spawn;
	}
	
	public String getNetherPerm() {
		return _nether_perm;
	}
	
	public String getNetherAccesMessage() {
		return _nether_access_message;
	}
	
	public Location getEnderSpawn() {
		return _ender_spawn;
	}
	
	public String getEnderPerm() {
		return _ender_perm;
	}
	
	public String getEnderAccesMessage() {
		return _ender_access_message;
	}

}
