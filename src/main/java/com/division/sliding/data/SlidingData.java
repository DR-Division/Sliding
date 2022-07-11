package com.division.sliding.data;

import java.util.*;

public class SlidingData {

    private int distance;
    private int time;
    private final Map<UUID, PlayerData> playerDataMap;

    public SlidingData() {
        distance = 0;
        time = 0;
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
