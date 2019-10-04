package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubcommandReset implements CommandExecutor {

    private WaypointCompass main;

    SubcommandReset(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location waypoint;

        String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("wc.reset");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        waypoint = player.getWorld().getSpawnLocation();
        player.setCompassTarget(waypoint);

        return true;
    }
}
