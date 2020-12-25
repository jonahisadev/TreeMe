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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (args[0]) {
                case "toggle": {
                    _plugin.playerStore.toggle(player.getUniqueId());
                    boolean state = _plugin.playerStore.state(player.getUniqueId());
                    String msg = state ? (ChatColor.GREEN + "enabled") : (ChatColor.RED + "disabled");
                    player.sendMessage(ChatColor.GRAY + "TreeMe is now " + msg + ChatColor.GRAY + " for you");
                    break;
                }
                default:
                    return false;
            }

            return true;
        }

        return false;
    }

}
