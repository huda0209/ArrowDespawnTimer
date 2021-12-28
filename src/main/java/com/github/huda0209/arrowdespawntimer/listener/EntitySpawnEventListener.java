package com.github.huda0209.arrowdespawntimer.listener;

import com.github.huda0209.arrowdespawntimer.ArrowDespawnTimer;
import com.github.huda0209.arrowdespawntimer.task.despawnSchedule;
import com.github.huda0209.arrowdespawntimer.config.ConfigManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnEventListener implements Listener {
    private final ArrowDespawnTimer plugin;
    public EntitySpawnEventListener(ArrowDespawnTimer pl) {
        this.plugin=pl;
    }

    @EventHandler
    public void EntitySpawnEventHandler(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        if( !(entity instanceof Arrow) ) return;
        new despawnSchedule(entity, ConfigManager.getDespawnTime()).runTaskTimer(plugin,0,20);
    }
}
