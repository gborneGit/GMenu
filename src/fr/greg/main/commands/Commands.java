package fr.greg.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.greg.main.Main;
import fr.greg.main.menu.MenuGuy;
import fr.greg.main.utils.Utils;

public class Commands implements CommandExecutor {
	
	private Main	_main;
	
	public Commands(Main main) {
		_main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = null;
		
		if (args.length > 0)
			player = Bukkit.getPlayer(args[0]);
		
		if (player != null) {
			
			if (!(sender instanceof Player)) {
				
				if (args.length == 1) {
					MenuGuy.openGuy(player, _main);
					return true;
				}
				else if (args.length == 2) {
					
					if (args[1].equals("spawn")) {
						_main.teleport.launch(player, _main.config.getSpawn(), -1, "&eSpawn", null, true);
						return true;
					}
					else
						System.out.println(ChatColor.RED + "[GMenu] Wrong spawn command '" + args[1] + "'");
				}
				else if (args.length >= 3) {
					
					Location 	loc = null;
					int 		delay = -1;
					boolean		breakable = true;
					
					String 		title = null;
					String		subtitle = null;
					
					if (args[1].equals("spawn")) {
						loc = _main.config.getSpawn();
						title = "§eSpawn";
						
						try {
							if (args.length >= 3)
								delay = Integer.valueOf(args[2]);
							if (args.length == 4 && !args[3].equals("true"))
								breakable = Boolean.getBoolean(args[3]);
						} catch (Exception e) {
							System.out.println("§c[GMenu] " + e.toString());
							for (int i = 0; i < args.length; i++)
								System.out.println("§c<" + args[i] + ">");
						}
						
					}
					else if (args[1].equals("tp") && args.length >= 4) {
						
						try {
							
							loc = Utils.parseStringToLoc(args[2], args[3]);
							
							if (args.length >= 5 && !args[4].equals("-1"))
								delay = Integer.parseInt(args[4]);
							if (args.length >= 6 && !args[5].equals("null"))
								title = args[5];
							if (args.length >= 7 && !args[6].equals("null"))
								subtitle = args[6];
							if (args.length == 8 && !args[7].equals("true"))
								breakable = Boolean.getBoolean(args[7]);
						} catch (Exception e) {
							System.out.println("§c[GMenu] " + e.toString());
							for (int i = 0; i < args.length; i++)
								System.out.println("§c<" + args[i] + ">");
						}
					}
					
					if (loc != null) {
						_main.teleport.launch(player, loc, delay, title, subtitle, breakable);
						return true;
					}
					else
						System.out.println(ChatColor.RED + "[GMenu] Invalid location");
				}
				System.out.println(ChatColor.RED + "[GMenu] Wrong tp command");
			}
		}
		if (sender instanceof Player) {
			if (args[0].equals("reload") && sender.hasPermission("gmenu.reload")) {
				_main.reload();
				sender.sendMessage("§a[GMenu] Reload success");
				System.out.println("§a[GMenu] Reload success");
				return true;
			}
		}
		return false;
	}
}
