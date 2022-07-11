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

    /*@EventHandler
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
    }*/

    @EventHandler
    public void onPlayerJump(PlayerMoveEvent event) {
        Location to = event.getTo();
        Location from = event.getFrom();
        Player p = event.getPlayer();
        PlayerData playerData = data.getPlayerData(p.getUniqueId());
        if (to != null) {
            if (to.getY() - from.getY() > 0) {
                //JUMP
                if (data.getPlayerData(p.getUniqueId()).getTaskID() != -1) {
                    SlidingUtil.stopSliding(p.getUniqueId(), data.getPlayerData(p.getUniqueId()));
                    p.setVelocity(new Vector(0, 0, 0));
                }
            }
            else if (to.getY() == from.getY() && playerData.getTaskID() == -1 && p.isSneaking()) {
                //슬라이딩 안하는경우
                String direction = playerData.getDirection();
                Vector blockDirection = to.clone().subtract(from).toVector();
                String currentDirection = parseDirection(blockDirection, p.getLocation().getDirection());
                long currentTime = System.currentTimeMillis();
                long lastTime = playerData.getLastSneakTime();
                long lastSlideTime = playerData.getLastSlideTime();
                if (direction != null && direction.equalsIgnoreCase(currentDirection) && currentTime - lastTime >= data.getSneakInterval() && currentTime - lastSlideTime >= data.getSlideDelay()) {
                    int taskID = Bukkit.getScheduler().runTaskTimer(Plugin, new SlidingRunnable(p.getUniqueId(), data, blockDirection.multiply(10)), 0L, 1L).getTaskId();
                    playerData.setLastSlideTime(currentTime);
                    playerData.setTaskID(taskID);
                    playerData.setDirection(null);
                }
                playerData.setLastSneakTime(System.currentTimeMillis());
                playerData.setDirection(currentDirection);

            }

        }
    }

    private String parseDirection(Vector move, Vector direction) {
        //move = 움직인 위치 - 원래위치 벡터, direction은 방향벡터
        direction.setY(0);
        Vector backWard = direction.clone().multiply(-1);
        Vector rightSide = new Vector(-direction.getZ(), 0 , direction.getX());
        Vector leftSide = new Vector(direction.getZ(), 0 , -direction.getX());
        try {
            double angle = move.angle(direction);
            if (angle <= 0.05)
                return "W";
            else if (move.angle(backWard) <= 0.2)
                return "S";
            else if (move.angle(rightSide) <= 0.2)
                return "D";
            else if (move.angle(leftSide) <= 0.2)
                return "A";
            else {
                return null;
            }
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}
