package me.kyleseven.waypointcompass;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class Utils {
    public static void sendMsg(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
