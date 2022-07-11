package com.division.sliding.runnable;

import com.division.sliding.data.SlidingData;
import com.division.sliding.util.SlidingUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

public class SlidingRunnable implements Runnable{

    private int count;
    private final UUID uuid;
    private final SlidingData data;
    private final Vector direction;

    public SlidingRunnable(UUID uuid, SlidingData data, Vector direction) {
        count = 0;
        this.uuid = uuid;
        this.data = data;
        this.direction = direction.setY(0);
    }

    @Override
    public void run() {
        int maxTime = data.getSlidingTime() == 0 ? 1 : data.getSlidingTime();
        int distance = data.getSlidingDistance() == 0 ? 1 : data.getSlidingDistance();
        int interval = maxTime / distance == 0 ? 1 : maxTime / distance;
        Player p = Bukkit.getPlayer(uuid);
        if (p == null || count >= maxTime)
            SlidingUtil.stopSliding(uuid, data.getPlayerData(uuid));
        else {
            p.setGliding(true);
            if (count >= interval && count % interval == 0)
                p.setVelocity(direction);
        }
        count++;


    }


}
