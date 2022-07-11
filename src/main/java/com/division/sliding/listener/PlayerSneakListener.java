package com.division.sliding.listener;

import com.division.sliding.data.PlayerData;
import com.division.sliding.data.SlidingData;
import com.division.sliding.runnable.SlidingRunnable;
import com.division.sliding.util.SlidingUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class PlayerSneakListener implements Listener {

    private final SlidingData data;
    private final JavaPlugin Plugin;

    public PlayerSneakListener(SlidingData data, JavaPlugin Plugin) {
        this.data = data;
        this.Plugin = Plugin;
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player p = event.getPlayer();
        PlayerData playerData = data.getPlayerData(p.getUniqueId());
        if (event.isSneaking()) {
            if (playerData.getTaskID() != -1) //이미 슬라이딩이 진행중인경우
                SlidingUtil.stopSliding(p.getUniqueId(), data.getPlayerData(p.getUniqueId()));
            else {
                if (!data.isRequireRunning() || (data.isRequireRunning()) && p.isSprinting()) {
                    long currentTime = System.currentTimeMillis();
                    long lastTime = playerData.getLastSneakTime();
                    long lastSlideTime = playerData.getLastSlideTime();
                    if (lastTime != -1 && currentTime - lastTime <= data.getSneakInterval() && currentTime - lastSlideTime >= data.getSlideDelay()) {
                        //슬라이딩 실행
                        int taskID = Bukkit.getScheduler().runTaskTimer(Plugin, new SlidingRunnable(p.getUniqueId(), data, p.getLocation().getDirection()), 0L, 1L).getTaskId();
                        playerData.setLastSlideTime(currentTime);
                        playerData.setTaskID(taskID);
                    }
                    playerData.setLastSneakTime(System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJump(PlayerMoveEvent event) {
        Location to = event.getTo();
        Location from = event.getFrom();
        Player p = event.getPlayer();
        if (to != null && to.getY() - from.getY() > 0) {
            SlidingUtil.stopSliding(p.getUniqueId(), data.getPlayerData(p.getUniqueId()));
            p.setVelocity(new Vector(0,0,0));
        }
    }

}
