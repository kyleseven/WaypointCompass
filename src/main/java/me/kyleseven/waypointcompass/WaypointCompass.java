package me.kyleseven.waypointcompass;

import co.aikar.commands.PaperCommandManager;
import me.kyleseven.waypointcompass.config.MainConfig;
import me.kyleseven.waypointcompass.config.MsgConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaypointCompass extends JavaPlugin {

    private static WaypointCompass plugin;
    private static PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        // Load plugin configs
        loadConfigs();
        // Executor for command wc - pass config and messages
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static WaypointCompass getPlugin() {
        return plugin;
    }

    public void loadConfigs() {
        MainConfig.getInstance();
        MsgConfig.getInstance();
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new MainCommand());
    }
}
