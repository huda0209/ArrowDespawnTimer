package com.github.huda0209.arrowdespawntimer.task;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class despawnSchedule extends BukkitRunnable {
    private final Entity entity;
    private int time;

    public despawnSchedule(Entity en, int ti){
        this.entity = en;
        this.time = ti;
    }

    @Override
    public void run() {
        if(time == 0){
            try {
                entity.remove();
            }catch (Exception e){
                System.out.println("While remove a entity, occurred error.\n" + e.toString() + "\n" + e.getLocalizedMessage() + "\n" + e.getLocalizedMessage());
            }
            cancel();
            return;
        }
        time--;
    }
}