package fr.greg.main;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.greg.main.commands.Commands;
import fr.greg.main.config.Config;
import fr.greg.main.config.ConfigFile;
import fr.greg.main.config.Home;
import fr.greg.main.menu.DeathListener;
import fr.greg.main.menu.MenuListener;
import fr.greg.main.teleport.Teleport;
import fr.greg.main.teleport.TeleportListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public ConfigFile				config_file;
	public Config					config;
	public ConfigFile				home_file;
	public Home						home;
	public Economy					eco;
	
	public Teleport					teleport;

	@Override
	public void onEnable() {

		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "[GMenu] You must have Vault");
			getServer().getPluginManager().disablePlugin(this);
			return ;
		}
		
		this.config_file = new ConfigFile(this, "config.yml");
		this.home_file = new ConfigFile(this, "home.yml");
		this.config = new Config();
		this.config_file.fillConfig();
		this.home = new Home(this);
		this.home_file.fillHome();
		
		this.teleport = new Teleport(this);
		
		getServer().getPluginManager().registerEvents(new MenuListener(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
		getServer().getPluginManager().registerEvents(new TeleportListener(this), this);
		
		getCommand("menu").setExecutor(new Commands(this));
	}
	
	@Override
	public void onDisable() {
	}
	
	public void reload() {
		this.config_file = new ConfigFile(this, "config.yml");
		this.home_file = new ConfigFile(this, "home.yml");
		this.config = new Config();
		this.config_file.fillConfig();
		this.home = new Home(this);
		this.home_file.fillHome();
		this.teleport = new Teleport(this);
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null)
			eco = economy.getProvider();
		return (economy != null);
	}
	
	public File getDirectory() {
		return getDataFolder();
	}
}
