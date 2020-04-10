package me.kyleseven.waypointcompass.config;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class ConfigLoader {
    protected WaypointCompass plugin = WaypointCompass.getPlugin();
    protected String fileName;
    protected File configFile;
    protected FileConfiguration config;

    public ConfigLoader(String relativePath, String fileName) {
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), relativePath + File.separator + fileName);
        loadFile();
    }

    public ConfigLoader(String fileName) {
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        loadFile();
    }

    protected void loadFile() {
        if (!configFile.exists()) {
            plugin.getServer().getLogger().info("Creating " + fileName + " file...");
            plugin.saveResource(fileName, false);
        }
        else {
            plugin.getServer().getLogger().info("Loading " + fileName + "file...");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
