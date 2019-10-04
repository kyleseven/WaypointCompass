package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SubcommandReload implements CommandExecutor {

    private WaypointCompass main;

    SubcommandReload(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("waypointcompass.use.reload")) {
            String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("wc.reload");
            String msgCR = main.getConfigMessages().getString("prefix") + "config.yml - &areloaded.";
            String msgMR = main.getConfigMessages().getString("prefix") + "messages.yml - &areloaded.";

            main.saveDefaultConfig();
            main.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msgCR));
            main.reloadConfigMessages();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msgMR));

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            return true;
        } else {
            String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("error.noPerms");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            return false;
        }
    }
}
