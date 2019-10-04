package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SubcommandHelp implements CommandExecutor {

    private WaypointCompass main;

    SubcommandHelp(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

        return true;
    }

}
