package me.kyleseven.waypointcompass;

import co.aikar.commands.PaperCommandManager;
import me.kyleseven.waypointcompass.commands.BaseCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class WaypointCompass extends JavaPlugin {

    private static WaypointCompass plugin;
    private static PaperCommandManager commandManager;
    private FileConfiguration configMessages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        // Save config.yml if it doesn't already exist
        this.saveDefaultConfig();
        // Create new message config
        createConfigMessages();
        // Executor for command wc - pass config and messages
        Objects.requireNonNull(this.getCommand("waypointcompass")).setExecutor(new BaseCommand(this));
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static WaypointCompass getPlugin() {
        return plugin;
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
