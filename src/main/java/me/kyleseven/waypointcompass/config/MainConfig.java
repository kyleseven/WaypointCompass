package me.kyleseven.waypointcompass.config;

public class MainConfig extends ConfigLoader {
    private static MainConfig mainConfig;

    public MainConfig() {
        super("config.yml");
    }

    public static MainConfig getInstance() {
        if (mainConfig == null) {
            mainConfig = new MainConfig();
        }
        return mainConfig;
    }

    public static void reload() {
        mainConfig = null;
        getInstance();
    }

    /*
    Config keys
     */

    public String getVersion() {
        return config.getString("version");
    }
}
