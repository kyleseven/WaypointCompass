package me.kyleseven.waypointcompass;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MainCommand extends BaseCommand {
    @Subcommand("help|h")
    @Default @CatchUnknown
    public void doHelp(CommandSender sender) {
        String[] help = {"&7---- &cWaypointCompass Help &7----",
                "&c/wc &7help &7- Displays the plugin commands.",
                "&c/wc &7current &7- Displays the current compass heading.",
                "&c/wc &7set &c<x> <y> <z> &7- Sets the compass to the specified coordinate.",
                "&c/wc &7reset &7- Resets the compass back to spawn.",
                "&c/wc &7reload &7- Reloads the plugin configuration.",
                "&c/wc &7version &7- See the version of the plugin."};
        for (String s : help) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }
}
