package com.division.sliding.util;

import com.division.sliding.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SlidingUtil {

    public static void stopSliding(UUID uuid, PlayerData data) {
        Bukkit.getScheduler().cancelTask(data.getTaskID());
        Player p = Bukkit.getPlayer(uuid);
        if (p != null)
            p.setGliding(false);
        data.setTaskID(-1);
    }
}
