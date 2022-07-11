package com.division.sliding.file;

import com.division.sliding.data.SlidingData;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SneakConfig {

    private final File configFile;
    private final YamlConfiguration config;
    private final SlidingData data;
    private final JavaPlugin Plugin;

    public SneakConfig(SlidingData data, JavaPlugin Plugin, String path) {
        this.data = data;
        this.configFile = new File(path, "config.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.Plugin = Plugin;
    }

    public void load() {
        try {
            config.load(configFile);
            data.setSlidingDistance(config.getInt("sliding-distance", 5));
            data.setSlidingTime(config.getInt("sliding-time", 20));
            data.setSneakInterval(config.getLong("sneak-interval", 500));
            data.setSlideDelay(config.getLong("sliding-delay", 2000));
            data.setRequireRunning(config.getBoolean("require-running", true));

        }
        catch (IOException | InvalidConfigurationException e) {
            Plugin.saveDefaultConfig();
        }
    }
}
