package com.division.sliding.data;

import java.util.*;

public class SlidingData {

    private int distance;
    private int time;
    private long sneakInterval;
    private long slideDelay;
    private final Map<UUID, PlayerData> playerDataMap;
    private boolean requireRunning;

    public SlidingData() {
        distance = 0;
        time = 0;
        sneakInterval = 0;
        playerDataMap = new HashMap<>();
    }

    public int getSlidingDistance() {
        return distance;
    }

    public int getSlidingTime() {
        return time;
    }

    public void setSlidingDistance(int distance) {
        this.distance = distance;
    }

    public void setSlidingTime(int time) {
        this.time = time;
    }

    public long getSneakInterval() {
        return sneakInterval;
    }

    public void setSneakInterval(long sneakInterval) {
        this.sneakInterval = sneakInterval;
    }

    public long getSlideDelay() {
        return slideDelay;
    }

    public void setSlideDelay(long slideDelay) {
        this.slideDelay = slideDelay;
    }

    public boolean isRequireRunning() {
        return requireRunning;
    }

    public void setRequireRunning(boolean requireRunning) {
        this.requireRunning = requireRunning;
    }



    public PlayerData getPlayerData(UUID uuid) {
        if (playerDataMap.containsKey(uuid))
            return playerDataMap.get(uuid);
        else {
            PlayerData data = new PlayerData();
            playerDataMap.put(uuid, data);
            return data;
        }
    }


    public Collection<PlayerData> getAllPlayersData() {
        return playerDataMap.values();
    }
}
