package com.github.huda0209.arrowdespawntimer.config;

import com.github.huda0209.arrowdespawntimer.ArrowDespawnTimer;
import org.bukkit.configuration.Configuration;


public class ConfigManager {
    private static ArrowDespawnTimer plugin;
    private static Configuration config;
    private static int DespawnTime = 59;


    public static void loadConfig(ArrowDespawnTimer pl){
        plugin=pl;
        config = plugin.getConfig();

        if(!config.isInt("DespawnTime")){
            throw new NumberFormatException("For input string \""+config.get("DespawnTime")+"\"");
        }

        int tempDespawnTime = config.getInt("DespawnTime");

        if(tempDespawnTime<1 || tempDespawnTime >59) DespawnTime = 59;
            else DespawnTime = tempDespawnTime;
    }

    public static int getDespawnTime(){
        System.out.println(DespawnTime);
        return DespawnTime;
    }

    public static void setDespawnTime(int time){
        DespawnTime = time;
        config.set("DespawnTime", DespawnTime);
        plugin.saveConfig();
    }

    public static void reloadConfig(){
        plugin.reloadConfig();
        config = plugin.getConfig();
        if(!config.isInt("DespawnTime")){
            throw new NumberFormatException("For input string \""+config.get("DespawnTime")+"\"");
        }
        int tempDespawnTime = config.getInt("DespawnTime");
        if(tempDespawnTime<1 || tempDespawnTime >59) DespawnTime = 59;
        else DespawnTime = tempDespawnTime;
    }
}