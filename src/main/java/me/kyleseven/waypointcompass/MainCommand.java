package me.kyleseven.waypointcompass;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.kyleseven.waypointcompass.config.MainConfig;
import me.kyleseven.waypointcompass.config.MsgConfig;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

@CommandAlias("waypointcompass|wc")
public class MainCommand extends BaseCommand {

    private final WaypointCompass plugin = WaypointCompass.getPlugin();

    @CatchUnknown
    public void onInvalid(CommandSender sender) {
        Utils.sendPrefixMsg(sender, MsgConfig.getInstance().getInvalidSubcommand());
    }

    @Subcommand("help|h")
    @Description("Gives plugin command info.")
    @CommandPermission("waypointcompass.use")
    @HelpCommand
    @Default
    public void doHelp(CommandSender sender) {
        String[] help = {"&7---- &cWaypointCompass Help &7----",
                         "&c/wc &7help &7- Displays the plugin commands.",
                         "&c/wc &7current &7- Displays the current compass heading.",
                         "&c/wc &7set &c<x> <y> <z> &7- Sets the compass to the specified coordinate.",
                         "&c/wc &7reset &7- Resets the compass back to spawn.",
                         "&c/wc &7reload &7- Reloads the plugin configuration.",
                         "&c/wc &7version &7- See the version of the plugin."};
        for (String s : help) {
            Utils.sendMsg(sender, s);
        }
    }

    @Subcommand("set|s")
    @Description("Sets the player compass target.")
    @CommandPermission("waypointcompass.use")
    public void doSet(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location waypoint;

        // Check valid args length
        if (args.length != 3) {
            Utils.sendPrefixMsg(sender, MsgConfig.getInstance().getSetUsage());
            return;
        }

        // Check that coordinate arguments are valid
        for (int i = 0; i < 3; i++) {
            try {
                Double.parseDouble(args[i]);
            }
            catch (NumberFormatException e) {
                // Send NaN error message
                String message = MsgConfig.getInstance().getNaNError().replaceAll("%value", args[i]);

                Utils.sendPrefixMsg(sender, message);
                Utils.sendPrefixMsg(sender, MsgConfig.getInstance().getSetUsage());
                return;
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
    }

    @Subcommand("reset|r")
    @Description("Resets the player compass target to world spawn.")
    @CommandPermission("waypointcompass.use")
    public void doReset(CommandSender sender) {
        Player player = (Player) sender;
        Location waypoint;
        Utils.sendPrefixMsg(player, MsgConfig.getInstance().getReset());
        waypoint = player.getWorld().getSpawnLocation();
        player.setCompassTarget(waypoint);
    }

    @Subcommand("current|c")
    @Description("Retrieves the player's current compass heading")
    @CommandPermission("waypointcompass.use")
    public void doCurrent(CommandSender sender) {
        Player player = (Player) sender;
        String message = MsgConfig.getInstance().getCurrent();

        // If player compass is pointing to spawn, print spawn. Else, print the coordinate.
        if (player.getCompassTarget().equals(player.getWorld().getSpawnLocation())) {
            message = message.replaceAll("%location", MsgConfig.getInstance().getSpawn());
        }
        else {
            String coordinates;
            double x = player.getCompassTarget().getX();
            double y = player.getCompassTarget().getY();
            double z = player.getCompassTarget().getZ();
            DecimalFormat df = new DecimalFormat("#.##");
            coordinates = df.format(x) + " " + df.format(y) + " " + df.format(z);
            message = message.replaceAll("%location", coordinates);
        }

        Utils.sendPrefixMsg(player, message);
    }

    @Subcommand("version|v")
    @Description("Shows the plugin version")
    @CommandPermission("waypointcompass.use")
    public void doVersion(CommandSender sender) {
        String message = "WaypointCompass " + MainConfig.getInstance().getVersion() + " by kyleseven";
        Utils.sendPrefixMsg(sender, message);
    }
}
