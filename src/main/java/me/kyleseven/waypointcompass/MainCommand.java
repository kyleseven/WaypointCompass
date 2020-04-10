package me.kyleseven.waypointcompass;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.kyleseven.waypointcompass.config.MsgConfig;
import org.bukkit.command.CommandSender;

@CommandAlias("waypointcompass|wc")
public class MainCommand extends BaseCommand {

    private final WaypointCompass plugin = WaypointCompass.getPlugin();

    @CatchUnknown
    public void onInvalid(CommandSender sender) {
        Utils.sendPrefixMsg(sender, MsgConfig.getInstance().getInvalidSubcommand());
    }

    @Subcommand("help|h")
    @Default
    public void doHelp(CommandSender sender) {
        String[] help = {"&7---- &cWaypointCompass Help &7----",
                         "&c/wc &7help &7- Displays the plugin commands.",
                         "&c/wc &7current &7- Displays the current compass heading.",
                         "&c/wc &7set &c<x> <y> <z> &7- Sets the compass to the specified coordinate.",
                         "&c/wc &7reset &7- Resets the compass back to spawn.",
                         "&c/wc &7reload &7- Reloads the plugin configuration.",
                         "&c/wc &7version &7- See the version of the plugin."};
        for (String s : help) {
            Utils.sendMsg(sender, s);
        }
    }
}
