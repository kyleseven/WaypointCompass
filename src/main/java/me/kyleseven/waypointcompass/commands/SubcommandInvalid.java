package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Print invalid subcommand message
public class SubcommandInvalid implements CommandExecutor {

    private WaypointCompass main;

    SubcommandInvalid(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("error.invalidSubcommand");
        msg = msg.replaceAll("%command", args[0]);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));

        return false;
    }
}
