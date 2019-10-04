package me.kyleseven.waypointcompass.commands;

import me.kyleseven.waypointcompass.WaypointCompass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

// Main command for all WaypointCompass functions (/waypointcompass or /wc)
public class CommandMainCW implements CommandExecutor {

    private WaypointCompass main;
    // Subcommand Definitions
    private SubcommandHelp subHelp;         // help
    private SubcommandSet subSet;           // set
    private SubcommandReset subReset;       // reset
    private SubcommandCurrent subCurrent;   // current
    private SubcommandVersion subVersion;   // version
    private SubcommandInvalid subInvalid;   // show invalid command message
    private SubcommandReload subReload;     // reload config

    public CommandMainCW(WaypointCompass main) {
        this.main = main;
        this.subHelp = new SubcommandHelp(main);
        this.subSet = new SubcommandSet(main);
        this.subReset = new SubcommandReset(main);
        this.subCurrent = new SubcommandCurrent(main);
        this.subVersion = new SubcommandVersion(main);
        this.subInvalid = new SubcommandInvalid(main);
        this.subReload = new SubcommandReload(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check permissions
            if (player.hasPermission("waypointcompass.use")) {
                // Display help
                if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                    return this.subHelp.onCommand(sender, command, label, args);
                }
                // Set waypoint coordinates
                else if (args[0].equalsIgnoreCase("set")) {
                    return this.subSet.onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
                }
                // Reset compass to spawn
                else if (args[0].equalsIgnoreCase("reset")) {
                    return this.subReset.onCommand(sender, command, label, args);
                }
                // Return current heading
                else if (args[0].equalsIgnoreCase("current")) {
                    return this.subCurrent.onCommand(sender, command, label, args);
                }
                // Version command
                else if (args[0].equalsIgnoreCase("version")) {
                    return this.subVersion.onCommand(sender, command, label, args);
                }
                // Reload command
                else if (args[0].equalsIgnoreCase("reload")) {
                    return this.subReload.onCommand(sender, command, label, args);
                }
                // Unknown subcommand
                else {
                    return this.subInvalid.onCommand(sender, command, label, args);
                }
            } else {
                String msg = main.getConfigMessages().getString("prefix") + main.getConfigMessages().getString("error.noPerms");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                return false;
            }

        }
        else {
            // If sender is console, they can use reload. Otherwise, send version string.
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                return this.subReload.onCommand(sender, command, label, args);
            }
            else {
                return this.subVersion.onCommand(sender, command, label, args);
            }
        }
    }
}
