package com.division.sliding;

import com.division.sliding.listener.PlayerSneakListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sliding extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerListeners(new PlayerSneakListener());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
