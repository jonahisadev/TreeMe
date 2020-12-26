package me.jonahisadev.treeme;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChopCommandCompleter implements TabCompleter {

    private Main _plugin;

    public ChopCommandCompleter(Main plugin)
    {
        _plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            List<String> list = new ArrayList<>();
            list.add("toggle");
            if (_plugin.config.getBoolean("replant"))
                list.add("replant");
            list.add("help");

            // Extra commands for admin
            if (player.hasPermission("treeme.admin")) {
                // TODO: extra admin commands
            }

            return list;
        }

        return null;
    }

}
