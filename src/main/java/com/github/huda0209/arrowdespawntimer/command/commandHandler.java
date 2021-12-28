package com.github.huda0209.arrowdespawntimer.command;

import com.github.huda0209.arrowdespawntimer.ArrowDespawnTimer;
import com.github.huda0209.arrowdespawntimer.config.ConfigManager;
import com.github.huda0209.arrowdespawntimer.util.IndexSelection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class commandHandler implements CommandExecutor, TabCompleter {
    private final ArrowDespawnTimer plugin;
        public commandHandler(ArrowDespawnTimer pl) {
        this.plugin = pl;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(command.getPermission())){
            sender.sendMessage(command.getPermissionMessage());
            return true;
        }

        if(args.length<1){
            plugin.getLogger().info(sender.getName() + " ran the command. But, the argument is missing.");
            sender.sendMessage("§cThe argument is missing.");
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "settime" -> {
                if (args.length != 2) {
                    plugin.getLogger().info(sender.getName() + " ran the command. But, the argument is missing.");
                    sender.sendMessage("§cThe argument is missing.");
                    break;
                }

                int tempDespawnTime;
                try {
                    tempDespawnTime = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    plugin.getLogger().info(sender.getName() + " ran the command. But, occurred error.§r\n"+e.toString());
                    sender.sendMessage("§cWhile run the command, occurred error.§r\n"+e.toString());
                    break;
                }

                if (tempDespawnTime < 1 || tempDespawnTime > 59) {
                    plugin.getLogger().info(sender.getName() + " ran the command. But, the time was not specified between 1 and 59.");
                    sender.sendMessage("§cPlease specify a time between 1 and 59.");
                    break;
                }

                ConfigManager.setDespawnTime(tempDespawnTime);
                plugin.getLogger().info(sender.getName() + " ran the command, and the time is set to " + ConfigManager.getDespawnTime() + ".");
                sender.sendMessage("§aThe time is set to " + ConfigManager.getDespawnTime() + ".");
            }

            case "info" -> {
                sender.sendMessage("§a" + plugin.getDescription().getName() + " is §bv" + plugin.getDescription().getVersion() + "§r\n" +
                        "§aArrow despawn time §8: §b" + ConfigManager.getDespawnTime()+"s §8(§7" + ConfigManager.getDespawnTime()*20 + "ticks§8)");
            }

            case "reload" -> {
                try{
                    ConfigManager.reloadConfig();
                }catch (Exception e){
                    plugin.getLogger().info(sender.getName() + " ran the command. But while reload the config file, occurred error.\n" +  e.toString());
                    sender.sendMessage("§cWhile reload the config file, occurred error.\n" +  e.toString());
                    break;
                }
                plugin.getLogger().info(sender.getName() + " ran the command, and succeeded to reload the config file.");
                sender.sendMessage("§aSucceeded to reload the config file.");
            }

            default -> {
                plugin.getLogger().info(sender.getName() + " ran the command. But, unknown command.");
                sender.sendMessage(command.getUsage());
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!sender.hasPermission(command.getPermission())) return new ArrayList<String>();
        if(args.length <= 1) return IndexSelection.select(args[0], Arrays.asList("SetTime", "info", "reload"));
        if(args.length ==2 && args[1].equalsIgnoreCase("settime")) return Collections.singletonList("times");
        return new ArrayList<String>();
    }
}