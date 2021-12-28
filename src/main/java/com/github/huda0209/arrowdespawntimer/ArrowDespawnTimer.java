package com.github.huda0209.arrowdespawntimer;

import com.github.huda0209.arrowdespawntimer.command.commandHandler;
import com.github.huda0209.arrowdespawntimer.listener.EntitySpawnEventListener;
import com.github.huda0209.arrowdespawntimer.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArrowDespawnTimer extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("ArrowDespawnTimer").setExecutor(new commandHandler(this));
        getServer().getPluginManager().registerEvents(new EntitySpawnEventListener(this),this);
        try{
            ConfigManager.loadConfig(this);
        }catch (Exception e){
            getLogger().warning("While load the config file, occurred error.\n" +  e.toString());
            this.setEnabled(false);
        }
        String[] EnableMessage = {"=============================","Plugin Name : "+this.getDescription().getName() ,"Author : "+ this.getDescription().getAuthors(),"============================="};
        for (String s : EnableMessage) {
            getLogger().info(s);
        }
    }

    @Override
    public void onDisable() {
        this.
        getLogger().info("This plugin was disable.");
    }
}