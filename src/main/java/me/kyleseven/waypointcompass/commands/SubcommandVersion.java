package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SubcommandVersion implements CommandExecutor {

    private WaypointCompass main;

    SubcommandVersion(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String msg = main.getConfigMessages().getString("prefix") + "Waypoint Compass " + main.getConfig().getString("version") + " by kyleseven";
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));

        return true;
    }
}
