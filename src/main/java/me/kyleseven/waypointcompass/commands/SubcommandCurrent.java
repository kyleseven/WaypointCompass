package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class SubcommandCurrent implements CommandExecutor {

    private WaypointCompass main;

    SubcommandCurrent(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("wc.current");
        // If player compass is pointing to spawn
        if (player.getCompassTarget().equals(player.getWorld().getSpawnLocation())) {
            msg = msg.replaceAll("%location", main.getConfigMessages().getString("wc.spawn"));
        }
        else {
            String coordinates;
            double x = player.getCompassTarget().getX();
            double y = player.getCompassTarget().getY();
            double z = player.getCompassTarget().getZ();
            DecimalFormat df = new DecimalFormat("#.##");
            coordinates = df.format(x) + " " + df.format(y) + " " + df.format(z);
            msg = msg.replaceAll("%location", coordinates);
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));

        return true;
    }
}
