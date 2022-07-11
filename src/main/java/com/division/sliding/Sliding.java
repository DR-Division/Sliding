package com.division.sliding;

import com.division.sliding.command.SlidingCommand;
import com.division.sliding.data.PlayerData;
import com.division.sliding.data.SlidingData;
import com.division.sliding.file.SneakConfig;
import com.division.sliding.listener.PlayerSneakListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Sliding extends JavaPlugin {

    private SlidingData data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        data = new SlidingData();
        SneakConfig config = new SneakConfig(data, this, getDataFolder().getAbsolutePath());
        config.load();
        Objects.requireNonNull(getCommand("sliding")).setExecutor(new SlidingCommand(config));
        registerListeners(new PlayerSneakListener(data, this));

    }

    @Override
    public void onDisable() {
        for (PlayerData data : data.getAllPlayersData()) {
            Bukkit.getScheduler().cancelTask(data.getTaskID());
        }
        // Plugin shutdown logic
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
