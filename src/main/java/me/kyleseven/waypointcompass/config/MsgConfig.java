package me.kyleseven.waypointcompass.config;

public class MsgConfig extends ConfigLoader {
    private static MsgConfig msgConfig;

    public MsgConfig() {
        super("messages.yml");
    }

    public static MsgConfig getInstance() {
        if (msgConfig == null) {
            msgConfig = new MsgConfig();
        }
        return msgConfig;
    }

    public static void reload() {
        msgConfig = null;
        getInstance();
    }

    /*
    Message keys
     */

    public String getPrefix() {
        return config.getString("prefix");
    }

    public String getSet() {
        return config.getString("wc.set");
    }

    public String getReset() {
        return config.getString("wc.reset");
    }

    public String getCurrent() {
        return config.getString("wc.current");
    }

    public String getSpawn() {
        return config.getString("wc.spawn");
    }

    public String getReload() {
        return config.getString("wc.reload");
    }

    public String getSetUsage() {
        return config.getString("usage.set");
    }

    public String getNoPermsError() {
        return config.getString("error.noPerms");
    }

    public String getNaNError() {
        return config.getString("error.NaN");
    }

    public String getInvalidSubcommand() {
        return config.getString("error.invalidSubcommand");
    }
}
