package me.jonahisadev.treeme;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChopCommand implements CommandExecutor {

    private Main _plugin;

    public ChopCommand(Main plugin)
    {
        _plugin = plugin;
    }

    private void sendHelp(Player player)
    {
        player.sendMessage(ChatColor.GREEN + "TreeMe Commands:");
        player.sendMessage(ChatColor.GOLD + "  /treeme toggle: Toggle TreeMe features for yourself");
        if (_plugin.config.getBoolean("replant"))
            player.sendMessage(ChatColor.GOLD + "  /treeme replant: Toggle replanting features for yourself");
        player.sendMessage(ChatColor.GOLD + "  /treeme help: Show this message");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Commands
            switch (args[0]) {
                case "toggle": {
                    _plugin.playerStore.toggle(player.getUniqueId(), "enabled");
                    boolean state = _plugin.playerStore.state(player.getUniqueId()).enabled;
                    String msg = state ? (ChatColor.GREEN + "enabled") : (ChatColor.RED + "disabled");
                    player.sendMessage(ChatColor.GRAY + "TreeMe is now " + msg + ChatColor.GRAY + " for you");
                    break;
                }
                case "replant": {
                    if (!_plugin.config.getBoolean("replant"))
                        sendHelp(player);

                    _plugin.playerStore.toggle(player.getUniqueId(), "replant");
                    boolean state = _plugin.playerStore.state(player.getUniqueId()).replant;
                    String msg = state ? (ChatColor.GREEN + "enabled") : (ChatColor.RED + "disabled");
                    player.sendMessage(ChatColor.GRAY + "TreeMe replant is now " + msg + ChatColor.GRAY + " for you");
                    break;
                }
                case "help": {
                    sendHelp(player);
                    break;
                }
                default:
                    sendHelp(player);
                    break;
            }

            return true;
        }

        sender.sendMessage("This should only be run by players for now");
        return true;
    }

}
