package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class SubcommandSet implements CommandExecutor {

    private WaypointCompass main;

    SubcommandSet(WaypointCompass main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location waypoint;

        // Check valid args length
        if (args.length != 3) {
            String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("error.invalidArgs");
            String usage = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("usage.set");

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', usage));

            return false;
        }

        // Check that coordinate arguments are valid
        for (int i = 0; i < 3; i++) {
            try {
                Double.parseDouble(args[i]);
            }
            catch (NumberFormatException e) {
                String msg = main.getConfigMessages().getString("prefix")
                        + main.getConfigMessages().getString("error.NaN");
                msg = msg.replaceAll("%value", args[i]);

                String usage = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("usage.set");

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', usage));

                return false;
            }
        }

        // Save coordinates and set compass target
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double z = Double.parseDouble(args[2]);
        waypoint = new Location(player.getWorld(), x, y, z);

        // Format message and replace %x %y %z with actual coordinates
        DecimalFormat df = new DecimalFormat("#.##");
        String msg = main.getConfigMessages().getString("prefix")
                + main.getConfigMessages().getString("wc.set");
        msg = msg.replaceAll("%x", df.format(x));
        msg = msg.replaceAll("%y", df.format(y));
        msg = msg.replaceAll("%z", df.format(z));
        // Send
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        player.setCompassTarget(waypoint);

        return true;
    }
}
