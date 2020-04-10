package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.Utils;
import me.kyleseven.waypointcompass.WaypointCompass;
import me.kyleseven.waypointcompass.config.MsgConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

// Subcommand for /wc set
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
                // Send NaN error message
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

        // Format message and replace %location with xyz coordinates
        DecimalFormat df = new DecimalFormat("#.##");
        String coordinates = df.format(x) + " " + df.format(y) + " " + df.format(z);
        String message = MsgConfig.getInstance().getSet().replaceAll("%location", coordinates);

        // Send message
        Utils.sendPrefixMsg(sender, message);
        player.setCompassTarget(waypoint);

        return true;
    }
}
