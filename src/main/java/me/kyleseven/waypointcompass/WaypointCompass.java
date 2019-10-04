package me.kyleseven.waypointcompass;

import me.kyleseven.waypointcompass.commands.CommandMainCW;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class WaypointCompass extends JavaPlugin {

    private FileConfiguration configMessages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Save config.yml if it doesn't already exist
        this.saveDefaultConfig();
        // Create new message config
        createConfigMessages();
        // Executor for command wc - pass config and messages
        this.getCommand("waypointcompass").setExecutor(new CommandMainCW(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Returns the messages.yml config
    public FileConfiguration getConfigMessages() {
        return this.configMessages;
    }

    // Reloads the messages.yml config
    public void reloadConfigMessages() {
        createConfigMessages();
    }

    // Creates the messages.yml config file
    private void createConfigMessages() {
        File configMessagesFile = new File(getDataFolder(), "messages.yml");
        if (!configMessagesFile.exists()) {
            configMessagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        configMessages = new YamlConfiguration();
        try {
            configMessages.load(configMessagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


}
